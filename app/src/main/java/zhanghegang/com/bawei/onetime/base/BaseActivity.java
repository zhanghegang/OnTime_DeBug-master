package zhanghegang.com.bawei.onetime.base;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import zhanghegang.com.bawei.onetime.R;

/**
 * current package:zhanghegang.com.bawei.onetime.base
 * Created by Mr.zhang
 * date: 2017/11/12
 * decription:开发
 */

public abstract class BaseActivity<V extends BasePresenter> extends AppCompatActivity {
    public V presenter;
    //初始化接口
    public abstract V initPresenter();
    //获得布局id
    public abstract int getLayoutId();
    //获得初始化布局
    public abstract void initView();
    public abstract void ionDestroy();
    //提供onResum方法
    public void mOnResum(){}

    /**
     * 设置状态栏是否显示
     * @param isStatus
     */
    public void  setStatus(boolean isStatus){
if(isStatus)
{
    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT)
    {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }
}
}

    /**
     * 设置状态栏颜色
     * @param
     */
public void setNight(boolean isNight){
    if(isNight)
    {
        System.out.println("night===========");
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }else {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
    recreate();

}

public void setStatus(int color){

    //先设置状态栏显示
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
        {
            Window window = getWindow();
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
   window .addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
   window.setStatusBarColor(color);
        }
}
public void setActionBar(){
    getSupportActionBar().hide();
}
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        ButterKnife.bind(this);
       presenter= initPresenter();


       //激活统计
        MobclickAgent.onResume(this);
        //设置场景，这里是普通场景
        /**
         * EScenarioType. E_UM_NORMAL　　普通统计场景类型

         EScenarioType. E_UM_GAME     　　游戏场景类型

         EScenarioType. E_UM_ANALYTICS_OEM  统计盒子场景类型

         EScenarioType. E_UM_GAME_OEM      　 游戏盒子场景类型
         */
        MobclickAgent.setScenarioType(this,MobclickAgent.EScenarioType.E_UM_NORMAL);
//        //
//        MobclickAgent.UMAnalyticsConfig config=new MobclickAgent.UMAnalyticsConfig(this,"5a0a42bfa40fa3477e0004ab","Wandoujia", MobclickAgent.EScenarioType.E_UM_NORMAL);
//        MobclickAgent.startWithConfigure(config);
        initView();
    }
public void start(Class<?> tclass,boolean isFinish){
   if(Build.VERSION.SDK_INT>=21)
   {
       startActivity(new Intent(this,tclass), ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
   }
   else {
       startActivity(new Intent(this, tclass));
   }
     if(isFinish)
     {
         finish();
     }
}
    public void start(Class<?> tclass,boolean isFinish,Bundle bundle){
        Intent intent = new Intent(this, tclass);
        intent.putExtras(bundle);
        if(Build.VERSION.SDK_INT>=21)
        {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }
        else {
            startActivity(intent);
        }

        if(isFinish)
        {
            finish();
        }
    }
    public void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //关闭统计
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ionDestroy();
        if(presenter!=null)
        {
            presenter.deatch();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
