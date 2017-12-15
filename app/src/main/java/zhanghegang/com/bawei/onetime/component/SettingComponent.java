package zhanghegang.com.bawei.onetime.component;

import dagger.Component;
import zhanghegang.com.bawei.onetime.SettingActivity;
import zhanghegang.com.bawei.onetime.moudles.SettingMoudle;

/**
 * current package:zhanghegang.com.bawei.onetime.component
 * Created by Mr.zhang
 * date: 2017/12/12
 * decription:开发
 */
@Component(modules = SettingMoudle.class)
public interface SettingComponent {
    void inject(SettingActivity settingActivity);
}
