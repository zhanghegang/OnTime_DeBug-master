package zhanghegang.com.bawei.onetime;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;

import android.support.v7.app.AppCompatDelegate;
import android.util.DisplayMetrics;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;

import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.igexin.sdk.PushManager;
import com.igexin.sdk.PushReceiver;

import com.mob.MobApplication;
import com.mob.MobSDK;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.Iterator;
import java.util.List;

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
        initHuanxin();
        Fresco.initialize(this);
init(this);
initPush();
//initMob();
   initCrash();
   daoApp=this;
   setDao();




    }

    private void initMob() {
        MobSDK.init(this, null, null);
    }


    private void initHuanxin() {

        EMOptions options = new EMOptions();
// 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        //自动登录
        options.setAutoLogin(true);
// 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
//        options.setAutoTransferMessageAttachments(true);
// 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
//        options.setAutoDownloadThumbnail(true);

        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
// 如果APP启用了远程的service，此application:onCreate会被调用2次
// 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
// 默认的APP会在以包名为默认的process name下运行，如果查到的process name不是APP的process name就立即返回

        if (processAppName == null ||!processAppName.equalsIgnoreCase(this.getPackageName())) {
            Log.e("myApp======", "enter the service process!");

            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }
//初始化
        EMClient.getInstance().init(this, options);
//在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);

    }
    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
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
