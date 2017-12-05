package zhanghegang.com.bawei.onetime.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import zhanghegang.com.bawei.onetime.MyApp;

/**
 * current package:zhanghegang.com.bawei.onetime.utils
 * Created by Mr.zhang
 * date: 2017/11/15
 * decription:开发
 */

public class RetrofitUtils {
public static RetrofitUtils retrofitUtils;
    private final UrlData urlData;


public RetrofitUtils(UrlData urlData){
    this.urlData=urlData;
}
public UrlData getUrlData(){
    return urlData;
}
public static class Builder{
    List<Converter.Factory> converters=new ArrayList<>();
    List<CallAdapter.Factory> adapters=new ArrayList<>();
    public Builder addConverterFactory(Converter.Factory converter){
        converters.add(converter);
        return this;

    }
    public Builder addCallAdapterFactory(CallAdapter.Factory adapter){
        adapters.add(adapter);
        return this;
    }
   public RetrofitUtils build(){
      /* OkHttpClient.Builder okhttpBuilder=new OkHttpClient.Builder().addInterceptor(new NetInterceptor() {
           @Override
           public Map<String, String> getHeadMap() {

//               Map<String,String> map=new HashMap<>();
//               String token = (String) SharePrefrenceUtils.getData(SharePrefrenceBack.String, "token");
//               PackageInfo packageArchiveInfo = MyApp.app.getPackageManager().getPackageArchiveInfo(MyApp.app.getPackageName(), 0);
//               int versionCode = packageArchiveInfo.versionCode;
//               map.put("source","android");
//               map.put("token",token+"");
//               map.put("appVersion",versionCode+"");
               return null;
           }
           int versionCode =0;
           @Override
           public Map<String, String> getQueryFormMap() {
               Map<String,String> map=new HashMap<>();
               String token = (String) SharePrefrenceUtils.getData(SharePrefrenceBack.String, "token");
               try {
                   Context context = MyApp.app;
                   PackageManager pm = context.getPackageManager();
                   PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
                   versionCode = pi.versionCode;
               } catch (PackageManager.NameNotFoundException e) {
                   e.printStackTrace();
               }

               map.put("source","android");
               map.put("token",token+"");
               map.put("appVersion",versionCode+"");
               return map;
           }

           @Override
           public Map<String, String> getFormBodyMap() {
               Map<String,String> map=new HashMap<>();
               String token = (String) SharePrefrenceUtils.getData(SharePrefrenceBack.String, "token");
               try {
                   Context context = MyApp.app;
                   PackageManager pm = context.getPackageManager();
                   PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
                   versionCode = pi.versionCode;
               } catch (PackageManager.NameNotFoundException e) {
                   e.printStackTrace();
               }
               map.put("source","android");
               map.put("token",token);
               map.put("appVersion",versionCode+"");
               return map;
           }
       });*/
       OkHttpClient.Builder okhtttp=new OkHttpClient.Builder().addInterceptor(new MyIntercepter());
       Retrofit.Builder retrofit=new Retrofit.Builder();
       if(converters.size()==0)
       {
          retrofit.addConverterFactory(GsonConverterFactory.create());
       }
       else {
           for (int i = 0; i <converters.size() ; i++) {
              retrofit.addConverterFactory(converters.get(i)) ;
           }
       }
       if(adapters.size()==0)
       {
           retrofit.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
       }
       else {
           for (int i = 0; i <adapters.size() ; i++) {
               retrofit.addCallAdapterFactory(adapters.get(i));
           }
       }
       Retrofit build = retrofit.client(okhtttp.build()).baseUrl(UrlSum.BASEURL).build();
       UrlData urlData = build.create(UrlData.class);
       retrofitUtils=new RetrofitUtils(urlData);
       return retrofitUtils;

   }
}

}
