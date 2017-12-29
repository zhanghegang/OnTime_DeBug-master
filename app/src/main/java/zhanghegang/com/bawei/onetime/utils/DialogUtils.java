package zhanghegang.com.bawei.onetime.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

    static Dialog show;
    static ImageView iv_dialog;
    static RotateAnimation rotateAnimation;

    @SuppressLint("ResourceAsColor")
    public static void showDialog(Context context){

        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog, null);
        iv_dialog = inflate.findViewById(R.id.iv_dialog);

        AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.CustomDialog)
                .setIcon(R.drawable.process).setView(inflate);

        show = builder.show();


//设置dialog的大小和位置
        Window window = show.getWindow();

window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams attributes = window.getAttributes();
     attributes.width=400;
        attributes.height=400;
       window.setAttributes(attributes);




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
