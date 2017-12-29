package zhanghegang.com.bawei.onetime.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zhanghegang.com.bawei.onetime.R;
import zhanghegang.com.bawei.onetime.base.BaseFragment;
import zhanghegang.com.bawei.onetime.base.BasePresenter;

/**
 * current package:zhanghegang.com.bawei.onetime.fragment
 * Created by Mr.zhang
 * date: 2017/12/21
 * decription:开发
 */

public class FindFragment extends BaseFragment {
    @BindView(R.id.tab_find)
    TabLayout tabFind;


    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getViewId() {
        return R.layout.find_fragment;
    }

    @Override
    public void initFragment() {
        tabFind.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
               switch (tab.getPosition())
               {
                   case 0:
                       showToast("会话");
                       break;
                   case 1:
                       showToast("通讯录");
                       break;
                   case 2:
                       showToast("设置");
                       break;
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
