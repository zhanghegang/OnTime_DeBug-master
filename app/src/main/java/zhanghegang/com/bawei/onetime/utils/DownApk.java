package zhanghegang.com.bawei.onetime.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * current package:zhanghegang.com.bawei.onetime.utils
 * Created by Mr.zhang
 * date: 2017/12/15
 * decription:开发
 */

public class DownApk {
    private String TAGDOWN="DOWNFILE";
    private String url;
    private int ThreadCount;
    private Context context;

    public DownApk(String url, int threadCount, Context context) {
        this.url = url;
        ThreadCount = threadCount;
        this.context = context;
    }

    public void downNewVersion(){
        HttpLoggingInterceptor.Logger logger=new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("DOWN********",message+"*************");
            }
        };
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(logger);
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder=new OkHttpClient.Builder().readTimeout(3, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor);
        OkHttpClient okHttpClient = builder.build();
        //开始请求初始值
        if(!TextUtils.isEmpty(url))
        {
        Request.Builder request=new Request.Builder().url(url).get();
            Request initRequest = request.build();
            Call call = okHttpClient.newCall(initRequest);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println(TAGDOWN+e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    int code = response.code();
                    if(code==200)
                    {

                    }
                }
            });
        }
        else {
            System.out.println(TAGDOWN+"DownFile逗我呢，没地址");
        }


    }
}
