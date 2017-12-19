package zhanghegang.com.bawei.onetime;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatDelegate;
import android.util.DisplayMetrics;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.igexin.sdk.PushManager;
import com.igexin.sdk.PushReceiver;

import com.mob.MobApplication;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.bugly.crashreport.CrashReport;

import zhanghegang.com.bawei.onetime.bean.DaoMaster;
import zhanghegang.com.bawei.onetime.bean.DaoSession;

/**
 * current package:zhanghegang.com.bawei.onetime
 * Created by Mr.zhang
 * date: 2017/11/13
 * decription:开发
 */

public class MyApp extends MobApplication {
    public static Context app;
    private RefWatcher install;
private static MyApp daoApp;
    private DaoMaster.DevOpenHelper down_db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    /**
     * 获得RefWatcher检测activity的内存泄漏问题
     * @param context
     * @return
     */
    public static RefWatcher getRefWatcher(Context context){
    MyApp myApp= (MyApp) context.getApplicationContext();
    return myApp.install;

}
    public static void init(Context context) {
        sRes = context.getResources();
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
init(this);
initPush();
   initCrash();
   daoApp=this;
   setDao();



    }

    private void setDao() {
        down_db = new DaoMaster.DevOpenHelper(this, "down_db", null);
        SQLiteDatabase writableDatabase = down_db.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(writableDatabase);
        daoSession = daoMaster.newSession();

    }
    public DaoSession getDaoSession(){
        return daoSession;
    }

    public static MyApp getIntence(){
        return daoApp;
    }

    private void initCrash() {
        CrashReport.initCrashReport(getApplicationContext(), "e4a123c11b", false);
//        CrashReport.testJavaCrash();
        app = getApplicationContext();

        LeakCanary.install(this);
    }

    private void initPush() {
        PushManager.getInstance().initialize(this.getApplicationContext(), DemoPushService.class);
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), DemoIntentService.class);
        //自定义接口接收广播发送内容
//GeTuiReceiver.setOnGeTuiLitenter(new GeTuiReceiver.OnGeTuiLitenter() {
//    @Override
//    public void getGeTuiData(String msg) {
//        System.out.println("=======myapp=="+msg);
//    }
//});
    }

    public static Resources sRes;
    public static void updateNightMode(boolean on) {


//        System.out.println("======updateNightMode=====on========="+on+defaultNightMode);
        DisplayMetrics dm = sRes.getDisplayMetrics();
        Configuration config = sRes.getConfiguration();

        config.uiMode &= ~Configuration.UI_MODE_NIGHT_MASK;
        config.uiMode |= on ? Configuration.UI_MODE_NIGHT_YES : Configuration.UI_MODE_NIGHT_NO;
        sRes.updateConfiguration(config, dm);
        int defaultNightMode = AppCompatDelegate.getDefaultNightMode();

        if(defaultNightMode==AppCompatDelegate.MODE_NIGHT_YES)
        {
            System.out.println("======updateNightMode===Yes==on========="+on+defaultNightMode);
        }
        else {
            System.out.println("======updateNightMode==No===on========="+on+defaultNightMode);
        }
    }

}
