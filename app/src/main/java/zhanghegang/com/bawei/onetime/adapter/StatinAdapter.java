package zhanghegang.com.bawei.onetime.adapter;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhanghegang.com.bawei.onetime.R;
import zhanghegang.com.bawei.onetime.bean.StatinBean;

/**
 * current package:zhanghegang.com.bawei.onetime.adapter
 * Created by Mr.zhang
 * date: 2017/11/28
 * decription:开发
 */

public class StatinAdapter extends RecyclerView.Adapter<StatinAdapter.MyViewHolder> {
    public Context context;
    public List<StatinBean.DataBean> list;
public Map<Integer,Boolean> map;
    private View view;
    boolean flag=true;


    public StatinAdapter(Context context, List<StatinBean.DataBean> list,Map<Integer,Boolean> map) {
        this.context = context;
        this.list = list;
      this.map=map;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.duanzi_item, null);
       MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        StatinBean.DataBean statinBean = list.get(position);
        String imgUrls = (String) statinBean.getImgUrls();

            if(!TextUtils.isEmpty(imgUrls))
            {
                String[] split = imgUrls.split("\\|");
                if(split.length<3)
                {
                    holder.rcv_statin_img.setLayoutManager(new GridLayoutManager(context,split.length));

                }
                else {
                    holder.rcv_statin_img.setLayoutManager(new GridLayoutManager(context,3));
                }
                StatinIMgeAdapter statinIMgeAdapter=new StatinIMgeAdapter(context,split);
                holder.rcv_statin_img.setAdapter(statinIMgeAdapter);
        }

        String createTime = statinBean.getCreateTime();

        String content = statinBean.getContent();
        StatinBean.DataBean.UserBean user = statinBean.getUser();
        String icon = user.getIcon();
        String nickname = user.getNickname();
        if(!TextUtils.isEmpty(icon)) {
            System.out.println("icon============"+icon);
            holder.ivDuanziUserhead.setImageURI(Uri.parse(icon));
        }
      holder.tvDuanziName.setText(nickname);
       holder.tvDuanziTime.setText(createTime);
        holder.tvDuanziContent.setText(content);

        final Object commentNum = list.get(position).getCommentNum();
        final Object praiseNum = list.get(position).getPraiseNum();
        final Object shareNum = list.get(position).getShareNum();

        if(commentNum!=null)
        {
            holder.tvMsg.setText(commentNum+"");
        }
        if(shareNum!=null)
        {
            holder.tvDuanziShare.setText(shareNum+"");
        }
        if(praiseNum!=null)
        {
            holder.tvDuanziGuanzhu.setText(praiseNum+"");
        }

        if(commentNum==null&&praiseNum==null&&shareNum==null) {
            holder.tvMsg.setText(0+"");
            holder.tvDuanziShare.setText(0+"");
            holder.tvDuanziGuanzhu.setText(0+"");
        }
        if(map.get(position))
        {
            holder.ivDuanziJia.setImageResource(R.drawable.item_jian);
           holder.llDuanziGuanzhu.setVisibility(View.VISIBLE);
            holder.llDuanziMsg.setVisibility(View.VISIBLE);
            holder.llDuanziShare.setVisibility(View.VISIBLE);
            Animator animator= AnimatorInflater.loadAnimator(context,R.animator.statin_anim_ll_translatex);
            Animator animator2= AnimatorInflater.loadAnimator(context,R.animator.statin_anim_ll_translatex2);
            Animator animator3= AnimatorInflater.loadAnimator(context,R.animator.statin_anim_ll_translatex3);
            animator.setTarget(holder.llDuanziGuanzhu);
            animator2.setTarget(holder.llDuanziShare);
            animator3.setTarget(holder.llDuanziMsg);
            AnimatorSet animatorSet=new AnimatorSet();
            animatorSet.play(animator).with(animator2).with(animator3);
            animatorSet.start();

        }
        else {
            map.put(position,false);
            holder.ivDuanziJia.setImageResource(R.drawable.item_jia);
            holder.llDuanziGuanzhu.setVisibility(View.GONE);
            holder.llDuanziMsg.setVisibility(View.GONE);
            holder.llDuanziShare.setVisibility(View.GONE);
        }
        holder.llDuanziJia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(position+"position=========="+map.get(position));


                        if(map.get(position)==false)
                        {
                            setAnim(holder,holder.getLayoutPosition());
                            map.put(position,true);
                        }
                        else {

                            setAnimin(holder,holder.getLayoutPosition());
                            map.put(position,false);
                        }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * 退出动画
     * @param myViewHolder
     * @param position
     */
    private void setAnimin(final MyViewHolder myViewHolder, int position){
    //LinearLayout动画
    Animator animator= AnimatorInflater.loadAnimator(context,R.animator.statin_anim_in);
    Animator animator2= AnimatorInflater.loadAnimator(context,R.animator.statin_anim2_in);
    Animator animator3= AnimatorInflater.loadAnimator(context,R.animator.statin_anim3_in);
    Animator animator4= AnimatorInflater.loadAnimator(context,R.animator.statin_anim4);

    animator.setTarget(myViewHolder.llDuanziGuanzhu);
    animator2.setTarget(myViewHolder.llDuanziShare);
    animator3.setTarget(myViewHolder.llDuanziMsg);
    animator4.setTarget(myViewHolder.ivDuanziJia);
//图片动画
    Animator img1animator1= AnimatorInflater.loadAnimator(context,R.animator.statin_anim_img_in);
    img1animator1.setTarget(myViewHolder.ivDuanziGuanzhu);
    Animator img1animator2= AnimatorInflater.loadAnimator(context,R.animator.statin_anim_img_in);
    img1animator2.setTarget(myViewHolder.ivDuanziShare);
    Animator img1animator3= AnimatorInflater.loadAnimator(context,R.animator.statin_anim_img_in);
    img1animator3.setTarget(myViewHolder.ivDuanziMsg);

//动画集合让所有动画同时执行
    AnimatorSet animatorSet=new AnimatorSet();
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
     * @param myViewHolder
     * @param position
     */
    private void setAnim(final MyViewHolder myViewHolder, int position) {

//让控件显示
        myViewHolder.llDuanziGuanzhu.setVisibility(View.VISIBLE);
        myViewHolder.llDuanziMsg.setVisibility(View.VISIBLE);
        myViewHolder.llDuanziShare.setVisibility(View.VISIBLE);
        //linearLayout动画
        Animator animator= AnimatorInflater.loadAnimator(context,R.animator.statin_anim);
        animator.setTarget(myViewHolder.llDuanziGuanzhu);
        Animator animator2= AnimatorInflater.loadAnimator(context,R.animator.statin_anim2);
        Animator animator3= AnimatorInflater.loadAnimator(context,R.animator.statin_anim3);
        Animator animator4= AnimatorInflater.loadAnimator(context,R.animator.statin_anim4);


        animator2.setTarget(myViewHolder.llDuanziShare);
        animator3.setTarget(myViewHolder.llDuanziMsg);
        animator4.setTarget(myViewHolder.ivDuanziJia);


//图片动画
        Animator img1animator1= AnimatorInflater.loadAnimator(context,R.animator.statin_anim_img);
        img1animator1.setTarget(myViewHolder.ivDuanziGuanzhu);
        Animator img1animator2= AnimatorInflater.loadAnimator(context,R.animator.statin_anim_img);
        img1animator2.setTarget(myViewHolder.ivDuanziShare);
        Animator img1animator3= AnimatorInflater.loadAnimator(context,R.animator.statin_anim_img);
        img1animator3.setTarget(myViewHolder.ivDuanziMsg);
//修改图片
        myViewHolder.ivDuanziJia.setImageResource(R.drawable.item_jian);
        AnimatorSet animatorSet=new AnimatorSet();
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

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_duanzi_name)
        TextView tvDuanziName;
        @BindView(R.id.tv_duanzi_time)
        TextView tvDuanziTime;
        @BindView(R.id.iv_duanzi_msg)
        ImageView ivDuanziMsg;
        @BindView(R.id.tv_msg)
        TextView tvMsg;
        @BindView(R.id.ll_duanzi_msg)
        LinearLayout llDuanziMsg;
        @BindView(R.id.iv_duanzi_share)
        ImageView ivDuanziShare;
        @BindView(R.id.tv_duanzi_share)
        TextView tvDuanziShare;
        @BindView(R.id.ll_duanzi_share)
        LinearLayout llDuanziShare;
        @BindView(R.id.iv_duanzi_guanzhu)
        ImageView ivDuanziGuanzhu;
        @BindView(R.id.tv_duanzi_guanzhu)
        TextView tvDuanziGuanzhu;
        @BindView(R.id.ll_duanzi_guanzhu)
        LinearLayout llDuanziGuanzhu;
        @BindView(R.id.iv_duanzi_jia)
        ImageView ivDuanziJia;
        @BindView(R.id.ll_duanzi_jia)
        LinearLayout llDuanziJia;
        @BindView(R.id.tv_duanzi_content)
        TextView tvDuanziContent;
        @BindView(R.id.iv_duanzi_userhead)
        SimpleDraweeView ivDuanziUserhead;
        @BindView(R.id.rcv_statin_img)
        RecyclerView rcv_statin_img;
        public MyViewHolder(View itemView) {
            super(itemView);
ButterKnife.bind(this,itemView);
        }
    }
}
