package zhanghegang.com.bawei.onetime.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import zhanghegang.com.bawei.onetime.R;
import zhanghegang.com.bawei.onetime.RegActivity;
import zhanghegang.com.bawei.onetime.base.BaseFragment;
import zhanghegang.com.bawei.onetime.bean.UserInfo;
import zhanghegang.com.bawei.onetime.presenter.GetUserInfoPresenter;
import zhanghegang.com.bawei.onetime.view.GetUserInfoView;

/**
 * current package:zhanghegang.com.bawei.onetime.fragment
 * Created by Mr.zhang
 * date: 2017/11/23
 * decription:开发
 */

public class LeftFragment extends BaseFragment<GetUserInfoPresenter> implements GetUserInfoView {


    @BindView(R.id.iv_left_head)
    RoundedImageView ivLeftHead;
    @BindView(R.id.iv_sex)
    ImageView ivSex;
    @BindView(R.id.tv_qianming)
    TextView tvQianming;
    @BindView(R.id.ll_guanzhu)
    LinearLayout llGuanzhu;
    @BindView(R.id.ll_shoucang)
    LinearLayout llShoucang;
    @BindView(R.id.ll_find)
    LinearLayout llFind;
    @BindView(R.id.ll_msg)
    LinearLayout llMsg;
    @BindView(R.id.iv_night)
    ImageView ivNight;
    @BindView(R.id.sw)
    Switch sw;
    @BindView(R.id.ll_worker)
    LinearLayout llWorker;
    @BindView(R.id.ll_setting)
    LinearLayout llSetting;
    @BindView(R.id.tv_left_name)
    TextView tvLeftName;


    private GetUserInfoPresenter getUserInfoPresenter;

    @Override
    public GetUserInfoPresenter initPresenter() {
        getUserInfoPresenter = new GetUserInfoPresenter(this, getContext());
        return getUserInfoPresenter;
    }

    @Override
    public int getViewId() {

        return R.layout.left_fragment;
    }


    @Override
    public void initFragment() {

sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
       if(isChecked)
       {
           ivNight.setImageResource(R.drawable.night_select);
       }
       else {
           ivNight.setImageResource(R.drawable.night);
       }
    }
});
    }


    @Override
    public void resumFragment() {
        getUserInfoPresenter.getUserInfo();
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hidLoading() {

    }

    @Override
    public void failure(String msg) {

    }

    @Override
    public void getUserInfoSuc(Object data) {
        String code = ((UserInfo) data).getCode();

        if("0".equals(code)) {
            UserInfo.DataBean userInfo = ((UserInfo) data).getData();
            String nickname = userInfo.getNickname();
            String icon = userInfo.getIcon();
            Glide.with(getContext()).load(icon).skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE).into(ivLeftHead);
            tvLeftName.setText(nickname + "");
        }
        else {
            return;
        }

    }


    @OnClick({R.id.iv_left_head, R.id.ll_guanzhu, R.id.ll_shoucang, R.id.ll_find, R.id.ll_msg, R.id.sw, R.id.ll_worker, R.id.ll_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left_head:
                start(RegActivity.class,false);
                break;
            case R.id.ll_guanzhu:
                break;
            case R.id.ll_shoucang:
                break;
            case R.id.ll_find:
                break;
            case R.id.ll_msg:
                break;
            case R.id.sw:
                break;
            case R.id.ll_worker:
                break;
            case R.id.ll_setting:
                break;
        }
    }


}
