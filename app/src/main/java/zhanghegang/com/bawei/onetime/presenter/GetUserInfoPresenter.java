package zhanghegang.com.bawei.onetime.presenter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import zhanghegang.com.bawei.onetime.base.BasePresenter;
import zhanghegang.com.bawei.onetime.bean.UserInfo;
import zhanghegang.com.bawei.onetime.model.GetUserInfoCallBack;
import zhanghegang.com.bawei.onetime.model.GetUserInfoModel;
import zhanghegang.com.bawei.onetime.utils.SharePrefrenceBack;
import zhanghegang.com.bawei.onetime.utils.SharePrefrenceUtils;
import zhanghegang.com.bawei.onetime.view.GetUserInfoView;

/**
 * current package:zhanghegang.com.bawei.onetime.presenter
 * Created by Mr.zhang
 * date: 2017/11/28
 * decription:开发
 */

public class GetUserInfoPresenter extends BasePresenter<GetUserInfoView> {
    private  Context context;
    private GetUserInfoView getUserInfoView;
    private GetUserInfoModel getUserInfoModel;

    public GetUserInfoPresenter(GetUserInfoView mView,Context context) {
        super(mView);
        getUserInfoView=mView;
        this.context=context;
        getUserInfoModel=new GetUserInfoModel();
    }


    public void getUserInfo(){
        System.out.println("code2222=getUserInfo===");
        String uid = (String) SharePrefrenceUtils.getData(SharePrefrenceBack.String, "uid");
        if(!TextUtils.isEmpty(uid))
        {
            getUserInfoModel.getUserInfo(uid, new GetUserInfoCallBack() {
                @Override
                public void loadSucess(final Object data) {
                    ((AppCompatActivity)context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("code2222=loadSucess==="+((UserInfo) data).getCode());
                            if(data!=null) {

                                getUserInfoView.getUserInfoSuc(data);
                            }
                        }
                    });
                }

                @Override
                public void loadFail(final String msg) {
                    System.out.println("==code2222===="+msg);
                    ((AppCompatActivity)context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            getUserInfoView.failure(msg);
                        }
                    });

                }
            });
        }
        else {
            System.out.println("ccccccuidcccc===");
        }
    }
}
