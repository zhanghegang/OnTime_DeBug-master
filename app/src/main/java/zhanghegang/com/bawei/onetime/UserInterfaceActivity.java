package zhanghegang.com.bawei.onetime;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tencent.bugly.crashreport.biz.UserInfoBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhanghegang.com.bawei.onetime.adapter.TuijianHot_LookAdapter;
import zhanghegang.com.bawei.onetime.adapter.UserInterfaceAdapter;
import zhanghegang.com.bawei.onetime.base.BaseActivity;
import zhanghegang.com.bawei.onetime.base.BasePresenter;
import zhanghegang.com.bawei.onetime.bean.HotVideoBean;
import zhanghegang.com.bawei.onetime.bean.UserInterfaceBean;
//import zhanghegang.com.bawei.onetime.component.DaggerUserInterfaceComponent;
import zhanghegang.com.bawei.onetime.component.DaggerUserInterfaceComponent;
import zhanghegang.com.bawei.onetime.component.UserInterfaceComponent;
import zhanghegang.com.bawei.onetime.moudles.UserInterfaceMoudle;
import zhanghegang.com.bawei.onetime.presenter.UserInterfacePresenter;
import zhanghegang.com.bawei.onetime.utils.DialogUtils;
import zhanghegang.com.bawei.onetime.utils.SharePrefrenceBack;
import zhanghegang.com.bawei.onetime.utils.SharePrefrenceUtils;
import zhanghegang.com.bawei.onetime.view.UserInterfaceView;

public class UserInterfaceActivity extends BaseActivity implements UserInterfaceView, XRecyclerView.LoadingListener, View.OnClickListener {
    @Inject
    UserInterfacePresenter userInterfacePresenter;
//    @BindView(R.id.user_back)
    ImageView userBack;
//    @BindView(R.id.user_interface_name)
    TextView userInterfaceName;
//    @BindView(R.id.user_share)
    ImageView userShare;
//    @BindView(R.id.user_msg)
    ImageView userMsg;
//    @BindView(R.id.iv_user_interface_img)
    RoundedImageView ivUserInterfaceImg;
//    @BindView(R.id.tv_fans)
    TextView tvFans;
//    @BindView(R.id.tv_attention)
    TextView tvAttention;
//    @BindView(R.id.tv_user_attention)
    TextView tvUserAttention;
//    @BindView(R.id.iv_user_praise)
    ImageView ivUserPraise;
//    @BindView(R.id.tv_praise_num)
    TextView tvPraiseNum;
//   @BindView(R.id.tab_attention)
    TabLayout tabAttention;
    @BindView(R.id.xrcv_user_interface)
    XRecyclerView xrcvUserInterface;
private int page=1;
    private List<UserInterfaceBean.DataBean> userList;
    private Map<Integer, Boolean> map;
    private UserInterfaceAdapter adapter;
    private String userid;
    private String pUid="";
    private View view;
    private String uid;

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {

//        ButterKnife.bind(this, view);
        return R.layout.activity_user_interface;
    }

    @Override
    public void initView() {

        setStatus(getResources().getColor(R.color.main_head));
        xrcvUserInterface=findViewById(R.id.xrcv_user_interface);
        UserInterfaceComponent build = DaggerUserInterfaceComponent.builder().userInterfaceMoudle(new UserInterfaceMoudle(this)).build();

        build.inject2(this);


        //添加头部
        view = LayoutInflater.from(this).inflate(R.layout.user_interface_head,null);

        init();
        xrcvUserInterface.addHeaderView(view);

        xrcvUserInterface.setPullRefreshEnabled(true);
        xrcvUserInterface.setLoadingMoreEnabled(true);
        xrcvUserInterface.setLoadingListener(this);
        xrcvUserInterface.setLayoutManager(new LinearLayoutManager(this));
        userList = new ArrayList<>();
        map = new HashMap<>();
        uid = EventBus.getDefault().getStickyEvent(String.class);
        System.out.println("uid=============="+ uid);
        if(!TextUtils.isEmpty(uid))
        {

            pUid = uid;
            userInterfacePresenter.getUserIntefaceInfo(uid,page+"");
        }
        else {
            showToast("用户不存在");
        }
//        userInterfacePresenter.getUserIntefaceInfo("88","1");

    }

    private void init() {
        userBack=view.findViewById(R.id.user_back);
        userShare=view.findViewById(R.id.user_share);
        userMsg=view.findViewById(R.id.user_msg);
        ivUserPraise=view.findViewById(R.id.iv_user_praise);
        userInterfaceName=view.findViewById(R.id.user_interface_name);
        tvFans=view.findViewById(R.id.tv_fans);
        tvAttention=view.findViewById(R.id.tv_attention);
        tvUserAttention=view.findViewById(R.id.tv_user_attention);
        tvPraiseNum=view.findViewById(R.id.tv_praise_num);
        ivUserInterfaceImg=view.findViewById(R.id.iv_user_interface_img);
        tabAttention=view.findViewById(R.id.tab_attention);
        tvUserAttention.setOnClickListener(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN ,sticky = true)
    public void onData(String uid){
        System.out.println("uid=============="+uid);
       if(!TextUtils.isEmpty(uid))
       {

           pUid = uid;
               userInterfacePresenter.getUserIntefaceInfo(uid,page+"");
           }
           else {
              showToast("用户不存在");
           }


    }

    @Override
    public void ionDestroy() {
EventBus.getDefault().removeAllStickyEvents();
    }


    @Override
    public void showLoading() {
        DialogUtils.showDialog(this);
    }

    @Override
    public void hidLoading() {
        DialogUtils.hiddialog();
    }

    @Override
    public void failure(String msg) {
showToast(msg);
    }

    @Override
    public void sucData(Object data) {
        List<UserInterfaceBean.DataBean> list = ((UserInterfaceBean) data).getData();
        if(list!=null)
        {
        for (int i = userList.size(); i < userList.size()+list.size(); i++) {
            map.put(i,false);
        }
        userList.addAll(list);
        if(adapter==null)
        {
        adapter = new UserInterfaceAdapter(this,userList,map);
        xrcvUserInterface.setAdapter(adapter);
        }
        else {
            adapter.notifyDataSetChanged();
        }
        }
//        TuijianHot_LookAdapter tuijianHot_lookAdapter=new TuijianHot_LookAdapter(UserInterfaceActivity.this,list,map);
        xrcvUserInterface.loadMoreComplete();
        xrcvUserInterface.refreshComplete();

    }

    @Override
    public void sucAttentionData(Object data) {
        String code = (String) data;
        if("0".equals(code))
        {
            showToast("关注成功");
            tvUserAttention.setSelected(true);
            tvUserAttention.setTextColor(Color.WHITE);
        }
        else if("2".equals(code)){
            showToast("code为2");
            start(OtherRegActivity.class,true);

        }
        else {
            tvUserAttention.setTextColor(Color.WHITE);
            tvUserAttention.setSelected(true);
            showToast("用户已关注");
        }
    }

    @Override
    public void errorAttentionInfo(String msg) {
        showToast(msg);
    }


//

    @Override
    public void onRefresh() {
        page=1;
        userList.clear();
        System.out.println("pUid=========="+pUid);
        if(!TextUtils.isEmpty(pUid))
        {
            userInterfacePresenter.getUserIntefaceInfo(pUid,page+"");
        }

    }

    @Override
    public void onLoadMore() {
page++;
        if(!TextUtils.isEmpty(pUid))
        {
            userInterfacePresenter.getUserIntefaceInfo(pUid,page+"");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tv_user_attention:
                System.out.println("uid=========="+uid);
                userInterfacePresenter.getAttentionInfo(uid);
                break;
        }
    }
}
