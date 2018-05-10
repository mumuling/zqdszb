package ghitbug.zqdszb.library.basemvp;

import android.support.v7.app.AppCompatActivity;

import com.alibaba.fastjson.JSONObject;

import java.lang.ref.WeakReference;

/**
 * BasePresenter
 * friendscloud-android V1.0
 * 2018/2/28
 *
 * @auth wangchaoyong
 * 重庆锐云科技有限公司
 */

public class BasePresenter<V extends BaseView, M extends BaseModel> {
    public M mModel;
    public WeakReference<V> mViewRef;
    private AppCompatActivity appCompatActivity;
    BaseModeImpl baseMode;

    public void attachModelView(V pView, M pModel, AppCompatActivity appCompatActivity) {
        mViewRef = new WeakReference<>(pView);
        this.appCompatActivity = appCompatActivity;
        this.mModel = pModel;
        if (pModel != null) {
            ((BaseModeImpl) mModel).attachPresenter(getView(), appCompatActivity);
        }
    }


    public V getView() {
        if (isAttach()) {
            return mViewRef.get();
        } else {
            return null;
        }
    }

    public boolean isAttach() {
        return null != mViewRef && null != mViewRef.get();
    }

    /**
     * 无presenter下使用，请他情况下禁止调用
     *
     * @param method
     * @param parameters
     * @param cl
     * @param isList
     * @param isShowProgress
     * @param toast
     */
    public void sendPost(String method, JSONObject parameters, Class<?> cl, boolean isList, boolean isShowProgress, String toast) {
        if (baseMode == null) baseMode = new BaseModeImpl();
        baseMode.attachPresenter(getView(), appCompatActivity);
        baseMode.sendPost(method, parameters, cl, isList, isShowProgress, toast);
    }

    public void onDettach() {
        if (null != mViewRef) {
            mViewRef.clear();
            baseMode=null;
            mModel=null;
            mViewRef = null;
        }
    }
}
