package zhanghegang.com.bawei.onetime.presenter;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

import zhanghegang.com.bawei.onetime.base.BaseCallBack;
import zhanghegang.com.bawei.onetime.base.BasePresenter;
import zhanghegang.com.bawei.onetime.model.RegModel;
import zhanghegang.com.bawei.onetime.view.RegView;

/**
 * current package:zhanghegang.com.bawei.onetime.presenter
 * Created by Mr.zhang
 * date: 2017/11/29
 * decription:开发
 */

public class RegPresenter extends BasePresenter<RegView> {

    private final RegModel regModel;

    public RegPresenter(RegView mView) {
        super(mView);
        regModel = new RegModel();
    }


    public void getRegInfo(String regType,String mobile,String password){
        Map<String,String> map=new HashMap<>();
        map.put("regType",regType);
        if(TextUtils.isEmpty(mobile))
        {
            mView.mobileError("手机号为空");
            return;
        }
        else {
            if(mobile.length()<11||mobile.length()>11) {
                mView.mobileError("手机号不合法");
                return;
            }
            map.put("mobile",mobile);

        }
        if (TextUtils.isEmpty(password)){
            mView.passwordError("密码不能为空");
        }
        else {
            if(password.length()<6)
            {
                mView.passwordError("密码不能少于6位");
                return;
            }
            else {
                map.put("password",password);
            }
        }
        regModel.getRegInfo(map, new BaseCallBack() {
            @Override
            public void loadSucess(Object data) {
                System.out.println("data=========="+data);
                if(data!=null)
                {
                    mView.sucReg(data);
                }
            }

            @Override
            public void loadFail(String msg) {
mView.failure(msg);
            }
        });

    }
    public void getOtherRegInfo(String regType,String userId,String gender,String nickname,String icon){
        Map<String,String> map=new HashMap<>();
        map.put("regType",regType);
        if(TextUtils.isEmpty(userId))
        {
            mView.mobileError("信息不全");
            return;
        }
        else {

            map.put("userId",userId);

        }

        if(TextUtils.isEmpty(gender))
        {
            mView.mobileError("信息不全");
            return;
        }
        else {

            map.put("gender",gender);

        }

        if(TextUtils.isEmpty(nickname))
        {
            mView.mobileError("信息不全");
            return;
        }
        else {

            map.put("nickname",nickname);

        }
        if(TextUtils.isEmpty(icon))
        {
            mView.mobileError("信息不全");
            return;
        }
        else {

            map.put("icon",icon);

        }

        regModel.getRegInfo(map, new BaseCallBack() {
            @Override
            public void loadSucess(Object data) {
                System.out.println("data=========="+data);
                if(data!=null)
                {
                    mView.sucReg(data);
                }
            }

            @Override
            public void loadFail(String msg) {
                mView.failure(msg);
            }
        });
    }
}
