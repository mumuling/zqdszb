package ghitbug.zqdszb.library.api;


import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

import ghitbug.zqdszb.library.common.JConstant;
import ghitbug.zqdszb.library.retrofit.Api.BaseApi;
import ghitbug.zqdszb.library.utils.utils.AESOperator;
import retrofit2.Retrofit;
import rx.Observable;


/**
 * Created by wcy on 2017/3/14.
 */
public class SubjectPostApi extends BaseApi {
    private JSONObject parameters;

    public JSONObject getParameters() {
        return parameters;
    }

    public static HashMap<String, Integer> mapMethods = new HashMap<>();

    /**
     * 参数设置
     *
     * @param method     请求指令
     * @param parameters 请求参数
     */
    public void setParameters(String method, JSONObject parameters) {
        super.setMethod(method);
        this.parameters = parameters;
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        Object httpService = retrofit.create(JConstant.getHttpPostService());
        try {
            String params = "";
            String token = JConstant.getToken();
            if (parameters != null) {
                if (JConstant.isEncrypt()) {
                    params = AESOperator.encrypt(parameters.toJSONString());
                } else {
                    params = parameters.toJSONString();
                }
            }
            int count = mapMethods.get(getMethod());
            if (count == 0) {
                return (Observable) httpService.getClass().getMethod(getMethod()).invoke(httpService);
            } else if (count == 1) {
                if (parameters == null) {
                    params = token;
                }
                return (Observable) httpService.getClass().getMethod(getMethod(), new Class[]{String.class}).invoke(httpService, params);
            } else {
                return (Observable) httpService.getClass().getMethod(getMethod(), new Class[]{String.class, String.class}).invoke(httpService, params, token);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
