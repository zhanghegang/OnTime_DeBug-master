package zhanghegang.com.bawei.onetime.presenter;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

import zhanghegang.com.bawei.onetime.base.BasePresenter;
import zhanghegang.com.bawei.onetime.model.LoginCallBack;
import zhanghegang.com.bawei.onetime.model.LoginModel;
import zhanghegang.com.bawei.onetime.view.LoginView;

/**
 * current package:zhanghegang.com.bawei.onetime.presenter
 * Created by Mr.zhang
 * date: 2017/11/12
 * decription:开发
 */

public class LoginPresenter extends BasePresenter<LoginView> {

    private final LoginModel loginModel;

    public LoginPresenter(LoginView mView) {
        super(mView);

        loginModel = new LoginModel();

    }
    public void login(String mobile,String password){

        Map<String,Object> map=new HashMap<>();
        if(TextUtils.isEmpty(mobile))
        {
            mView.mobileError();
            return;
        }
     if(TextUtils.isEmpty(password))
     {
         mView.passwordError();
         return;
     }
        map.put("mobile",mobile);
        map.put("password",password);
        loginModel.load(map, new LoginCallBack() {
            @Override
            public void loadSucess(Object data) {
                if(data!=null)
                {
                    mView.gainSucess(data);
                }

            }

            @Override
            public void loadFail(String msg) {
mView.failure(msg);
            }
        });

    }

}
