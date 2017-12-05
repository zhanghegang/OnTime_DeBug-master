package zhanghegang.com.bawei.onetime;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kson.slidingmenu.SlidingMenu;
import com.tencent.bugly.crashreport.biz.UserInfoBean;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhanghegang.com.bawei.onetime.autoview.NoDrawLayout;
import zhanghegang.com.bawei.onetime.autoview.UpdateTitleView;
import zhanghegang.com.bawei.onetime.base.BaseActivity;
import zhanghegang.com.bawei.onetime.base.BasePresenter;
import zhanghegang.com.bawei.onetime.bean.UserInfo;
import zhanghegang.com.bawei.onetime.fragment.DuanziFragment;
import zhanghegang.com.bawei.onetime.fragment.LeftFragment;
import zhanghegang.com.bawei.onetime.fragment.TuijianFragment;
import zhanghegang.com.bawei.onetime.fragment.VedioFragment;
import zhanghegang.com.bawei.onetime.presenter.GetUserInfoPresenter;
import zhanghegang.com.bawei.onetime.view.GetUserInfoView;

public class MainActivity extends BaseActivity<GetUserInfoPresenter> implements UpdateTitleView.OnClickImage, GetUserInfoView {


    @BindView(R.id.update_title)
    UpdateTitleView updateTitle;
    @BindView(R.id.fl_main)
    FrameLayout flMain;
    @BindView(R.id.tv_tuijian)
    TextView tvTuijian;
    @BindView(R.id.ll_tuijian)
    LinearLayout llTuijian;
    @BindView(R.id.tv_dianzi)
    TextView tvDianzi;
    @BindView(R.id.ll_duanzi)
    LinearLayout llDuanzi;
    @BindView(R.id.tv_vedio)
    TextView tvVedio;
    @BindView(R.id.ll_video)
    LinearLayout llVideo;
    @BindView(R.id.iv_tuijian)
    ImageView ivTuijian;
    @BindView(R.id.iv_duanzi)
    ImageView ivDuanzi;
    @BindView(R.id.iv_vedio)
    ImageView ivVedio;
//    @BindView(R.id.fl_left)
//    FrameLayout flLeft;
//    @BindView(R.id.dl_pager)
//    NoDrawLayout dlPager;

    private SlidingMenu slidingMenu;
    private GetUserInfoPresenter getUserInfoPresenter;


    @Override
    public GetUserInfoPresenter initPresenter() {
        getUserInfoPresenter = new GetUserInfoPresenter(this,this);
        return getUserInfoPresenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {


        //设置状态栏颜色

        setStatus(getResources().getColor(R.color.main_head));

        updateTitle.setOnClickImage(this);
        TuijianFragment tuijianFragment = new TuijianFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_main, tuijianFragment).commit();
        setBottom(1);
        setLeft();
//        SkinCompat.setSkinStyle(this, SkinStyle.Light, new SkinCompat.SkinStyleChangeListener() {
//            @Override
//            public void beforeChange() {
//                System.out.println("======beforeChange=====night=========");
//            }
//
//            @Override
//            public void afterChange() {
//                System.out.println("=====afterChange======night=========");
//
//            }
//        });

    }
    boolean flag=false;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(slidingMenu!=null&&slidingMenu.isMenuShowing())
        {
            slidingMenu.toggle();
        }
        else {

            if(flag==false)
            {
                flag=true;
                showToast( "再次点击.退出一刻钟");
                Timer timer=new Timer();
                TimerTask timerTask=new TimerTask() {
                    @Override
                    public void run() {
                        flag=false;
                    }
                };
                timer.schedule(timerTask,2000);
            }
            else {
                finish();
            }


        }
        return false;
    }


    @Override
    protected void onResume() {
        super.onResume();
presenter.getUserInfo();
    }

    private void setLeft() {
        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMenu(R.layout.left_sliding);

       LeftFragment leftFragment= new LeftFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_slding_left,leftFragment).commit();

        slidingMenu.setMode(SlidingMenu.LEFT);
        //设置边缘滑动
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        //设置滑动后主布局剩余宽度
        slidingMenu.setBehindOffsetRes(R.dimen.margin);
        slidingMenu.setBehindScrollScale(0);

        slidingMenu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);

    }


    @Override
    public void ionDestroy() {

    }


    @OnClick({R.id.ll_tuijian, R.id.ll_duanzi, R.id.ll_video})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_tuijian:

                TuijianFragment tuijianFragment = new TuijianFragment();

                getSupportFragmentManager().beginTransaction().replace(R.id.fl_main, tuijianFragment).commit();
                setBottom(1);

                break;
            case R.id.ll_duanzi:

                DuanziFragment duanziFragment = new DuanziFragment();


                getSupportFragmentManager().beginTransaction().replace(R.id.fl_main, duanziFragment).commit();
                setBottom(2);
                break;
            case R.id.ll_video:

                MyApp.updateNightMode(true);
                VedioFragment vedioFragment = new VedioFragment();

                getSupportFragmentManager().beginTransaction().replace(R.id.fl_main, vedioFragment).commit();
                setBottom(3);
                break;
        }
    }

    public void setBottom(int flag) {

        ivTuijian.setImageResource(R.drawable.tuijian_normal);
        tvTuijian.setTextColor(Color.BLACK);
        ivDuanzi.setImageResource(R.drawable.duanzi_normal);
        tvDianzi.setTextColor(Color.BLACK);
        ivVedio.setImageResource(R.drawable.video_normal);
        tvVedio.setTextColor(Color.BLACK);
        int color = getResources().getColor(R.color.main_head);
        if (flag == 1) {
            updateTitle.setTitle("推荐");
            ivTuijian.setImageResource(R.drawable.tuijian_select);

            tvTuijian.setTextColor(color);
        } else if (flag == 2) {
            updateTitle.setTitle("段子");
            ivDuanzi.setImageResource(R.drawable.duanzi_select);
            tvDianzi.setTextColor(color);
        } else if (flag == 3) {
            updateTitle.setTitle("视频");
            ivVedio.setImageResource(R.drawable.video_select);
            tvVedio.setTextColor(color);
        }


    }

    @Override
    public void userHeadImage() {
        if(slidingMenu!=null)
        {
        if(slidingMenu.isMenuShowing())
        {
            slidingMenu.toggle();
        }
        else {
            slidingMenu.showMenu();
        }
        }

/*if(dlPager.isDrawerOpen(flLeft))
{
    dlPager.closeDrawer(flLeft);
}
else {
    dlPager.openDrawer(flLeft);
}*/
    }

    @Override
    public void writerImage() {
start(WriterStatinActivity.class,false);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hidLoading() {

    }

    @Override
    public void failure(String msg) {
showToast(msg);
    }

    @Override
    public void getUserInfoSuc(Object data) {

//        String code = ((UserInfo) data).getCode();
//        System.out.println("code======"+code);
//        if("2".equals(code))
//        {
//            start(RegActivity.class,true);
//            return;
//        }
        UserInfo.DataBean userInfo = ((UserInfo) data).getData();
        String icon = userInfo.getIcon();
        updateTitle.setheadImage(this,icon);
    }
}
