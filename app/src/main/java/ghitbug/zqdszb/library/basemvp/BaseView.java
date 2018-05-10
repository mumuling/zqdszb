package ghitbug.zqdszb.library.basemvp;


import android.content.Context;

import ghitbug.zqdszb.library.api.entitys.BaseResult;
import ghitbug.zqdszb.library.retrofit.exception.ApiException;


/**
 * BaseView
 * friendscloud-android V1.0
 * 2018/2/28
 *
 * @auth wangchaoyong
 * 重庆锐云科技有限公司
 */

public interface BaseView {
    /**
     * 成功后回调方法
     */
    void onNext(BaseResult result);

    /**
     * 失败
     * 失败或者错误方法
     * 自定义异常处理
     *
     * @param e
     */
    void onError(ApiException e, String mothead);

    Context getThisContext();

}
