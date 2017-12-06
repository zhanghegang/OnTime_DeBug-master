package zhanghegang.com.bawei.onetime.utils;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import zhanghegang.com.bawei.onetime.bean.BanderBean;
import zhanghegang.com.bawei.onetime.bean.BaseReg;
import zhanghegang.com.bawei.onetime.bean.RegBean;
import zhanghegang.com.bawei.onetime.bean.StatinBean;
import zhanghegang.com.bawei.onetime.bean.UserInfo;
import zhanghegang.com.bawei.onetime.bean.VideosBean;

/**
 * current package:zhanghegang.com.bawei.onetime.utils
 * Created by Mr.zhang
 * date: 2017/11/27
 * decription:开发
 */

public interface UrlData {
@POST("user/login")
@FormUrlEncoded
    Observable<BaseReg<RegBean>> getReg(@FieldMap Map<String,Object> map);
@POST("user/getUserInfo")
@FormUrlEncoded
    Observable<ResponseBody> getUserInfo(@Field("uid") String uid);
@POST("quarter/getJokes")
@FormUrlEncoded
    Observable<StatinBean> getStatin(@Field("page") String page);
@POST("quarter/publishJoke")
@Multipart
    Observable<ResponseBody> getStatinContent(@Part List<MultipartBody.Part> parts);
@POST("quarter/register")
@FormUrlEncoded
    Observable<ResponseBody> getRegin(@FieldMap Map<String,String> map);
@POST("quarter/getAd")
    Observable<BanderBean> getBanner();
@POST("quarter/getVideos")
@FormUrlEncoded
    Observable<VideosBean> getHot_look(@FieldMap Map<String,String> map);
@POST("quarter/publishVideo")
@Multipart
    Observable<ResponseBody> getPublicshVideo(@Part List<MultipartBody.Part> parts);


}
