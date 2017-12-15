package zhanghegang.com.bawei.onetime.presenter;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import zhanghegang.com.bawei.onetime.base.BaseCallBackShowLodding;
import zhanghegang.com.bawei.onetime.base.BasePresenter;
import zhanghegang.com.bawei.onetime.model.UserInterfaceModel;
import zhanghegang.com.bawei.onetime.view.UserInterfaceView;

/**
 * current package:zhanghegang.com.bawei.onetime.presenter
 * Created by Mr.zhang
 * date: 2017/12/14
 * decription:开发
 */

public class UserInterfacePresenter extends BasePresenter<UserInterfaceView> {
    @Inject
    UserInterfaceModel userInterfaceModel;
//    UserInterfaceView userInterfaceView;
    @Inject
    public UserInterfacePresenter(UserInterfaceView mView) {
        super(mView);
       this.mView=mView;
    }
    public void getUserIntefaceInfo(String uid,String page){
        Map<String,String> map=new HashMap<>();
        userInterfaceModel.getUserInterfaceInfo(map, new BaseCallBackShowLodding() {
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
