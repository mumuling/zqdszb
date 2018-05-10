package ghitbug.zqdszb.library.retrofit.Api;


import ghitbug.zqdszb.library.retrofit.subscribers.ProgressDialogUtil;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * 请求数据统一封装类
 * * @version v1.0
 *
 * @date 2017/3/14
 * @auth wcy
 * @company 重庆锐云科技有限公司
 */
public abstract class BaseApi {
    /*是否能取消加载框*/
    private boolean cancel = false;
    /*是否显示加载框*/
    private boolean showProgress = true;
    /*基础url*/
//    private String baseUrl = "http://appadviser.cqlure.cn/version2/";
    private String baseUrl = "";
    //private String baseUrl="http://testapps.hejuzg.com/version1/"prysy.hejuzg.cc;
//    private String baseUrl = "http://appadviser.yj.cn.com/version2/";
    /*方法-如果需要缓存必须设置这个参数；不需要p不用設置*/
    private String method;
    /*超时时间-默认6秒*/
    private int connectionTime = 6;
    private Class<?> data;
    private boolean isList = false;
    private String msg;
    private ProgressDialogUtil progressDialog;//自定义提示

    /**
     * 设置参数
     *
     * @param retrofit
     * @return
     */
    public abstract Observable getObservable(Retrofit retrofit);

    public int getConnectionTime() {
        return connectionTime;
    }

    public void setConnectionTime(int connectionTime) {
        this.connectionTime = connectionTime;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public boolean isShowProgress() {
        return showProgress;
    }

    public void setShowProgress(boolean showProgress) {
        this.showProgress = showProgress;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Class<?> getData() {
        return data;
    }

    public void setData(Class<?> data) {
        this.data = data;
    }

    public boolean isList() {
        return isList;
    }

    public void setList(boolean list) {
        isList = list;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ProgressDialogUtil getProgressDialog() {
        return progressDialog;
    }

    public void setProgressDialog(ProgressDialogUtil progressDialog) {
        this.progressDialog = progressDialog;
    }
}
