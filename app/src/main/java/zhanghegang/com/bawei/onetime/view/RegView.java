package zhanghegang.com.bawei.onetime.view;

import okhttp3.MultipartBody;
import zhanghegang.com.bawei.onetime.base.BaseView;

/**
 * current package:zhanghegang.com.bawei.onetime.view
 * Created by Mr.zhang
 * date: 2017/11/29
 * decription:开发
 */

public interface RegView extends BaseView{
    void sucReg(Object data);
    void mobileError(String msg);
void passwordError(String msg);

}
