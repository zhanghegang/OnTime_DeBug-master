package zhanghegang.com.bawei.onetime.model;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import zhanghegang.com.bawei.onetime.bean.BaseReg;
import zhanghegang.com.bawei.onetime.bean.RegBean;
import zhanghegang.com.bawei.onetime.utils.RetrofitUtils;
import zhanghegang.com.bawei.onetime.utils.UrlData;

/**
 * current package:zhanghegang.com.bawei.onetime.model
 * Created by Mr.zhang
 * date: 2017/11/12
 * decription:开发
 */

public class LoginModel implements ILoginModel {
    @Override
    public void load(Map<String, Object> objectMap, final LoginCallBack loginCallBack) {
        System.out.println("登录1111111111");
        UrlData urlData = new RetrofitUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().getUrlData();
        urlData.getReg(objectMap).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<BaseReg<RegBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseReg<RegBean> regBeanBaseReg) {
                System.out.println("登录2222222");
                if("0".equals(regBeanBaseReg.code))
                {
                loginCallBack.loadSucess(regBeanBaseReg);
                }
                else{
                    loginCallBack.loadFail("登录异常");
                }
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("登录33333333");
loginCallBack.loadFail(e.toString());
            }

            @Override
            public void onComplete() {

            }
        })
        ;

    }
}
