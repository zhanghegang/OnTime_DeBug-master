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
import io.reactivex.Observable;
import zhanghegang.com.bawei.onetime.base.BaseActivity;
import zhanghegang.com.bawei.onetime.base.BasePresenter;
import zhanghegang.com.bawei.onetime.bean.BaseReg;
import zhanghegang.com.bawei.onetime.bean.RegBean;
import zhanghegang.com.bawei.onetime.presenter.LoginPresenter;
import zhanghegang.com.bawei.onetime.utils.SharePrefrenceUtils;
import zhanghegang.com.bawei.onetime.view.LoginView;

public class OtherRegActivity extends BaseActivity<LoginPresenter> implements LoginView {

    @BindView(R.id.tv_add_acount)
    TextView tvAddAcount;
    @BindView(R.id.iv_other_middle)
    ImageView ivOtherMiddle;
    @BindView(R.id.et_user)
    EditText etUser;
    @BindView(R.id.et_pass)
    EditText etPass;
    @BindView(R.id.ll_edit)
    LinearLayout llEdit;
    @BindView(R.id.btn_other_reg)
    Button btnOtherReg;
    @BindView(R.id.tv_forget_pass)
    TextView tvForgetPass;
    @BindView(R.id.tv_auto_reg)
    TextView tv_auto_reg;
    private LoginPresenter loginPresenter;

    @Override
    public LoginPresenter initPresenter() {
        loginPresenter = new LoginPresenter(this);
        return loginPresenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_other_reg;
    }

    @Override
    public void initView() {
        setStatus(true);
    }

    @Override
    public void ionDestroy() {

    }




    @OnClick({R.id.tv_add_acount, R.id.btn_other_reg, R.id.tv_forget_pass, R.id.tv_auto_reg,R.id.tv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_add_acount:
                start(ReginPhoneActivity.class,false);
                break;
            case R.id.btn_other_reg:
                reghost();
                break;
            case R.id.tv_forget_pass:
                start(ForgetPassActivity.class,false);
                break;
            case R.id.tv_auto_reg:
                boolean publish = getIntent().getBooleanExtra("publish", false);
                if(publish)
                {
                    finish();
                }
                else {
                    start(MainActivity.class, false);
                }
                break;
            case R.id.tv_back:
                finish();
                break;
        }
    }

    private void reghost() {
        String phone = etUser.getText().toString().trim();
        String pass = etPass.getText().toString().trim();
        loginPresenter.login(phone,pass);
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
    public void gainSucess(Object data) {
        RegBean regBean = ((BaseReg<RegBean>) data).data;
        String token = regBean.token;
        String uid = regBean.uid;
        SharePrefrenceUtils.putData("token",token);
        SharePrefrenceUtils.putData("uid",uid);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                start(MainActivity.class,false);
            }
        });
    }

    @Override
    public void mobileError() {
showToast("手机号有误");
    }

    @Override
    public void passwordError() {
showToast("密码输入有误");
    }
}
