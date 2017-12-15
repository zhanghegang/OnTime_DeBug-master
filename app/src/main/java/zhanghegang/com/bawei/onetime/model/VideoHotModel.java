package zhanghegang.com.bawei.onetime.model;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import zhanghegang.com.bawei.onetime.base.BaseCallBack;
import zhanghegang.com.bawei.onetime.bean.HotVideoBean;
import zhanghegang.com.bawei.onetime.utils.RetrofitUtils;
import zhanghegang.com.bawei.onetime.utils.UrlData;

/**
 * current package:zhanghegang.com.bawei.onetime.model
 * Created by Mr.zhang
 * date: 2017/12/7
 * decription:开发
 */

public class VideoHotModel {
    public void getUserInfo(String page, final BaseCallBack getVideoCallBack) {

        UrlData urlData = new RetrofitUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().getUrlData();
        urlData.getHotVideo(page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotVideoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HotVideoBean hotVideoBean) {
                        System.out.println("msg=====HotVideoBean========="+hotVideoBean.getMsg());
                        getVideoCallBack.loadSucess(hotVideoBean);
                    }

                    @Override
                    public void onError(Throwable e) {
getVideoCallBack.loadFail(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
}
