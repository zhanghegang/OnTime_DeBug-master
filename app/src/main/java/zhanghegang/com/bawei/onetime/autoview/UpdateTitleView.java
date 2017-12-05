package zhanghegang.com.bawei.onetime.autoview;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhanghegang.com.bawei.onetime.R;

/**
 * current package:zhanghegang.com.bawei.onetime.autoview
 * Created by Mr.zhang
 * date: 2017/11/23
 * decription:开发
 */

public class UpdateTitleView extends RelativeLayout {

    @BindView(R.id.main_user_head)
    RoundedImageView mainUserHead;
    @BindView(R.id.tv_main_title)
    TextView tvMainTitle;
    @BindView(R.id.iv_main_writer)
    ImageView ivMainWriter;
    private OnClickImage onClickImage;
//    private RoundedImageView roundedImageView;
//    private TextView tv_title;
//    private ImageView iv_writer;
private Context context;
    public UpdateTitleView(Context context) {
        this(context, null);
        this.context=context;
    }

    public UpdateTitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UpdateTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.main_title, this,true);
        ButterKnife.bind(this);

//        roundedImageView = findViewById(R.id.main_user_head);
//        tv_title = findViewById(R.id.tv_main_title);
//        iv_writer = findViewById(R.id.iv_main_writer);
    }
public void setTitle(String title){
        tvMainTitle.setText(title);
}
    @OnClick({R.id.main_user_head, R.id.iv_main_writer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_user_head:
                onClickImage.userHeadImage();
                break;
            case R.id.iv_main_writer:
                onClickImage.writerImage();
                break;
        }
    }
    public void setheadImage(Context context,String url){
        if(!TextUtils.isEmpty(url))
        {
            Glide.with(context).load(url).into(mainUserHead);
        }
    }
    public void setOnClickImage(OnClickImage onClickImage){
    this.onClickImage=onClickImage;

    }
    public interface OnClickImage{
    void userHeadImage();
    void writerImage();
    }
}
