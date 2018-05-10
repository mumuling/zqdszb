package ghitbug.zqdszb.library.retrofit.subscribers;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import ghitbug.zqdszb.R;
import ghitbug.zqdszb.library.common.JConstant;
import ghitbug.zqdszb.library.retrofit.Api.BaseApi;
import ghitbug.zqdszb.library.retrofit.exception.ApiException;
import ghitbug.zqdszb.library.retrofit.exception.CodeException;
import ghitbug.zqdszb.library.retrofit.exception.HttpTimeException;
import ghitbug.zqdszb.library.retrofit.listener.HttpOnNextListener;
import ghitbug.zqdszb.library.utils.utils.DES3.StringEncrypt;
import ghitbug.zqdszb.library.utils.utils.RxDataTool;
import rx.Subscriber;

/**
 * 用于在Http请求开始时，自动显示一个ProgressDialog
 * 在Http请求结束是，关闭ProgressDialog
 * 调用者自己对请求数据进行处理
 *
 * @version v1.0
 * @date 2017/3/14
 * @auth wcy
 * @company 重庆锐云科技有限公司
 */
public class ProgressSubscriber<T> extends Subscriber<T> {
    /*是否弹框*/
    private boolean showPorgress = true;
    //    回调接口
    private HttpOnNextListener mSubscriberOnNextListener;
    //    加载框可自己定义
    private ProgressDialogUtil progressDialog;
    /*请求数据*/
    private BaseApi api;

    /**
     * 构造
     *
     * @param api
     */
    public ProgressSubscriber(final BaseApi api, HttpOnNextListener listenerSoftReference, AppCompatActivity context) {
        this.api = api;
        this.mSubscriberOnNextListener = listenerSoftReference;
        setShowPorgress(api.isShowProgress());
        if (api.isShowProgress()) {
            progressDialog = api.getProgressDialog();
            if (progressDialog == null) {
                progressDialog = new ProgressDialogUtil(context) {
                    @Override
                    protected int getLayoutId() {
                        return R.layout.dialog_loading;
                    }

                    @Override
                    protected void setView(Window window) {
                        TextView tv = window.findViewById(R.id.textViewMessage);
                        if (!RxDataTool.isNullString(api.getMsg())) {
                            tv.setText(api.getMsg());
                        }
                    }
                };
                progressDialog.setCanselable(api.isCancel());
            }
        }
    }

    /**
     * 显示加载框
     */
    private void showProgressDialog() {
        if (!isShowPorgress()) return;
        if (progressDialog == null) return;
        progressDialog.show();
    }


    /**
     * 隐藏
     */
    private void dismissProgressDialog() {
        if (!isShowPorgress()) return;
        if (progressDialog != null) {
            onCancelProgress();
            progressDialog.hide();
        }
    }


    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        showProgressDialog();
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {

    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        errorDo(e);
        dismissProgressDialog();
    }


    /**
     * 错误统一处理
     *
     * @param e
     */
    private void errorDo(Throwable e) {
        if (mSubscriberOnNextListener == null) return;
        if (e instanceof HttpTimeException) {
            HttpTimeException exception = (HttpTimeException) e;
            mSubscriberOnNextListener.onError(new ApiException(exception, CodeException.RUNTIME_ERROR, exception.getMessage()), api.getMethod());
        } else {
            Log.e("ProgressSubscriber", "网络连接错误：" + api.getMethod());
            mSubscriberOnNextListener.onError(new ApiException(e, CodeException.UNKNOWN_ERROR, "网络连接错误"), api.getMethod());
        }
        /*可以在这里统一处理错误处理-可自由扩展*/
        //Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
    }


    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener != null) {
            try {
                String result = (String) t;
                StringEncrypt  encrypter = new StringEncrypt(JConstant.TripleDESKey);
                result = encrypter.decrypt(result, JConstant.CN);

                Log.i("方法：", api.getMethod());
                Log.i("ProgressSubscriber未解密", result);

                if (!RxDataTool.isNullString(result)) {
                    mSubscriberOnNextListener.onNext(api, result);
                } else {
                    mSubscriberOnNextListener.onError(new ApiException(null, CodeException.ERROR, "服务器返回数据错误"), api.getMethod());
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("ProgressSubscriber", e.getMessage());
                mSubscriberOnNextListener.onError(new ApiException(e, CodeException.JSON_ERROR, "网络数据处理错误"), api.getMethod());
            }
        }
        dismissProgressDialog();

    }


    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            mSubscriberOnNextListener = null;
            this.unsubscribe();
        }
    }


    public boolean isShowPorgress() {
        return showPorgress;
    }

    /**
     * 是否需要弹框设置
     *
     * @param showPorgress
     */
    public void setShowPorgress(boolean showPorgress) {
        this.showPorgress = showPorgress;
    }

    public void setProgressDialog(ProgressDialogUtil progressDialog) {
        this.progressDialog = progressDialog;
    }
}