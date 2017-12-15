package zhanghegang.com.bawei.onetime.model;


import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import zhanghegang.com.bawei.onetime.base.BaseCallBackShowLodding;
import zhanghegang.com.bawei.onetime.bean.HotVideoBean;
import zhanghegang.com.bawei.onetime.bean.UserInterfaceBean;
import zhanghegang.com.bawei.onetime.bean.VerSionUpdateBean;
import zhanghegang.com.bawei.onetime.utils.RetrofitUtils;
import zhanghegang.com.bawei.onetime.utils.UrlData;

/**
 * current package:zhanghegang.com.bawei.onetime.model
 * Created by Mr.zhang
 * date: 2017/11/12
 * decription:开发
 */

public class UserInterfaceModel {
    @Inject
    public UserInterfaceModel() {

    }

    private DisposableSubscriber<UserInterfaceBean> flowableSubscriber;

    public void getUserInterfaceInfo(Map<String,String> map,final BaseCallBackShowLodding getUserInterfaceInfo) {

        UrlData urlData = new RetrofitUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).showLoading(getUserInterfaceInfo)
                .build().getUrlData();
        flowableSubscriber = urlData.getUserInterface(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<UserInterfaceBean>() {
                    @Override
                    public void onNext(UserInterfaceBean userInterfaceBean) {
                    getUserInterfaceInfo.loadSucess(userInterfaceBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        getUserInterfaceInfo.loadFail(t.toString());
                        getUserInterfaceInfo.shutDownLodding();

                    }

                    @Override
                    public void onComplete() {
                    getUserInterfaceInfo.shutDownLodding();
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
