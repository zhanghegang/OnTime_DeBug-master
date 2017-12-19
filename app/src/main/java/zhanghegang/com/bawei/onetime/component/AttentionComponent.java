package zhanghegang.com.bawei.onetime.component;

import dagger.Component;
import zhanghegang.com.bawei.onetime.UserInterfaceActivity;
import zhanghegang.com.bawei.onetime.fragment.Tuijian_Hot_Fragment;
import zhanghegang.com.bawei.onetime.moudles.AttentionMoudle;


;

/**
 * current package:zhanghegang.com.bawei.onetime.component
 * Created by Mr.zhang
 * date: 2017/12/12
 * decription:开发
 */
@Component(modules = AttentionMoudle.class)
public interface AttentionComponent {
//    void inject_userInterface(UserInterfaceActivity settingActivity);
    void inject_recommend(Tuijian_Hot_Fragment tuijian_hot_fragment);
}
