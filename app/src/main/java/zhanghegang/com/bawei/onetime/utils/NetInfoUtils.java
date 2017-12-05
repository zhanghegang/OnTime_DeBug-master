package zhanghegang.com.bawei.onetime.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * current package:zhanghegang.com.bawei.onetime.utils
 * Created by Mr.zhang
 * date: 2017/11/13
 * decription:开发
 */

public class NetInfoUtils {
    public static void gainNetInfoStatus(Context context,OnNetInfoStatus onNetInfoStatus){
       ConnectivityManager con= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = con.getActiveNetworkInfo();
        if(activeNetworkInfo!=null)
        {
          onNetInfoStatus.hasNet();
        }
        else {
            onNetInfoStatus.noNet();
        }

    }
    public interface OnNetInfoStatus{
        void hasNet();
        void noNet();
    }
}
