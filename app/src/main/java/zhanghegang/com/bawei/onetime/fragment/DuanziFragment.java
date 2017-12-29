package zhanghegang.com.bawei.onetime.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import zhanghegang.com.bawei.onetime.R;
import zhanghegang.com.bawei.onetime.adapter.StatinAdapter;
import zhanghegang.com.bawei.onetime.base.BaseFragment;
import zhanghegang.com.bawei.onetime.bean.StatinBean;
import zhanghegang.com.bawei.onetime.presenter.StatinPresenter;
import zhanghegang.com.bawei.onetime.utils.MyItemDecoration;
import zhanghegang.com.bawei.onetime.view.SatinView;

/**
 * current package:zhanghegang.com.bawei.onetime.fragment
 * Created by Mr.zhang
 * date: 2017/11/23
 * decription:开发
 */

public class DuanziFragment extends BaseFragment<StatinPresenter> implements SatinView, XRecyclerView.LoadingListener {


    @BindView(R.id.iv_reload)
    ImageView ivReload;

    private int page = 1;
    @BindView(R.id.rcv_duanzi)
    XRecyclerView rcvDuanzi;
    @BindView(R.id.ll_reload)
    LinearLayout ll_reload;
    private StatinPresenter statinPresenter;
    private StatinAdapter statinAdapter;
    private List<StatinBean.DataBean> dataList;
    private Map<Integer, Boolean> map;
    private int startIndex = 0;

    @Override
    public StatinPresenter initPresenter() {
        statinPresenter = new StatinPresenter(this);
        return statinPresenter;
    }

    @Override
    public int getViewId() {
        return R.layout.fragment_duanzi;
    }

    @Override
    public void initFragment() {
        map = new HashMap<>();
        dataList = new ArrayList<>();
        rcvDuanzi.addItemDecoration(new MyItemDecoration(getActivity()));
        rcvDuanzi.setLoadingListener(this);
        rcvDuanzi.setLoadingMoreEnabled(true);
        rcvDuanzi.setPullRefreshEnabled(true);

    }

    @Override
    public void resumFragment() {

        statinPresenter.getStatinInfo("1");
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hidLoading() {

    }

    @Override
    public void failure(String msg) {
        System.out.println("======failure=====list_statin============");
        showToast(msg);
        rcvDuanzi.setVisibility(View.GONE);
        ll_reload.setVisibility(View.VISIBLE);
    }

    @Override
    public void statinSuc(Object data) {
        String code = ((StatinBean) data).getCode();
        String msg = ((StatinBean) data).getMsg();
//        System.out.println(dataList.size()+"size==========="+code+((StatinBean) data).getData().get(0).getContent());
        List<StatinBean.DataBean> list_statin = ((StatinBean) data).getData();
        rcvDuanzi.setVisibility(View.VISIBLE);
        ll_reload.setVisibility(View.GONE);
        if (list_statin == null||list_statin.size()==0) {
            System.out.println("===========list_statin============");
            rcvDuanzi.setVisibility(View.GONE);
            ll_reload.setVisibility(View.VISIBLE);
            return;
        }
        this.dataList.addAll(((StatinBean) data).getData());

        for (int i = startIndex; i < this.dataList.size() + startIndex; i++) {
            map.put(i, false);
        }
        startIndex += this.dataList.size() - 1;
//        System.out.println("startIndex========"+startIndex+"datalist==========:"+dataList.size());
        if (statinAdapter == null) {
            if (this.dataList != null) {
                statinAdapter = new StatinAdapter(getContext(), this.dataList, map);
                rcvDuanzi.setLayoutManager(new LinearLayoutManager(getActivity()));
                rcvDuanzi.setAdapter(statinAdapter);
            }

        } else {
            statinAdapter.notifyDataSetChanged();
        }
        rcvDuanzi.refreshComplete();
        rcvDuanzi.loadMoreComplete();
    }


    @Override
    public void onRefresh() {
        page = 1;
        startIndex = 0;
        dataList.clear();
        statinPresenter.getStatinInfo(page + "");


    }

    @Override
    public void onLoadMore() {
        page++;
        statinPresenter.getStatinInfo(page + "");

    }




    @OnClick(R.id.iv_reload)
    public void onViewClicked() {
        statinPresenter.getStatinInfo("1");
    }
}
