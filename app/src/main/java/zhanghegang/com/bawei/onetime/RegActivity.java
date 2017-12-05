package zhanghegang.com.bawei.onetime;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhanghegang.com.bawei.onetime.base.BaseActivity;
import zhanghegang.com.bawei.onetime.base.BasePresenter;

public class RegActivity extends BaseActivity {

    @BindView(R.id.ll_qq)
    LinearLayout llQq;
    @BindView(R.id.ll_weichat)
    LinearLayout llWeichat;
    @BindView(R.id.tv_othreg)
    TextView tvOreg;
    @BindView(R.id.iv_back_reg)
    ImageView ivBackReg;

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_reg;
    }

    @Override
    public void initView() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(llQq, "alpha", 0f, 1f);
        ObjectAnimator animator_wechat = ObjectAnimator.ofFloat(llWeichat, "alpha", 0f, 1f);
        ObjectAnimator tv = ObjectAnimator.ofFloat(tvOreg, "alpha", 0f, 1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animator).with(animator_wechat).with(tv);
        animatorSet.setDuration(2000);
        animatorSet.start();

    }

    @Override
    public void ionDestroy() {

    }


    @OnClick({R.id.ll_qq, R.id.ll_weichat, R.id.tv_othreg,R.id.iv_back_reg})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.ll_qq:
                Toast.makeText(this, "qq", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_weichat:
                Toast.makeText(this, "wechat", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_othreg:
                start(OtherRegActivity.class, false);
                break;
            case R.id.iv_back_reg:
                finish();
                break;
        }
    }




}
