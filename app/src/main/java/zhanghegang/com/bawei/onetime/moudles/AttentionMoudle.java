package zhanghegang.com.bawei.onetime.moudles;

import dagger.Module;
import dagger.Provides;
import zhanghegang.com.bawei.onetime.view.AttentionView;
import zhanghegang.com.bawei.onetime.view.UserInterfaceView;

/**
 * current package:zhanghegang.com.bawei.onetime.moudles
 * Created by Mr.zhang
 * date: 2017/12/12
 * decription:开发
 */
@Module
public class AttentionMoudle {
    public AttentionView versionUpdateView;
    public AttentionMoudle(AttentionView v) {
        this.versionUpdateView=v;
    }

    @Provides
    public AttentionView getPresenter(){
        return versionUpdateView;
    }
}
