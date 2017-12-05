package zhanghegang.com.bawei.onetime;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;



import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhanghegang.com.bawei.onetime.base.BaseActivity;
import zhanghegang.com.bawei.onetime.presenter.RegPresenter;
import zhanghegang.com.bawei.onetime.view.RegView;

public class ReginPhoneActivity extends BaseActivity<RegPresenter> implements RegView {


    @BindView(R.id.iv_reg_back)
    ImageView ivRegBack;
    @BindView(R.id.tv_cacanle)
    TextView tvCacanle;
    @BindView(R.id.iv_other_middle)
    ImageView ivOtherMiddle;
    @BindView(R.id.et_user)
    EditText etUser;
    @BindView(R.id.et_pass)
    EditText etPass;
    @BindView(R.id.ll_edit)
    LinearLayout llEdit;
    @BindView(R.id.btn_phone_reg)
    Button btnPhoneReg;
    @BindView(R.id.tv_auto_reg)
    TextView tvAutoReg;

    @Override
    public RegPresenter initPresenter() {
        return new RegPresenter(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_regin_phone;
    }

    @Override
    public void initView() {
        setStatus(true);

    }


    @Override
    public void ionDestroy() {

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
    public void sucReg(Object data) {
        if (data.equals("0")) {
            start(OtherRegActivity.class, true);
        } else {
            showToast("浪了，注册有误，请重新注册");
        }
    }

    @Override
    public void mobileError(String msg) {
        showToast(msg);
    }

    @Override
    public void passwordError(String msg) {
        showToast(msg);
    }


    @OnClick({R.id.iv_reg_back, R.id.tv_cacanle, R.id.btn_phone_reg, R.id.tv_auto_reg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_reg_back:
                finish();
                break;
            case R.id.tv_cacanle:
                finish();
                break;
            case R.id.btn_phone_reg:
                setReg();
                break;
            case R.id.tv_auto_reg:
                start(MainActivity.class,true);
                break;
        }
    }

    private void setReg() {
        String user = etUser.getText().toString().trim();
        String pass = etPass.getText().toString().trim();
        presenter.getRegInfo("0",user,pass);
    }
}
