package zhanghegang.com.bawei.onetime.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zhanghegang.com.bawei.onetime.R;
import zhanghegang.com.bawei.onetime.base.BaseFragment;
import zhanghegang.com.bawei.onetime.base.BasePresenter;

/**
 * current package:zhanghegang.com.bawei.onetime.fragment
 * Created by Mr.zhang
 * date: 2017/11/23
 * decription:开发
 */

public class VedioFragment extends BaseFragment {


    @BindView(R.id.tab_video)
    TabLayout tabVideo;
    @BindView(R.id.fl_video)
    FrameLayout flVideo;


    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getViewId() {
        return R.layout.fragment_vedio;
    }
    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

    @Override
    public void initFragment() {
tabVideo.addTab(tabVideo.newTab().setText("热门"));
  tabVideo.addTab(tabVideo.newTab().setText("附近"));
        //修改宽度
        tabVideo.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(tabVideo,50,50);
            }
        });
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_video,new Video_Hot_Fragment()).commit();
  tabVideo.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override
      public void onTabSelected(TabLayout.Tab tab) {
          int position = tab.getPosition();
if(position==0)
{
    System.out.println("position============="+position);
    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_video,new Video_Hot_Fragment()).commit();
}
else if(position==1)
{
    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_video,new Video_Near_Fragment()).commit();
}
      }

      @Override
      public void onTabUnselected(TabLayout.Tab tab) {

      }

      @Override
      public void onTabReselected(TabLayout.Tab tab) {

      }
  });
    }

    @Override
    public void resumFragment() {

    }


}
