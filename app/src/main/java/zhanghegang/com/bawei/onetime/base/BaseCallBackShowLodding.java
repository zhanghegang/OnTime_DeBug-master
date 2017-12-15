package zhanghegang.com.bawei.onetime.base;

/**
 * current package:zhanghegang.com.bawei.onetime.base
 * Created by Mr.zhang
 * date: 2017/12/12
 * decription:开发
 */

public interface BaseCallBackShowLodding {
    void loadSucess(Object data);
    void loadFail(String msg);
    void showLodding();
    void shutDownLodding();
}
