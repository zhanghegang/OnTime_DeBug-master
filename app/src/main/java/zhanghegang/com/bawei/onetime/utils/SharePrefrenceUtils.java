package zhanghegang.com.bawei.onetime.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

import zhanghegang.com.bawei.onetime.MyApp;

/**
 * current package:zhanghegang.com.bawei.onetime.utils
 * Created by Mr.zhang
 * date: 2017/11/13
 * decription:开发
 */

public class SharePrefrenceUtils {
    public static SharedPreferences getSharePrefrence(){
        return MyApp.app.getSharedPreferences("user", Context.MODE_PRIVATE);
    }
    public static boolean putData(String key,Object value){
        SharedPreferences.Editor edit = getSharePrefrence().edit();
        if(value instanceof Boolean)
        {
            edit.putBoolean(key, (Boolean) value).commit();
            return true;
        }
        else if(value instanceof Integer){
           edit.putInt(key, (Integer) value).commit();
            return true;
        }
        else if(value instanceof String)
        {
            edit.putString(key, (String) value).commit();
            return true;
        }
        return false;
    }
    public static void remove(String key){
        getSharePrefrence().edit().remove(key).commit();
    }
    public static void removes(){
        getSharePrefrence().edit().clear().commit();

    }

    /**
     * o要返回值得类型
     * @param o
     * @param key
     * @return
     */
    public static Object getData(SharePrefrenceBack o,String key){

        if(o == SharePrefrenceBack.Boolean)
        {

            return getSharePrefrence().getBoolean(key,false);
        }
        else if(o == SharePrefrenceBack.Int){

            return getSharePrefrence().getInt(key,-1);
        }
        else if(o == SharePrefrenceBack.String)
        {

            return getSharePrefrence().getString(key,null);
        }
        return null;
    }


}
