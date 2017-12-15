package zhanghegang.com.bawei.onetime.model;


import javax.inject.Inject;

import io.reactivex.FlowableSubscriber;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import zhanghegang.com.bawei.onetime.base.BaseCallBack;

import zhanghegang.com.bawei.onetime.base.BaseCallBackShowLodding;
import zhanghegang.com.bawei.onetime.bean.VerSionUpdateBean;
import zhanghegang.com.bawei.onetime.utils.RetrofitUtils;
import zhanghegang.com.bawei.onetime.utils.UrlData;

/**
 * current package:zhanghegang.com.bawei.onetime.model
 * Created by Mr.zhang
 * date: 2017/11/12
 * decription:开发
 */

public class VersionUpdateModel {
    @Inject
    public VersionUpdateModel() {

    }

    private DisposableSubscriber<VerSionUpdateBean> flowableSubscriber;

    public void getUserInfo(final BaseCallBackShowLodding getVersionUpdate) {

        UrlData urlData = new RetrofitUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).showLoading(getVersionUpdate)
                .build().getUrlData();
        flowableSubscriber = urlData.getVerSionUpdate().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<VerSionUpdateBean>() {
                    @Override
                    public void onNext(VerSionUpdateBean verSionUpdateBean) {
                        System.out.println(verSionUpdateBean.getMsg());
                        getVersionUpdate.loadSucess(verSionUpdateBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        getVersionUpdate.shutDownLodding();
getVersionUpdate.loadFail(t.toString());
                    }

                    @Override
                    public void onComplete() {
                        getVersionUpdate.shutDownLodding();
                    }
                });


    }

    /**
     * 解决内存泄漏
     */
    public void deatch()
    {
        if(flowableSubscriber!=null&&flowableSubscriber.isDisposed())
        {
           flowableSubscriber.dispose();
        }
    }


}
