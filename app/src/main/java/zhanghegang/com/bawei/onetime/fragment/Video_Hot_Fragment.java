package zhanghegang.com.bawei.onetime.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zhanghegang.com.bawei.onetime.R;
import zhanghegang.com.bawei.onetime.adapter.Video_hot_Adapter;
import zhanghegang.com.bawei.onetime.base.BaseFragment;
import zhanghegang.com.bawei.onetime.base.BasePresenter;
import zhanghegang.com.bawei.onetime.bean.HotVideoBean;
import zhanghegang.com.bawei.onetime.presenter.HotVideoPresenter;
import zhanghegang.com.bawei.onetime.view.VideoHotView;

/**
 * current package:zhanghegang.com.bawei.onetime.fragment
 * Created by Mr.zhang
 * date: 2017/12/7
 * decription:开发
 */

public class Video_Hot_Fragment extends BaseFragment<HotVideoPresenter> implements VideoHotView, XRecyclerView.LoadingListener {

    @BindView(R.id.rcv_video_hot)
    XRecyclerView rcvVideoHot;
private int page=1;
    private List<HotVideoBean.DataBean> list;
    private Video_hot_Adapter video_hot_adapter;

    @Override
    public HotVideoPresenter initPresenter() {
        return new HotVideoPresenter(this);
    }

    @Override
    public int getViewId() {
        return R.layout.fragment_video_hot;
    }

    @Override
    public void initFragment() {
        List<Integer> list=new ArrayList<>();
rcvVideoHot.setLoadingMoreEnabled(true);
rcvVideoHot.setPullRefreshEnabled(true);
rcvVideoHot.setLoadingListener(this);

    }

    @Override
    public void resumFragment() {
        list=new ArrayList<>();
presenter.getHotVideoInfo(page+"");
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
    public void sucData(Object data) {
        String code = ((HotVideoBean) data).getCode();
        if("0".equals(code))
        {
            list .addAll(((HotVideoBean) data).getData());
            if(video_hot_adapter==null) {
                rcvVideoHot.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                video_hot_adapter = new Video_hot_Adapter(getActivity(), list);
                rcvVideoHot.setAdapter(video_hot_adapter);
            }
            else {
                video_hot_adapter.setHeight(list);
                video_hot_adapter.notifyDataSetChanged();
            }


        }
        else {
            System.out.println("sucData22222222"+code);
        }
        rcvVideoHot.loadMoreComplete();
        rcvVideoHot.refreshComplete();

    }

    @Override
    public void onRefresh() {
        page=1;
        list.clear();
        presenter.getHotVideoInfo(page+"");

    }

    @Override
    public void onLoadMore() {
page++;
presenter.getHotVideoInfo(page+"");
    }
}
