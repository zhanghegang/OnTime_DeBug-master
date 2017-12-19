package zhanghegang.com.bawei.onetime.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhanghegang.com.bawei.onetime.R;
import zhanghegang.com.bawei.onetime.bean.VideosBean;

/**
 * current package:zhanghegang.com.bawei.onetime.adapter
 * Created by Mr.zhang
 * date: 2017/12/19
 * decription:开发
 */

public class Comment_Adapter extends RecyclerView.Adapter<Comment_Adapter.MyViewHolder> {

    private Context context;
    private List<VideosBean.DataBean.CommentsBean> list;

    public Comment_Adapter(Context context, List<VideosBean.DataBean.CommentsBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.video_comment_list_item, null);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        VideosBean.DataBean.CommentsBean commentsBean = list.get(position);
        String content = commentsBean.getContent();
        String nickname = commentsBean.getNickname();
        System.out.println("nickname========"+nickname);
        if(!TextUtils.isEmpty(content))
        {
            holder.tvVideoCommentContent.setText(content);

        }
       if(!TextUtils.isEmpty(nickname)){
            holder.tvVideoCommentName.setText(nickname);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_video_comment_name)
        TextView tvVideoCommentName;
        @BindView(R.id.tv_video_comment_content)
        TextView tvVideoCommentContent;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
