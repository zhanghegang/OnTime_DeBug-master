package zhanghegang.com.bawei.onetime.model;

import com.google.gson.Gson;

import org.json.JSONObject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import zhanghegang.com.bawei.onetime.bean.BanderBean;
import zhanghegang.com.bawei.onetime.bean.UserInfo;
import zhanghegang.com.bawei.onetime.utils.RetrofitUtils;
import zhanghegang.com.bawei.onetime.utils.UrlData;

/**
 * current package:zhanghegang.com.bawei.onetime.model
 * Created by Mr.zhang
 * date: 2017/11/12
 * decription:开发
 */

public class HotInfoModel {

    public void getUserInfo( final HotInfoCallBack hotInfoCallBack) {

        UrlData urlData = new RetrofitUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().getUrlData();
        urlData.getBanner().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BanderBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BanderBean banderBean) {
                        if("0".equals(banderBean.getCode()))
                        {
                           hotInfoCallBack.loadSucess(banderBean);
                        }
                        else {
                            hotInfoCallBack.loadFail("获取有误");
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
hotInfoCallBack.loadFail(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }


}
