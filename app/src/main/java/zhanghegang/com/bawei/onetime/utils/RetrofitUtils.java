package zhanghegang.com.bawei.onetime.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import zhanghegang.com.bawei.onetime.MyApp;
import zhanghegang.com.bawei.onetime.base.BaseCallBackShowLodding;

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
    public Builder showLoading(BaseCallBackShowLodding baseCallBackShowLodding){
        if(baseCallBackShowLodding!=null)
        {
            baseCallBackShowLodding.showLodding();
        }
        return this;
    }
   public RetrofitUtils build(){
        long Size_Cache=10*1024*1024;
        //添加日志拦截器
       HttpLoggingInterceptor httpLoggingInterceptor = addLogInteception();
       //添加公共参数拦截器
       OkHttpClient.Builder okhtttp=new OkHttpClient.Builder().addInterceptor(new MyIntercepter()).addInterceptor(httpLoggingInterceptor);
     //添加缓存拦截器
        //       if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        //       {
           String cacheFile=MyApp.app.getCacheDir()+"/onetime";
           Cache cache=new Cache(new File(cacheFile),Size_Cache);
           okhtttp.addNetworkInterceptor(CacheIntercepter.ReWiterInterceptor)
                   .addInterceptor(CacheIntercepter.ReWiterInterceptor_Offline)
                   .cache(cache);
        //       }
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

    private HttpLoggingInterceptor addLogInteception() {
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("requestBody[*****",message+"*****]");
            }
        });
        //添加请求后打印信息的内容，分为NONE,BASIC,HEADERS,BODY
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

return httpLoggingInterceptor;


    }
}

}
