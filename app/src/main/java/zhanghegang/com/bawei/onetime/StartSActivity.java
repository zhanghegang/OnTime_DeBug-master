package zhanghegang.com.bawei.onetime;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhanghegang.com.bawei.onetime.base.BaseActivity;
import zhanghegang.com.bawei.onetime.presenter.LoginPresenter;
import zhanghegang.com.bawei.onetime.utils.SharePrefrenceBack;
import zhanghegang.com.bawei.onetime.utils.SharePrefrenceUtils;

public class StartSActivity extends BaseActivity {
    @BindView(R.id.iv_right1)
    ImageView ivRight1;
    @BindView(R.id.iv_right2)
    ImageView ivRight2;
    @BindView(R.id.iv_right3)
    ImageView ivRight3;
    @BindView(R.id.ll_right_jiantou)
    LinearLayout llRightJiantou;
    private ViewPager vp;
    public int[] imgs = {R.drawable.start4, R.drawable.start1, R.drawable.start5};
    private LinearLayout ll_reg;
    private ImageView iv_logo;
    private AnimatorSet animatorSet;


    private void initData() {
        vp.setAdapter(new VpAdapter());
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == imgs.length - 1) {
                    animatorSet.cancel();
                    ll_reg.setVisibility(View.VISIBLE);
llRightJiantou.setVisibility(View.GONE);

                } else {
                    animatorSet.start();
                    ll_reg.setVisibility(View.GONE);
                    llRightJiantou.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initLayout() {

        vp = findViewById(R.id.vp_start);
        ll_reg = findViewById(R.id.ll_reg);

        ObjectAnimator iv1 = ObjectAnimator.ofFloat(ivRight1, "alpha", 0f, 1f);
        ObjectAnimator iv2 = ObjectAnimator.ofFloat(ivRight2, "alpha", 0f, 1f);
        ObjectAnimator iv3 = ObjectAnimator.ofFloat(ivRight3, "alpha", 0f, 1f);
//        iv1.setRepeatCount(3);
//        iv2.setRepeatCount(3);
//        iv3.setRepeatCount(3);

        animatorSet = new AnimatorSet();
        animatorSet.play(iv2).after(iv1).before(iv3);

        animatorSet.setDuration(500);

        animatorSet.start();
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
animatorSet.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });



    }

    @Override
    public LoginPresenter initPresenter() {

        return null;
    }

    @Override
    public int getLayoutId() {

        return R.layout.activity_start_s;
    }

    @Override
    public void initView() {

        Boolean regin = (Boolean) SharePrefrenceUtils.getData(SharePrefrenceBack.Boolean, "regin");
        System.out.println("=boolean=============" + regin);
        if (regin) {
            startActivity(new Intent(StartSActivity.this, LeadActivity.class));
            finish();
        } else {
            initLayout();
            initData();
        }
    }

    @Override
    public void ionDestroy() {
        if(animatorSet!=null&&animatorSet.isRunning())
        {
            animatorSet.cancel();
        }
        ll_reg = null;
    }

    public void regin(View view) {
        boolean regin = SharePrefrenceUtils.putData("regin", true);
        startActivity(new Intent(StartSActivity.this, RegActivity.class));
        finish();
    }

    public void reginWalker(View view) {
        boolean regin = SharePrefrenceUtils.putData("regin", true);
        startActivity(new Intent(StartSActivity.this, MainActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("===========stars");
    }



    class VpAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return imgs.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View inflate = View.inflate(StartSActivity.this, R.layout.vp_item, null);
            ImageView iv = inflate.findViewById(R.id.iv_start);
            iv.setImageResource(imgs[position]);
            container.addView(inflate);
            return inflate;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
