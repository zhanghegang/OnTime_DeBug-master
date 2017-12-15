package zhanghegang.com.bawei.onetime.presenter;

import javax.inject.Inject;

import zhanghegang.com.bawei.onetime.base.BaseCallBack;
import zhanghegang.com.bawei.onetime.base.BaseCallBackShowLodding;
import zhanghegang.com.bawei.onetime.base.BasePresenter;
import zhanghegang.com.bawei.onetime.model.VersionUpdateModel;
import zhanghegang.com.bawei.onetime.view.VersionUpdateView;

/**
 * current package:zhanghegang.com.bawei.onetime.presenter
 * Created by Mr.zhang
 * date: 2017/12/12
 * decription:开发
 */

public class VerSionUpdatePresenter{
    @Inject
    VersionUpdateModel versionUpdateModel;
VersionUpdateView mView;

    @Inject
    public VerSionUpdatePresenter(VersionUpdateView versionUpdateView) {

this.mView=versionUpdateView;
    }


    public void getVersionUpdateInfo(){


        System.out.println("versionUpdateModelcrosss");
        if (versionUpdateModel==null)
        {
            System.out.println("versionUpdateModelnull");
            return;
        }
        versionUpdateModel.getUserInfo(new BaseCallBackShowLodding() {
            @Override
            public void loadSucess(Object data) {
                if(data!=null)
                {
                    mView.sucData(data);
                }
            }

            @Override
            public void loadFail(String msg) {
mView.failure(msg);
            }

            @Override
            public void showLodding() {
mView.showLoading();
            }

            @Override
            public void shutDownLodding() {
mView.hidLoading();
            }
        });
    }




}
