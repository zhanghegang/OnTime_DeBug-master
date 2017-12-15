package zhanghegang.com.bawei.onetime.component;

import dagger.Component;

import zhanghegang.com.bawei.onetime.UserInterfaceActivity;
;
import zhanghegang.com.bawei.onetime.moudles.UserInterfaceMoudle;

/**
 * current package:zhanghegang.com.bawei.onetime.component
 * Created by Mr.zhang
 * date: 2017/12/12
 * decription:开发
 */
@Component(modules = UserInterfaceMoudle.class)
public interface UserInterfaceComponent {
    void inject2(UserInterfaceActivity settingActivity);
}
