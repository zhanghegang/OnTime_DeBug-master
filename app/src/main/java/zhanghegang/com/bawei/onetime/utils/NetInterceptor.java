package zhanghegang.com.bawei.onetime.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * current package:zhanghegang.com.bawei.onetime.utils
 * Created by Mr.zhang
 * date: 2017/11/27
 * decription:开发
 */

public abstract class NetInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        Map<String, String> headMap = getHeadMap();
        //获得头部
        Headers.Builder newHeaderBuilder = request.headers().newBuilder();
       //添加到头部
        if(headMap!=null&&!headMap.isEmpty())
        {
            for (Map.Entry<String, String> entry : headMap.entrySet()) {
                newHeaderBuilder.add(entry.getKey(),entry.getValue());
            }
            builder.headers(newHeaderBuilder.build());
        }
        //添加到地址上
        if("GET".equals(request.method()))
        {
            HttpUrl.Builder newUrlBuilder = request.url().newBuilder();
            Map<String, String> queryFormMap = getQueryFormMap();
            if(queryFormMap!=null&&queryFormMap.isEmpty())
            {
                for (Map.Entry<String, String> entry : queryFormMap.entrySet()) {
                    newUrlBuilder.addQueryParameter(entry.getKey(),entry.getValue());
                }
                builder.url(newUrlBuilder.build());
            }
        }
        else if("POST".equals(request.method()))
        {
            RequestBody body = request.body();
            if(body!=null&&body instanceof FormBody)
            {
                FormBody formBody=(FormBody) body;
                Map<String,String> formBodyParaMap=new HashMap<>();
                int bodySize = formBody.size();
                for (int i = 0; i < bodySize; i++) {
                    formBodyParaMap.put(formBody.name(i),formBody.value(i));
                }
                Map<String, String> formBodyMap = getFormBodyMap();
                if(formBody!=null)
                {
                    formBodyMap.putAll(formBodyMap);
                    FormBody.Builder bodyBuilder=new FormBody.Builder();
                    for (Map.Entry<String, String> entry : formBodyParaMap.entrySet()) {
                        bodyBuilder.add(entry.getKey(),entry.getValue());
                    }
                    builder.method(request.method(),bodyBuilder.build());
                }
            }else if(body!=null&&body instanceof MultipartBody)
            {
                // POST Param form-data
                MultipartBody multipartBody = (MultipartBody) body;
                MultipartBody.Builder mbuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                Map<String, String> extraFormBodyParamMap = getFormBodyMap();
                for (Map.Entry<String, String> entry : extraFormBodyParamMap.entrySet()) {
                    mbuilder.addFormDataPart(entry.getKey(), entry.getValue());
                }
                List<MultipartBody.Part> parts = multipartBody.parts();
                for (MultipartBody.Part part : parts) {
                    mbuilder.addPart(part);
                }
                builder.post(mbuilder.build());



            }

        }
        return chain.proceed(builder.build());
    }
    public abstract Map<String,String> getHeadMap();
    public abstract Map<String,String> getQueryFormMap();
    public abstract Map<String,String> getFormBodyMap();
}
