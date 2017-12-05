package zhanghegang.com.bawei.onetime;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhanghegang.com.bawei.onetime.base.BaseActivity;
import zhanghegang.com.bawei.onetime.base.BasePresenter;

public class WriterStatinActivity extends BaseActivity {


    @BindView(R.id.tv_quxiao)
    TextView tvQuxiao;
    @BindView(R.id.include)
    RelativeLayout include;
    @BindView(R.id.iv_shipin)
    ImageView ivShipin;
    @BindView(R.id.iv_duanzi)
    ImageView ivDuanzi;

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_writer_statin;
    }

    @Override
    public void initView() {

        setStatus(true);
    }

    @Override
    public void ionDestroy() {

    }



    @OnClick({R.id.tv_quxiao, R.id.include, R.id.iv_shipin, R.id.iv_duanzi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_quxiao:
                finish();
                break;
            case R.id.include:
                break;
            case R.id.iv_shipin:
                break;
            case R.id.iv_duanzi:
start(StatinContentActivity.class,true);
                break;
        }
    }
}
