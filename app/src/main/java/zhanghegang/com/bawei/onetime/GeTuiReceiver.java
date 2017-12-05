package zhanghegang.com.bawei.onetime;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushReceiver;

/**
 * current package:zhanghegang.com.bawei.onetime
 * Created by Mr.zhang
 * date: 2017/11/14
 * decription:开发
 */

public class GeTuiReceiver extends PushReceiver {

    private static OnGeTuiLitenter onGeTuiLitenter;
    private String message;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
     switch (extras.getInt(PushConsts.CMD_ACTION))
     {
         case PushConsts.GET_CLIENTID:
             String cid = extras.getString("clientid");
             System.out.println("===BroadCastReceiver======="+cid);
             break;
             case PushConsts.GET_MSG_DATA:
                 byte[] payloads = extras.getByteArray("payload");
                 if(payloads!=null)
                 {
                     message = new String(payloads);
                     onGeTuiLitenter.getGeTuiData(message);
                 }
                 else {
                     message="";
                 }
                 System.out.println("=====GET_MSG_DATA========"+message);
                 break;
     }

    }
    public static void setOnGeTuiLitenter(OnGeTuiLitenter onGeTuiLitenter){
        GeTuiReceiver.onGeTuiLitenter=onGeTuiLitenter;
    }
    public interface OnGeTuiLitenter{
        void getGeTuiData(String msg);
    }
}
