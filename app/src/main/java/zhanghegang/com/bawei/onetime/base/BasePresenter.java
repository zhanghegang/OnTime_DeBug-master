package zhanghegang.com.bawei.onetime.base;

/**
 * current package:zhanghegang.com.bawei.onetime.base
 * Created by Mr.zhang
 * date: 2017/11/12
 * decription:开发
 */

public class BasePresenter<V> {
    /**
     *  提供解绑和绑定方法
     */
    public V mView;

    public BasePresenter(V mView) {
        this.mView = mView;
    }
    public void deatch(){
        this.mView=null;
    }
}
