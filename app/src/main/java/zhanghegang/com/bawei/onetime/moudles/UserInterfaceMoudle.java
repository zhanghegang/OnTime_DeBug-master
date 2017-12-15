package zhanghegang.com.bawei.onetime.moudles;

import dagger.Module;
import dagger.Provides;
import zhanghegang.com.bawei.onetime.view.UserInterfaceView;
import zhanghegang.com.bawei.onetime.view.VersionUpdateView;

/**
 * current package:zhanghegang.com.bawei.onetime.moudles
 * Created by Mr.zhang
 * date: 2017/12/12
 * decription:开发
 */
@Module
public class UserInterfaceMoudle {
    public UserInterfaceView versionUpdateView;
    public UserInterfaceMoudle(UserInterfaceView v) {
        this.versionUpdateView=v;
    }

    @Provides
    public UserInterfaceView getPresenter(){
        return versionUpdateView;
    }
}
