package zhanghegang.com.bawei.onetime.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import zhanghegang.com.bawei.onetime.R;

/**
 * current package:zhanghegang.com.bawei.onetime.utils
 * Created by Mr.zhang
 * date: 2017/12/13
 * decription:开发
 */

public class DialogUtils {

    static AlertDialog show;
    static ImageView iv_dialog;
    static RotateAnimation rotateAnimation;

    public static void showDialog(Context context){
//        ImageView imageView = new ImageView(context);
//        imageView.setImageResource(R.drawable.process);
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog, null);
        iv_dialog = inflate.findViewById(R.id.iv_dialog);
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setIcon(R.drawable.process).setView(inflate);
        show = builder.show();
        rotateAnimation = new RotateAnimation(0f,360f, Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
rotateAnimation.setDuration(2000);
rotateAnimation.setRepeatCount(-1);
iv_dialog.startAnimation(rotateAnimation);
    }
    public static void hiddialog(){
        if(show!=null&&iv_dialog!=null)
        {
            show.dismiss();
            rotateAnimation.cancel();

        }
    }

}
