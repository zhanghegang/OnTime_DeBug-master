package zhanghegang.com.bawei.onetime.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhanghegang.com.bawei.onetime.R;
import zhanghegang.com.bawei.onetime.bean.HotVideoBean;

/**
 * current package:zhanghegang.com.bawei.onetime.adapter
 * Created by Mr.zhang
 * date: 2017/12/7
 * decription:开发
 */

public class Video_hot_Adapter extends RecyclerView.Adapter<Video_hot_Adapter.MyViewHolder> {
    public Context context;
public List<HotVideoBean.DataBean> list;
    List<Integer> heigtList=new ArrayList<>();
    public Video_hot_Adapter(Context context, List<HotVideoBean.DataBean> list) {
        this.context = context;
        this.list = list;
        //初始化高度
        for (int i = 0; i <list.size()+1 ; i++) {
            int random =new Random().nextInt(300)+200;
            heigtList.add(random);
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.mvideo_hot_item,null);

        return new MyViewHolder(view);
    }
    public void setHeight( List<HotVideoBean.DataBean> list){
        if(heigtList.size()<list.size())
        {
            for (int i = heigtList.size(); i <list.size() ; i++) {
                heigtList.add(new Random().nextInt(300)+200);
            }
        }

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ViewGroup.LayoutParams layoutParams = holder.iv.getLayoutParams();
        layoutParams.height=heigtList.get(position);
        HotVideoBean.DataBean dataBean = list.get(position);
        String cover = dataBean.getCover();
        if(TextUtils.isEmpty(cover))
        {
            holder.iv.setVisibility(View.GONE);
        }
        else {

            Glide.with(context).load(cover).into(holder.iv);
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_video_hot_item)
        ImageView iv;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
