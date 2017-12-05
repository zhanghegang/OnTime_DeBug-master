package zhanghegang.com.bawei.onetime.model;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import zhanghegang.com.bawei.onetime.bean.UserInfo;
import zhanghegang.com.bawei.onetime.utils.RetrofitUtils;
import zhanghegang.com.bawei.onetime.utils.UrlData;

/**
 * current package:zhanghegang.com.bawei.onetime.model
 * Created by Mr.zhang
 * date: 2017/11/12
 * decription:开发
 */

public class StatinContentInfoModel {

    public void getStatinContentInfo(List<MultipartBody.Part> parts, final StatinContentCallBack getStatinContentInfoCallBack) {

        UrlData urlData = new RetrofitUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().getUrlData();
urlData.getStatinContent(parts).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String json = responseBody.string();
JSONObject jsonObject=new JSONObject(json);
                    String code = jsonObject.getString("code");

                        getStatinContentInfoCallBack.loadSucess(code);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
getStatinContentInfoCallBack.loadFail(e.toString());
            }

            @Override
            public void onComplete() {

            }
        });



    }


}
