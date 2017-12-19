package zhanghegang.com.bawei.onetime.utils;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.MultipartBody;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import zhanghegang.com.bawei.onetime.bean.BanderBean;
import zhanghegang.com.bawei.onetime.bean.BaseReg;
import zhanghegang.com.bawei.onetime.bean.HotVideoBean;
import zhanghegang.com.bawei.onetime.bean.RegBean;
import zhanghegang.com.bawei.onetime.bean.StatinBean;

import zhanghegang.com.bawei.onetime.bean.UserInterfaceBean;
import zhanghegang.com.bawei.onetime.bean.VerSionUpdateBean;
import zhanghegang.com.bawei.onetime.bean.VideosBean;

/**
 * current package:zhanghegang.com.bawei.onetime.utils
 * Created by Mr.zhang
 * date: 2017/11/27
 * decription:开发
 */

public interface UrlData {
  @Headers("cache:20")
@GET("user/login")
Observable<BaseReg<RegBean>> getReg(@QueryMap Map<String,Object> map);
@POST("user/getUserInfo")
@FormUrlEncoded
    Observable<ResponseBody> getUserInfo(@Field("uid") String uid);

 @Headers("cache:20")
@GET("quarter/getJokes")
Observable<StatinBean> getStatin(@Query("page") String page);

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
@Headers("cache:20")
@GET("quarter/getHotVideos")
    Observable<HotVideoBean> getHotVideo(@Query("page") String page);
    @GET("quarter/getVersion")
    Flowable<VerSionUpdateBean> getVerSionUpdate();
    @Headers("cache:20")
    @GET("quarter/getUserVideos")
    Flowable<UserInterfaceBean> getUserInterface(@QueryMap Map<String,String> map);
    @GET("quarter/follow")
    Flowable<ResponseBody> getFollow(@QueryMap Map<String,String> map);

}
