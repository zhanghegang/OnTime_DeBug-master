package zhanghegang.com.bawei.onetime;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

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
import zhanghegang.com.bawei.onetime.utils.DownApk;
import zhanghegang.com.bawei.onetime.utils.SharePrefrenceBack;
import zhanghegang.com.bawei.onetime.utils.SharePrefrenceUtils;
import zhanghegang.com.bawei.onetime.view.VersionUpdateView;

public class SettingActivity extends BaseActivity implements VersionUpdateView, View.OnClickListener, DownApk.OnBackProCess {


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
    private View inflate;
    private ProgressBar progressBar;
    private int contentLenth;
    private DownApk downApk;
    private AlertDialog show1;
    private AlertDialog.Builder builder1;
    private File file;
    private int vc;

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
        inflate = LayoutInflater.from(this).inflate(R.layout.down_dia_log, null);
        progressBar = inflate.findViewById(R.id.pb_down);
        progressBar.setMax(100);
        Button btn_down_pause= inflate.findViewById(R.id.btn_down_pause);
        Button btn_down_continue= inflate.findViewById(R.id.btn_down_continue);
        btn_down_pause.setOnClickListener(this);
        btn_down_continue.setOnClickListener(this);


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
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(new File("/data/data/com.onetime.platform/files/onetime.apk")), "application/vnd.android.package-archive");
        this.startActivity(intent);
        String msg = ((VerSionUpdateBean) data).getMsg();
        showToast(msg);
        VerSionUpdateBean.DataBean verSionInfo = ((VerSionUpdateBean) data).getData();

//        builder1 = new AlertDialog.Builder(this)
//                .setView(inflate);
//        show1 = builder1.show();

        String versionCode = verSionInfo.getVersionCode();
        try {
            Context context = MyApp.app;
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            vc = pi.versionCode;
            System.out.println("versionCode============"+ vc);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if(!versionCode.equals(vc+""))
        {
            String apkUrl = verSionInfo.getApkUrl();
            file = new File("/data/data/com.onetime.platform/files/onetime.apk");
            if(!file.exists())
            {
                try {
                    file.createNewFile();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            ///data/data/com.onetime.platform/files/onetime.apk
            ///data/data/com.onetime.platform/files/onetime.apk
            System.out.println(this.getFilesDir()+"downFile========"+ file.getName());
            downApk = new DownApk(apkUrl,2,this, file);
            downApk.setOnBackProCess(this);
      downApk.downNewVersion();
        }
        else {
            showToast("版本已最新");
            System.out.println("版本已最新");
        }






    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_down_pause:

                if(downApk!=null)
                {
                    downApk.onPause(true);
                }
                break;
            case R.id.btn_down_continue:
                if(downApk!=null)
                {
                    downApk.onPause(false);
                    downApk.downNewVersion();
                }
                break;
        }
    }
private int total=0;
    @Override
    public void onBack(int current) {

total+=current;
        System.out.println("SettingcontentLenth"+contentLenth+"current========="+current+"total======="+total);
        if(contentLenth!=0)
        {

            int progress = (int) (((float)(total / contentLenth))*100);
            System.out.println((double) (total / contentLenth)+"current=====%%%%%%%%%===="+progress);
            progressBar.setProgress(progress);
            if(progress==100)
            {
                System.out.println("progress========100");
//               show1.dismiss();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setDataAndType(Uri.fromFile(new File("/data/data/com.onetime.platform/files/onetime.apk")), "application/vnd.android.package-archive");
                this.startActivity(intent);

            }
        }
    }

    @Override
    public void onContentLenth(int contentLenth) {
 this.contentLenth=contentLenth;
    }
}
