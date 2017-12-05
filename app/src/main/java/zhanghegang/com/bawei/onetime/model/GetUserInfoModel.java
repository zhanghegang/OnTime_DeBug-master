package zhanghegang.com.bawei.onetime.model;

import android.content.pm.PackageInfo;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import zhanghegang.com.bawei.onetime.MyApp;
import zhanghegang.com.bawei.onetime.bean.BaseReg;
import zhanghegang.com.bawei.onetime.bean.RegBean;
import zhanghegang.com.bawei.onetime.bean.UserInfo;
import zhanghegang.com.bawei.onetime.utils.NetInterceptor;
import zhanghegang.com.bawei.onetime.utils.RetrofitUtils;
import zhanghegang.com.bawei.onetime.utils.SharePrefrenceBack;
import zhanghegang.com.bawei.onetime.utils.SharePrefrenceUtils;
import zhanghegang.com.bawei.onetime.utils.UrlData;
import zhanghegang.com.bawei.onetime.utils.UrlSum;

/**
 * current package:zhanghegang.com.bawei.onetime.model
 * Created by Mr.zhang
 * date: 2017/11/12
 * decription:开发
 */

public class GetUserInfoModel{

    public void getUserInfo(String uid, final GetUserInfoCallBack getUserInfoCallBack) {

        UrlData urlData = new RetrofitUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().getUrlData();
        urlData.getUserInfo(uid).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
        .subscribe(new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseBody res) {
                try {
                    String data = res.string();
                    JSONObject jsonObject=new JSONObject(data);
                    String code = jsonObject.getString("code");
                    System.out.println("code=========onNext======"+code);
                    UserInfo userInfo=null;
                    if("0".equals(code))
                    {
                        Gson gson=new Gson();
                       userInfo = gson.fromJson(data, UserInfo.class);

                    }
                    else
                    {
                      userInfo=new UserInfo();
                        userInfo.setCode(code);

                    }


                    getUserInfoCallBack.loadSucess(userInfo);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable e) {
//                System.out.println("thowable"+e.getMessage().toString());
getUserInfoCallBack.loadFail(e.getMessage().toString());
            }

            @Override
            public void onComplete() {

            }
        })
        ;


    }


}
