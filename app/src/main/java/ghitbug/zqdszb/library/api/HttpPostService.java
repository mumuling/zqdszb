package ghitbug.zqdszb.library.api;

import ghitbug.zqdszb.library.common.JConstant;
import retrofit2.http.GET;
import rx.Observable;

/**
 * 测试接口service-post相关
 * Created by wcy on 2017/3/14.
 */

public interface HttpPostService {
    //@Path("province_id") String province_id, @Query("token") String token

    //热播数据
    String LIVE_HOT_CHANNEL = "getLiveHotChannel";

    @GET("tv/hot_channel/1/"+ JConstant.CLEINT_ID+"/"+JConstant.VERSION)
    Observable<String> getLiveHotChannel();

}
