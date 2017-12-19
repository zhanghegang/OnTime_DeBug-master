package zhanghegang.com.bawei.onetime.presenter;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import zhanghegang.com.bawei.onetime.base.BaseCallBackShowLodding;
import zhanghegang.com.bawei.onetime.base.BasePresenter;
import zhanghegang.com.bawei.onetime.model.AttentionInfoModel;
import zhanghegang.com.bawei.onetime.utils.SharePrefrenceBack;
import zhanghegang.com.bawei.onetime.utils.SharePrefrenceUtils;
import zhanghegang.com.bawei.onetime.view.AttentionView;

/**
 * current package:zhanghegang.com.bawei.onetime.presenter
 * Created by Mr.zhang
 * date: 2017/12/18
 * decription:开发
 */

public class AttentionInfoPresenter extends BasePresenter<AttentionView> {
    @Inject
    AttentionInfoModel attentionInfoModel;
    @Inject
    public AttentionInfoPresenter(AttentionView mView) {
        super(mView);
        this.mView=mView;
    }
    private void getAttentionInfo(String followid){
        String uid= (String) SharePrefrenceUtils.getData(SharePrefrenceBack.String, "uid");
        String token = (String) SharePrefrenceUtils.getData(SharePrefrenceBack.String, "token");
        if(TextUtils.isEmpty(token))
        {
            mView.failure("token值为空");
            System.out.println("token值为空");
            return;
        }
        if(TextUtils.isEmpty(uid))
        {
            mView.failure("uid为空");
            System.out.println("uid值为空");
            return;
        }
        Map<String,String> map=new HashMap<>();
        map.put("uid",uid);
        map.put("followid",followid);
        map.put("token",token);
        attentionInfoModel.getUserInfo(map, new BaseCallBackShowLodding() {
            @Override
            public void loadSucess(Object data) {
                if(data!=null)
                {
mView.sucAttentionInfo(data);
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
