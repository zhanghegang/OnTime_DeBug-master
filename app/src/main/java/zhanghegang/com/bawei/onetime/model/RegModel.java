package zhanghegang.com.bawei.onetime.model;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import zhanghegang.com.bawei.onetime.base.BaseCallBack;
import zhanghegang.com.bawei.onetime.bean.UserInfo;
import zhanghegang.com.bawei.onetime.utils.RetrofitUtils;
import zhanghegang.com.bawei.onetime.utils.UrlData;

/**
 * current package:zhanghegang.com.bawei.onetime.model
 * Created by Mr.zhang
 * date: 2017/11/12
 * decription:开发
 */

public class RegModel {

    public void getRegInfo(Map<String,String> map, final BaseCallBack regCallBack) {

        UrlData urlData = new RetrofitUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().getUrlData();
urlData.getRegin(map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String json = responseBody.string();
JSONObject jsonObject=new JSONObject(json);
                    String code = jsonObject.optString("code");
                    System.out.println("=regCode============"+code);
                    regCallBack.loadSucess(code);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
regCallBack.loadFail(e.toString());
            }

            @Override
            public void onComplete() {

            }
        });


    }


}
