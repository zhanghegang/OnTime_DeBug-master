package zhanghegang.com.bawei.onetime.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import zhanghegang.com.bawei.onetime.R;

/**
 * current package:zhanghegang.com.bawei.onetime.adapter
 * Created by Mr.zhang
 * date: 2017/11/30
 * decription:开发
 */

public class StatinIMgeAdapter extends RecyclerView.Adapter<StatinIMgeAdapter.MyViewHolder> {
    public Context context;
    public String[] imgs;

    public StatinIMgeAdapter(Context context, String[] imgs) {
        this.context = context;
        this.imgs = imgs;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.statin_item_img,null);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String img = imgs[position];
        System.out.println("img=========="+img);
        Glide.with(context).load(img).into(holder.iv_statin_img);
    }

    @Override
    public int getItemCount() {
        return imgs.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{


        private final ImageView iv_statin_img;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_statin_img = itemView.findViewById(R.id.iv_statin_img);
        }
    }
}
