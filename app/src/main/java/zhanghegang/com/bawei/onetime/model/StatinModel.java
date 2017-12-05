package zhanghegang.com.bawei.onetime.model;

import com.google.gson.Gson;

import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import zhanghegang.com.bawei.onetime.bean.StatinBean;
import zhanghegang.com.bawei.onetime.bean.UserInfo;
import zhanghegang.com.bawei.onetime.utils.RetrofitUtils;
import zhanghegang.com.bawei.onetime.utils.UrlData;

/**
 * current package:zhanghegang.com.bawei.onetime.model
 * Created by Mr.zhang
 * date: 2017/11/12
 * decription:开发
 */

public class StatinModel {

    public void getStatinInfo(String page, final StatinCallBack statinCallBack) {
        UrlData urlData = new RetrofitUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().getUrlData();
       urlData.getStatin(page).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Observer<StatinBean>() {
                   @Override
                   public void onSubscribe(Disposable d) {

                   }

                   @Override
                   public void onNext(StatinBean statinBean) {
statinCallBack.loadSucess(statinBean);
                   }

                   @Override
                   public void onError(Throwable e) {
statinCallBack.loadFail(e.toString());
                   }

                   @Override
                   public void onComplete() {

                   }
               });


    }
}
