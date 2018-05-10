package ghitbug.zqdszb.library.api;


import java.io.File;

import ghitbug.zqdszb.library.api.entitys.UpdateImage;
import ghitbug.zqdszb.library.common.JConstant;
import ghitbug.zqdszb.library.retrofit.Api.BaseApi;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * 上传请求api
 * Created by wcy on 2017/3/14.
 */

public class UploadApi extends BaseApi {
    /*需要上传的文件*/
    private MultipartBody.Part part;

    public UploadApi() {
        setShowProgress(false);
        setMethod("");
        setMsg("图片上传中......");
        setData(UpdateImage.class);
    }

    public MultipartBody.Part getPart() {
        return part;
    }

    public void setFile(String path) {
        File file = new File(path);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
    }

    public void setByte(byte[] bytes) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), bytes);
        part = MultipartBody.Part.createFormData("file", "imgs", requestBody);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpUploadService httpService = retrofit.create(HttpUploadService.class);
        String token = JConstant.getToken();
        RequestBody uid = RequestBody.create(MediaType.parse("text/plain"), token);
        return httpService.uploadImage(uid, getPart());
    }

}
