package zhanghegang.com.bawei.onetime;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhanghegang.com.bawei.onetime.base.BaseActivity;
import zhanghegang.com.bawei.onetime.base.BasePresenter;
import zhanghegang.com.bawei.onetime.bean.VerSionUpdateBean;
import zhanghegang.com.bawei.onetime.component.DaggerSettingComponent;
import zhanghegang.com.bawei.onetime.moudles.SettingMoudle;
import zhanghegang.com.bawei.onetime.presenter.VerSionUpdatePresenter;
import zhanghegang.com.bawei.onetime.utils.CacheManager;
import zhanghegang.com.bawei.onetime.utils.DialogUtils;
import zhanghegang.com.bawei.onetime.utils.SharePrefrenceBack;
import zhanghegang.com.bawei.onetime.utils.SharePrefrenceUtils;
import zhanghegang.com.bawei.onetime.view.VersionUpdateView;

public class SettingActivity extends BaseActivity implements VersionUpdateView{


    @BindView(R.id.setting_back)
    ImageView settingBack;
    @BindView(R.id.rl_setting_head)
    RelativeLayout rlSettingHead;
    @BindView(R.id.tv_setting_version)
    TextView tvSettingVersion;
    @BindView(R.id.iv_check_right)
    ImageView ivCheckRight;
    @BindView(R.id.ll_check_update)
    RelativeLayout llCheckUpdate;
    @BindView(R.id.rl_option)
    RelativeLayout rlOption;
    @BindView(R.id.rl_about_onetime)
    RelativeLayout rlAboutOnetime;
    @BindView(R.id.tv_setting_cache)
    TextView tvSettingCache;
    @BindView(R.id.iv_clear_cache)
    ImageView ivClearCache;
    @BindView(R.id.rl_clear_cache)
    RelativeLayout rlClearCache;
    @BindView(R.id.btn_exit_login)
    Button btnExitLogin;
    private AlertDialog.Builder builder;
    private AlertDialog show;

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
    @Inject
    VerSionUpdatePresenter verSionUpdatePresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
        DaggerSettingComponent.builder().settingMoudle(new SettingMoudle(this)).build().inject(this);
        setStatus(getResources().getColor(R.color.main_head));
        String uid = (String) SharePrefrenceUtils.getData(SharePrefrenceBack.String, "uid");
        if(TextUtils.isEmpty(uid))
        {
            btnExitLogin.setVisibility(View.GONE);
        }
        else {
            btnExitLogin.setVisibility(View.VISIBLE);
        }
        getCache();


    }

    private void getCache() {
        long fileSize = CacheManager.getFileSize(this.getCacheDir());
        String fileSizeFormater= Formatter.formatFileSize(this, fileSize);
        System.out.println("filesize==========="+fileSizeFormater);
        if(!TextUtils.isEmpty(fileSizeFormater))
        {
            tvSettingCache.setText(fileSizeFormater);
        }

    }

    @Override
    public void ionDestroy() {

    }



    @OnClick({R.id.rl_setting_head, R.id.ll_check_update, R.id.rl_option, R.id.rl_about_onetime, R.id.rl_clear_cache, R.id.btn_exit_login,R.id.setting_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.rl_setting_head:
                break;
            case R.id.ll_check_update:
                verSionUpdatePresenter.getVersionUpdateInfo();
                break;
            case R.id.rl_option:
                break;
            case R.id.rl_about_onetime:
                break;
            case R.id.rl_clear_cache:
                clearCacheFile();
                break;
            case R.id.btn_exit_login:
                SharePrefrenceUtils.remove("uid");
                start(RegActivity.class,true);
                break;
        }
    }

    private void clearCacheFile() {
        long fileSize = CacheManager.getFileSize(this.getCacheDir());
        if(fileSize==0)
        {
            showToast("没有缓存哦，真棒");
        }
        else {
            String s = CacheManager.clearCacheFile(this);
            if (!TextUtils.isEmpty(s)) {
                if("0".equals(s))
                {
                    tvSettingCache.setText("0.0M");
                }
                else {
                    showToast(s);
                }
            } else {
                showToast("删除失败，请重试");
            }
        }
    }

    @Override
    public void showLoading() {
        DialogUtils.showDialog(this);


    }

    @Override
    public void hidLoading() {

        DialogUtils.hiddialog();



    }

    @Override
    public void failure(String msg) {
showToast(msg);
    }

    @Override
    public void sucData(Object data) {
        String msg = ((VerSionUpdateBean) data).getMsg();
        showToast(msg);
        VerSionUpdateBean.DataBean verSionInfo = ((VerSionUpdateBean) data).getData();



    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
