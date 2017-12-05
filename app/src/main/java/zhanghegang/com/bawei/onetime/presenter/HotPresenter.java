package zhanghegang.com.bawei.onetime.presenter;

import zhanghegang.com.bawei.onetime.base.BasePresenter;
import zhanghegang.com.bawei.onetime.model.HotInfoCallBack;
import zhanghegang.com.bawei.onetime.model.HotInfoModel;
import zhanghegang.com.bawei.onetime.view.HotFragmentView;

/**
 * current package:zhanghegang.com.bawei.onetime.presenter
 * Created by Mr.zhang
 * date: 2017/12/1
 * decription:开发
 */

public class HotPresenter extends BasePresenter<HotFragmentView> {

    private final HotInfoModel hotInfoModel;

    public HotPresenter(HotFragmentView mView) {
        super(mView);
        hotInfoModel = new HotInfoModel();
    }
    public void getHotInfo(){
        System.out.println("getHotInfo");
        hotInfoModel.getUserInfo(new HotInfoCallBack() {
            @Override
            public void loadSucVideo(Object data) {
                if(data!=null)
                {
                    mView.sucPublishVideo(data);
                }

            }

            @Override
            public void loadSucess(Object data) {
                if(data!=null)
                {
                    mView.sucBanner(data);
                }
            }

            @Override
            public void loadFail(String msg) {
mView.failure(msg);
            }
        });
    }
}
