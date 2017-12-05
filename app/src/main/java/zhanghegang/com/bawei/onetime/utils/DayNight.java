package zhanghegang.com.bawei.onetime.utils;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * current package:zhanghegang.com.bawei.onetime.utils
 * Created by Mr.zhang
 * date: 2017/11/24
 * decription:开发
 */

public class DayNight {

    /**
     * 日间模式
     */
    private final static int DAY_THEME = 1;

    /**
     * 夜间模式
     */
    private final static int NIGHT_THEME = 2;

    private Button btnSwitch;

    private TextView tvColor;

    private RelativeLayout rootLayout;

    private ImageView imageView;

    private int width;
    private int height;
    private int statusBarHeight;

}
