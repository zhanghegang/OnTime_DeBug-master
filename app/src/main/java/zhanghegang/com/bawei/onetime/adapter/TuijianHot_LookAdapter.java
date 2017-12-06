package zhanghegang.com.bawei.onetime.adapter;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Activity;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import android.widget.SeekBar;
import android.widget.TextView;


import com.dou361.ijkplayer.widget.IjkVideoView;
import com.dou361.ijkplayer.widget.PlayerView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import zhanghegang.com.bawei.onetime.R;
import zhanghegang.com.bawei.onetime.bean.VideosBean;

/**
 * current package:zhanghegang.com.bawei.onetime.adapter
 * Created by Mr.zhang
 * date: 2017/11/30
 * decription:开发
 */

public class TuijianHot_LookAdapter extends RecyclerView.Adapter<TuijianHot_LookAdapter.MyViewHolder> {


    public Activity context;
    public List<VideosBean.DataBean> list;
    public Map<Integer, Boolean> map;


    private View view;
    boolean flag = true;


    public TuijianHot_LookAdapter(Activity context, List<VideosBean.DataBean> list, Map<Integer, Boolean> map) {
        this.context = context;
        this.list = list;
        this.map = map;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.video_hot_item, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        VideosBean.DataBean dataBean = list.get(position);
        final String cover = dataBean.getCover();

        String createTime = dataBean.getCreateTime();
        String nickname = dataBean.getUser().getNickname();
        String icon = dataBean.getUser().getIcon();
        if (!TextUtils.isEmpty(icon)) {
            holder.ivHotUserhead.setImageURI(Uri.parse(icon));
//            Glide.with(context).load(icon).into(holder.ivHotUserhead);
        }
        holder.tvDuanziTime.setText(createTime + "");
        holder.tvDuanziName.setText(nickname);

//        View rootview = View.inflate(context, R.layout.simple_player_view_player, holder.rl_hot);


//        View rootview = LayoutInflater.from(context).inflate(R.layout.simple_player_view_player,holder.rlHotVideo);
        String url = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";
//        PlayerView playerView=new PlayerView(context,rootview);
        String videoUrl = dataBean.getVideoUrl();
        holder.llHotMenu.setVisibility(View.VISIBLE);
        holder.ijk.setVideoPath(videoUrl+"");
        holder.ivTrumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.llHotMenu.setVisibility(View.GONE);
                holder.appVideoReplay.setVisibility(View.VISIBLE);
                holder.ivTrumb.setVisibility(View.GONE);

            }
        });
        holder.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                holder.ijk.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        holder.ijk.setOnCompletionListener(new IMediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(IMediaPlayer iMediaPlayer) {
                long currentPosition = iMediaPlayer.getCurrentPosition();

                holder.seekBar.setProgress((int) currentPosition);
            }
        });
        holder.appVideoReplayIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(holder.ijk.isPlaying())
               {
                   holder.ijk.stopPlayback();
               }
               else {
                   holder.ijk.start();
               }
                for (int i = 0; i < list.size(); i++) {
                    holder.ijk.stopPlayback();
                }

                holder.appVideoReplayIcon.setVisibility(View.GONE);

            }
        });
//LinearLayout linearLayout=rootview.findViewById(R.id.simple_player_select_stream_container);

//holder.ijk.start();
//


        if (map.get(position)) {
            holder.ivDuanziJia.setImageResource(R.drawable.item_jian);
            holder.llPingbi.setVisibility(View.VISIBLE);
            holder.llLink.setVisibility(View.VISIBLE);
            holder.llTanhao.setVisibility(View.VISIBLE);
            Animator animator = AnimatorInflater.loadAnimator(context, R.animator.statin_anim_ll_translatex);
            Animator animator2 = AnimatorInflater.loadAnimator(context, R.animator.statin_anim_ll_translatex2);
            Animator animator3 = AnimatorInflater.loadAnimator(context, R.animator.statin_anim_ll_translatex3);
            animator.setTarget(holder.llPingbi);
            animator2.setTarget(holder.llLink);
            animator3.setTarget(holder.llTanhao);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(animator).with(animator2).with(animator3);
            animatorSet.start();

        } else {
            map.put(position, false);
            holder.ivDuanziJia.setImageResource(R.drawable.item_jia);
            holder.llPingbi.setVisibility(View.GONE);
            holder.llLink.setVisibility(View.GONE);
            holder.llTanhao.setVisibility(View.GONE);
        }
        holder.llDuanziJia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(position + "position==========" + map.get(position));


                if (map.get(position) == false) {
                    setAnim(holder, holder.getLayoutPosition());
                    map.put(position, true);
                } else {

                    setAnimin(holder, holder.getLayoutPosition());
                    map.put(position, false);
                }
            }
        });


    }


    /**
     * 退出动画
     *
     * @param myViewHolder
     * @param position
     */
    private void setAnimin(final MyViewHolder myViewHolder, int position) {
        //LinearLayout动画
        Animator animator = AnimatorInflater.loadAnimator(context, R.animator.statin_anim_in);
        Animator animator2 = AnimatorInflater.loadAnimator(context, R.animator.statin_anim2_in);
        Animator animator3 = AnimatorInflater.loadAnimator(context, R.animator.statin_anim3_in);
        Animator animator4 = AnimatorInflater.loadAnimator(context, R.animator.statin_anim4);

        animator.setTarget(myViewHolder.llPingbi);
        animator2.setTarget(myViewHolder.llLink);
        animator3.setTarget(myViewHolder.llTanhao);
        animator4.setTarget(myViewHolder.ivDuanziJia);
//图片动画
        Animator img1animator1 = AnimatorInflater.loadAnimator(context, R.animator.statin_anim_img_in);
        img1animator1.setTarget(myViewHolder.ivHotPingbi);
        Animator img1animator2 = AnimatorInflater.loadAnimator(context, R.animator.statin_anim_img_in);
        img1animator2.setTarget(myViewHolder.ivHotLink);
        Animator img1animator3 = AnimatorInflater.loadAnimator(context, R.animator.statin_anim_img_in);
        img1animator3.setTarget(myViewHolder.ivTanhao);

//动画集合让所有动画同时执行
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animator).with(animator2).with(animator3).with(animator4).with(img1animator1).with(img1animator2).with(img1animator3);

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                System.out.println("=========jia");
                //修改图片
                myViewHolder.ivDuanziJia.setImageResource(R.drawable.item_jia);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
//                if(commentNum!=null)
//                {
//                    tvMsg.setText(commentNum+"");
//                }
//                else {
//                    tvMsg.setText(0+"");
//                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();

    }

    /**
     * 图片进入动画
     *
     * @param myViewHolder
     * @param position
     */
    private void setAnim(final MyViewHolder myViewHolder, int position) {

//让控件显示
        myViewHolder.llPingbi.setVisibility(View.VISIBLE);
        myViewHolder.llLink.setVisibility(View.VISIBLE);
        myViewHolder.llTanhao.setVisibility(View.VISIBLE);
        //linearLayout动画
        Animator animator = AnimatorInflater.loadAnimator(context, R.animator.statin_anim);
        animator.setTarget(myViewHolder.llPingbi);
        Animator animator2 = AnimatorInflater.loadAnimator(context, R.animator.statin_anim2);
        Animator animator3 = AnimatorInflater.loadAnimator(context, R.animator.statin_anim3);
        Animator animator4 = AnimatorInflater.loadAnimator(context, R.animator.statin_anim4);


        animator2.setTarget(myViewHolder.llLink);
        animator3.setTarget(myViewHolder.llTanhao);
        animator4.setTarget(myViewHolder.ivDuanziJia);


//图片动画
        Animator img1animator1 = AnimatorInflater.loadAnimator(context, R.animator.statin_anim_img);
        img1animator1.setTarget(myViewHolder.ivHotPingbi);
        Animator img1animator2 = AnimatorInflater.loadAnimator(context, R.animator.statin_anim_img);
        img1animator2.setTarget(myViewHolder.ivHotLink);
        Animator img1animator3 = AnimatorInflater.loadAnimator(context, R.animator.statin_anim_img);
        img1animator3.setTarget(myViewHolder.ivTanhao);
//修改图片
        myViewHolder.ivDuanziJia.setImageResource(R.drawable.item_jian);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animator).with(animator2).with(animator3).with(animator4).with(img1animator1).with(img1animator2).with(img1animator3);
        animatorSet.start();
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {


            }

            @Override
            public void onAnimationEnd(Animator animation) {


            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_trumb)
        ImageView ivTrumb;
        @BindView(R.id.app_video_replay_icon)
        ImageView appVideoReplayIcon;
        @BindView(R.id.app_video_replay)
        LinearLayout appVideoReplay;
        @BindView(R.id.seekBar)
        SeekBar seekBar;
        @BindView(R.id.ijk_hot)
        IjkVideoView ijk;

        @BindView(R.id.ll_hot_menu)
        LinearLayout llHotMenu;
        @BindView(R.id.iv_hot_userhead)
        SimpleDraweeView ivHotUserhead;
        @BindView(R.id.tv_duanzi_name)
        TextView tvDuanziName;
        @BindView(R.id.tv_duanzi_time)
        TextView tvDuanziTime;
        @BindView(R.id.iv_hot_pingbi)
        ImageView ivHotPingbi;
        @BindView(R.id.tv_msg)
        TextView tvMsg;
        @BindView(R.id.ll_pingbi)
        LinearLayout llPingbi;
        @BindView(R.id.iv_hot_link)
        ImageView ivHotLink;
        @BindView(R.id.tv_duanzi_share)
        TextView tvDuanziShare;
        @BindView(R.id.ll_link)
        LinearLayout llLink;
        @BindView(R.id.iv_tanhao)
        ImageView ivTanhao;
        @BindView(R.id.tv_duanzi_guanzhu)
        TextView tvDuanziGuanzhu;
        @BindView(R.id.ll_tanhao)
        LinearLayout llTanhao;
        @BindView(R.id.iv_duanzi_jia)
        ImageView ivDuanziJia;
        @BindView(R.id.ll_duanzi_jia)
        LinearLayout llDuanziJia;
        @BindView(R.id.tv_hot_title)
        TextView tvHotTitle;
        @BindView(R.id.hot_heart)
        ImageView hotHeart;
        @BindView(R.id.tv_hot_heart)
        TextView tvHotHeart;
        @BindView(R.id.hot_start)
        ImageView hotStart;
        @BindView(R.id.tv_hot_start)
        TextView tvHotStart;
        @BindView(R.id.hot_share)
        ImageView hotShare;
        @BindView(R.id.tv_hot_shart)
        TextView tvHotShart;
        @BindView(R.id.hot_msg)
        ImageView hotMsg;
        @BindView(R.id.tv_hot_msg)
        TextView tvHotMsg;
        @BindView(R.id.hot_before)
        RelativeLayout hotBefore;
        private final RelativeLayout rl_hot;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            rl_hot = itemView.findViewById(R.id.hot_before);

        }
    }
}
