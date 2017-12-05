package zhanghegang.com.bawei.onetime.model;

import com.google.gson.Gson;

import org.json.JSONObject;

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
import zhanghegang.com.bawei.onetime.bean.VideosBean;
import zhanghegang.com.bawei.onetime.utils.RetrofitUtils;
import zhanghegang.com.bawei.onetime.utils.UrlData;

/**
 * current package:zhanghegang.com.bawei.onetime.model
 * Created by Mr.zhang
 * date: 2017/11/12
 * decription:开发
 */

public class TuijianModel {

    public void getHot_look(Map<String,String> map, final BaseCallBack gethot_lookCallback) {

        UrlData urlData = new RetrofitUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().getUrlData();
        urlData.getHot_look(map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VideosBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VideosBean videosBean) {
if("0".equals(videosBean.getCode()))
{
    gethot_lookCallback.loadSucess(videosBean);
}
else {
    gethot_lookCallback.loadFail("获取失败");
}
                    }

                    @Override
                    public void onError(Throwable e) {
gethot_lookCallback.loadFail(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });



    }


}
