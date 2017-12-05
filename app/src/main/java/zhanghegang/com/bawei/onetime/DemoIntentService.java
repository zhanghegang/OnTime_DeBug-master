package zhanghegang.com.bawei.onetime;

import android.content.Context;

import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTTransmitMessage;

/**
 * current package:zhanghegang.com.bawei.onetime
 * Created by Mr.zhang
 * date: 2017/11/14
 * decription:开发
 */

public class DemoIntentService extends GTIntentService {
    @Override
    public void onReceiveServicePid(Context context, int i) {
        System.out.println("getui====onReceiveServicePid===="+i);
    }

    @Override
    public void onReceiveClientId(Context context, String s) {
        System.out.println("getui===onReceiveClientId====="+s);
    }

    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage gtTransmitMessage) {
        String msg=new String(gtTransmitMessage.getPayload());
        System.out.println("getui==onReceiveMessageData======"+msg);
    }

    @Override
    public void onReceiveOnlineState(Context context, boolean b) {
        System.out.println("getui===onReceiveOnlineState====="+b);
    }

    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage gtCmdMessage) {
        System.out.println("getui===onReceiveCommandResult====="+gtCmdMessage.toString());
    }
}
