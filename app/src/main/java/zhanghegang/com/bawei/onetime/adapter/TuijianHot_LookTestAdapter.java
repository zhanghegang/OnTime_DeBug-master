package zhanghegang.com.bawei.onetime.adapter;


import android.app.Activity;
import android.content.Context;

import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;

import zhanghegang.com.bawei.onetime.R;


/**
 * current package:zhanghegang.com.bawei.onetime.adapter
 * Created by Mr.zhang
 * date: 2017/11/30
 * decription:开发
 */

public class TuijianHot_LookTestAdapter extends RecyclerView.Adapter<TuijianHot_LookTestAdapter.MyViewHolder> {
    public Context context;

    public TuijianHot_LookTestAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.test_hot,null));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        View root=View.inflate(context,R.layout.simple_player_view_player,holder.rl);
        String url = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";
       PlayerView player = new PlayerView((Activity) context,root)
                .setTitle("什么")
                .setScaleType(PlayStateParams.fitparent)
                .hideMenu(true)
                .forbidTouch(false)
                .showThumbnail(new OnShowThumbnailListener() {
                    @Override
                    public void onShowThumbnail(ImageView ivThumbnail) {
                        Glide.with(context)
                                .load("http://pic2.nipic.com/20090413/406638_125424003_2.jpg")
                                .placeholder(R.color.colorAccent)
                                .error(R.color.colorPrimary)
                                .into(ivThumbnail);
                    }
                })
                .setPlaySource(url)
                .startPlay();
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private final RelativeLayout rl;

        public MyViewHolder(View itemView) {
        super(itemView);
            rl = itemView.findViewById(R.id.rl_test_hot);
    }
}


}
