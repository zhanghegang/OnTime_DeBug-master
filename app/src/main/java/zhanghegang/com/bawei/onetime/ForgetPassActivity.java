package zhanghegang.com.bawei.onetime;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import zhanghegang.com.bawei.onetime.base.BaseActivity;
import zhanghegang.com.bawei.onetime.base.BasePresenter;

public class ForgetPassActivity extends BaseActivity {

    @BindView(R.id.tv_forget_back)
    ImageView tvForgetBack;
    @BindView(R.id.tv_forget_hava)
    TextView tvForgetHava;
    @BindView(R.id.iv_other_middle)
    ImageView ivOtherMiddle;
    @BindView(R.id.et_forgert_phone)
    EditText etForgertPhone;
    @BindView(R.id.tv_gainVercode)
    TextView tvGainVercode;
    @BindView(R.id.et_pass)
    EditText etPass;
    @BindView(R.id.ll_edit)
    LinearLayout llEdit;
    @BindView(R.id.btn_foget_next)
    Button btnFogetNext;
    @BindView(R.id.tv_auto_reg)
    TextView tvAutoReg;
    private EventHandler eventHandler;

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_forget_pass;
    }

    @Override
    public void initView() {
        setStatus(true);
        getEventHandler();
        gainVercode();

    }

    private void getEventHandler() {
        eventHandler = new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object o) {
if(result==SMSSDK.RESULT_COMPLETE)
{
    if(event==SMSSDK.EVENT_GET_VERIFICATION_CODE)
    {
        showToast("获取验证码成功");
    }
    else if(event==SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE)
    {
        showToast("提交验证码成功");
        System.out.println("提交验证码");
    }
}
else {
    showToast("mob错误");
    System.out.println("mob错误");
}


            }
        };
        SMSSDK.registerEventHandler(eventHandler);

    }

    @Override
    public void ionDestroy() {
        if(eventHandler!=null)
        {
SMSSDK.unregisterEventHandler(eventHandler);
        }
    }




    @OnClick({R.id.tv_forget_back, R.id.tv_forget_hava, R.id.tv_gainVercode, R.id.btn_foget_next, R.id.tv_auto_reg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_forget_back:
                finish();
                break;
            case R.id.tv_forget_hava:
              start(RegActivity.class,true);
                break;
            case R.id.tv_gainVercode:

                break;
            case R.id.btn_foget_next:
                String phone=etForgertPhone.getText().toString().trim();
                String version = etPass.getText().toString();
                SMSSDK.submitVerificationCode("86",phone,version);
                break;
            case R.id.tv_auto_reg:
                start(MainActivity.class,true);
                break;
        }
    }

    private void gainVercode() {

        RxView.clicks(tvGainVercode).subscribeOn(AndroidSchedulers.mainThread())
                .throttleFirst(30, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                        String phone = etForgertPhone.getText().toString().trim();
                        SMSSDK.getVerificationCode("86",phone);
                        Observable.interval(1,TimeUnit.SECONDS,AndroidSchedulers.mainThread())
                                .take(30).subscribe(new Observer<Long>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Long aLong) {
tvGainVercode.setText(29-aLong+"s");
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {
tvGainVercode.setText("重新获取验证码");
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
