package zhanghegang.com.bawei.onetime.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zhanghegang.com.bawei.onetime.R;
import zhanghegang.com.bawei.onetime.adapter.StatinSendIMgeAdapter;
import zhanghegang.com.bawei.onetime.adapter.TuijianHot_LookAdapter;
import zhanghegang.com.bawei.onetime.base.BaseFragment;
import zhanghegang.com.bawei.onetime.bean.BanderBean;
import zhanghegang.com.bawei.onetime.bean.VideosBean;
import zhanghegang.com.bawei.onetime.presenter.HotPresenter;
import zhanghegang.com.bawei.onetime.presenter.TuijianPresenter;
import zhanghegang.com.bawei.onetime.view.HotFragmentView;
import zhanghegang.com.bawei.onetime.view.HotVIew;

/**
 * current package:zhanghegang.com.bawei.onetime.fragment
 * Created by Mr.zhang
 * date: 2017/11/23
 * decription:开发
 */

public class Tuijian_Hot_Fragment extends BaseFragment<HotPresenter> implements HotFragmentView, XRecyclerView.LoadingListener, HotVIew {


    @BindView(R.id.xrcv_hot)
    XRecyclerView xrcvHot;
    private View view1;
    private XBanner xBanner;
    private List<String> imgUrl;
    private TuijianPresenter tuijianPresenter;
    private Map<Integer, Boolean> map;


    @Override
    public HotPresenter initPresenter() {
        return new HotPresenter(this);
    }

    @Override
    public int getViewId() {
        return R.layout.fragment_tuijian_remen;
    }

    @Override
    public void initFragment() {
        view1 = LayoutInflater.from(getContext()).inflate(R.layout.hothead,null,false);


xrcvHot.setLoadingListener(this);
xrcvHot.setPullRefreshEnabled(true);
xrcvHot.setLoadingMoreEnabled(true);
        xrcvHot.addHeaderView(view1);
        xBanner = view1.findViewById(R.id.hot_xbanner);
        tuijianPresenter = new TuijianPresenter(this);
    }

    @Override
    public void resumFragment() {
      tuijianPresenter.getHot_look(null,"1","1");
        xBanner.startAutoPlay();
        map = new HashMap<>();
        imgUrl = new ArrayList<>();
presenter.getHotInfo();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void onStop() {
        super.onStop();
        if(xBanner!=null)
        {
            xBanner.stopAutoPlay();
        }
    }

    @Override
    public void hidLoading() {

    }

    @Override
    public void failure(String msg) {

    }

    @Override
    public void sucBanner(Object data) {
        List<BanderBean.DataBean> banner = ((BanderBean) data).getData();
        if(banner!=null&&banner.size()>0)
        {
            for (BanderBean.DataBean dataBean : banner) {
                imgUrl.add(dataBean.getIcon());

            }
        }

        xBanner.setData(imgUrl,null);
        xBanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {

                Glide.with(getActivity()).load(imgUrl.get(position)).into((ImageView) view);
            }
        });

    }

    @Override
    public void sucPublishVideo(Object data) {

    }


    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void sucHot(Object data) {
        List<VideosBean.DataBean> list = ((VideosBean) data).getData();
        for (int i = 0; i < list.size(); i++) {
            map.put(i,false);
        }
        xrcvHot.setLayoutManager(new LinearLayoutManager(getContext()));
        xrcvHot.setAdapter(new TuijianHot_LookAdapter(getActivity(),list,map));
    }
}
