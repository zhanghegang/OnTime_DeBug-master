package zhanghegang.com.bawei.onetime.comment;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import zhanghegang.com.bawei.onetime.RegActivity;
import zhanghegang.com.bawei.onetime.bean.BaseReg;
import zhanghegang.com.bawei.onetime.bean.RegBean;

/**
 * current package:zhanghegang.com.bawei.onetime.comment
 * Created by Mr.zhang
 * date: 2017/11/16
 * decription:开发
 */

public interface UiData {
    @Headers({"Content-type:application/json;charset=utf-8"})
    @POST("login")

    Observable<ResponseBody> getlogin(@Body RequestBody res);
}
