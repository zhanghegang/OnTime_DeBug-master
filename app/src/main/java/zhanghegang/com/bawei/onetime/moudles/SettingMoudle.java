package zhanghegang.com.bawei.onetime.moudles;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import zhanghegang.com.bawei.onetime.model.VersionUpdateModel;
import zhanghegang.com.bawei.onetime.presenter.VerSionUpdatePresenter;
import zhanghegang.com.bawei.onetime.view.VersionUpdateView;

/**
 * current package:zhanghegang.com.bawei.onetime.moudles
 * Created by Mr.zhang
 * date: 2017/12/12
 * decription:开发
 */
@Module
public class SettingMoudle {
    public VersionUpdateView versionUpdateView;
    public SettingMoudle(VersionUpdateView v) {
        this.versionUpdateView=v;
    }

    @Provides
    public VersionUpdateView getPresenter(){
        return versionUpdateView;
    }
}
