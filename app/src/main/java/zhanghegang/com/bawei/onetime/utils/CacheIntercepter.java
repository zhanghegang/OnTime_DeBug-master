package zhanghegang.com.bawei.onetime.utils;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import zhanghegang.com.bawei.onetime.MyApp;
import zhanghegang.com.bawei.onetime.bean.RegBean;

/**
 * current package:zhanghegang.com.bawei.onetime.utils
 * Created by Mr.zhang
 * date: 2017/12/7
 * decription:开发
 */

public class CacheIntercepter {
    private static final int TIMEOUT_DISCONNECT = 60 * 60 * 24 * 7;
public static final Interceptor ReWiterInterceptor=new Interceptor() {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String cache = request.header("cache");
        Response proceed = chain.proceed(request);
        String header = proceed.header("Cache-Control");
        if(!TextUtils.isEmpty(header))
        {
            if(cache==null||"".equals(cache))
            {
                cache=5+"";
            }
            proceed=proceed.newBuilder()
                    .header("Cache-Control","pulic,max-age="+cache)
                    .build();
            return proceed;
        }
        else {
            return proceed;
        }

    }
};
public static final Interceptor ReWiterInterceptor_Offline=new Interceptor() {

    private Request request;

    @Override
    public Response intercept(Chain chain) throws IOException {
        request = chain.request();
        NetInfoUtils.gainNetInfoStatus(MyApp.app, new NetInfoUtils.OnNetInfoStatus() {
            @Override
            public void hasNet() {

            }

            @Override
            public void noNet() {
                System.out.println("netUserInfo======cache_offline");
                request = request.newBuilder()
        .header("Cache-Control","public,only-if-cached,max-stale="+TIMEOUT_DISCONNECT)
        .build();

            }
        });
        return chain.proceed(request);
    }
};


}
