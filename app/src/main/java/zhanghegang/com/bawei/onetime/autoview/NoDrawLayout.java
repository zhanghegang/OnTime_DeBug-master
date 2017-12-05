package zhanghegang.com.bawei.onetime.autoview;

import android.content.Context;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;

/**
 * current package:zhanghegang.com.bawei.onetime.autoview
 * Created by Mr.zhang
 * date: 2017/11/26
 * decription:开发
 */

public class NoDrawLayout extends DrawerLayout {
    public NoDrawLayout(Context context) {
        this(context,null);
    }

    public NoDrawLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NoDrawLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev){
        switch(ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                final float x = ev.getX();
                final float y = ev.getY();
                final View touchedView = findTopChildUnder((int) x, (int) y);

                if (touchedView != null && isContentView(touchedView)
                        && this.isDrawerOpen(GravityCompat.START)) {

                    return false;
                }
                break;

            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * 判断点击位置是否位于相应的View内
     * @param x
     * @param y
     * @return
     */
    public View findTopChildUnder(int x, int y) {

        final int childCount = getChildCount();
        for (int i = childCount - 1; i >= 0; i--) {
            final View child = getChildAt(i);
            if (x >= child.getLeft() && x+100 < child.getRight() &&
                    y >= child.getTop() && y < child.getBottom()) {
                return child;
            }
        }
        return null;
    }

    /**
     * 判断点击触摸点的View是否是ContentView(即是主页面的View)
     * @param child
     * @return
     */
    boolean isContentView(View child) {
        return ((LayoutParams) child.getLayoutParams()).gravity == Gravity.NO_GRAVITY;
    }

}
