package zhanghegang.com.bawei.onetime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import zhanghegang.com.bawei.onetime.adapter.StatinSendIMgeAdapter;
import zhanghegang.com.bawei.onetime.base.BaseActivity;
import zhanghegang.com.bawei.onetime.presenter.StatinContentPresenter;
import zhanghegang.com.bawei.onetime.utils.SharePrefrenceBack;
import zhanghegang.com.bawei.onetime.utils.SharePrefrenceUtils;
import zhanghegang.com.bawei.onetime.view.StatinContentView;

public class StatinContentActivity extends BaseActivity<StatinContentPresenter> implements StatinContentView {


    @BindView(R.id.tv_duanzi_quxiao)
    TextView tvDuanziQuxiao;
    @BindView(R.id.include)
    RelativeLayout include;
    @BindView(R.id.ed_fabiao)
    EditText edFabiao;
    @BindView(R.id.iv_addImg)
    ImageView ivAddImg;
    @BindView(R.id.tv_fabiao)
    TextView tvFabiao;
    @BindView(R.id.rl1)
    RelativeLayout rl1;
    @BindView(R.id.rcv_statin_send_img)
    RecyclerView rcvStatinSendImg;
    private List<String> path;

    @Override
    public StatinContentPresenter initPresenter() {

        return new StatinContentPresenter(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_statin_content;
    }

    @Override
    public void initView() {
        setStatus(true);
    }

    @Override
    public void ionDestroy() {

    }


    @OnClick({R.id.iv_addImg, R.id.tv_fabiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.iv_addImg:
                MultiImageSelector.create(this)
                        .start(StatinContentActivity.this, 100);
                break;
            case R.id.tv_fabiao:
                System.out.println("uid=========1111111111==content====");
                getStatinContentInfo();
                break;
        }
    }

    private void getStatinContentInfo() {
        String uid = (String) SharePrefrenceUtils.getData(SharePrefrenceBack.String, "uid");
        String content = edFabiao.getText().toString().trim();
        System.out.println(uid + "uid===========content====" + content);
        presenter.getStatinContentInfo(uid, content, path);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hidLoading() {

    }

    @Override
    public void failure(String msg) {
        showToast(msg);

    }

    @Override
    public void sucStatinContent(Object data) {
        String s = data.toString();
        if ("2".equals(s)) {
            start(OtherRegActivity.class, false);
        } else {
            showToast("发表成功");
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                // 获取返回的图片列表
                path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                if(path.size()<3)
                {
                    rcvStatinSendImg.setLayoutManager(new GridLayoutManager(this, path.size()));
                }
                else {
                    rcvStatinSendImg.setLayoutManager(new GridLayoutManager(this,3));
                }
                rcvStatinSendImg.setAdapter(new StatinSendIMgeAdapter(this, path));

                // 处理你自己的逻辑 ....
                for (String s : path) {
                    System.out.println("path==========" + s);
                }
            }
        }
}


}
