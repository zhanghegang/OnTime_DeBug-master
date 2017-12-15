package zhanghegang.com.bawei.onetime.presenter;

import android.text.TextUtils;

import zhanghegang.com.bawei.onetime.base.BaseCallBack;
import zhanghegang.com.bawei.onetime.base.BasePresenter;
import zhanghegang.com.bawei.onetime.model.VideoHotModel;
import zhanghegang.com.bawei.onetime.view.VideoHotView;

/**
 * current package:zhanghegang.com.bawei.onetime.presenter
 * Created by Mr.zhang
 * date: 2017/12/7
 * decription:开发
 */

public class HotVideoPresenter extends BasePresenter<VideoHotView> {

    private final VideoHotModel videoHotModel;

    public HotVideoPresenter(VideoHotView mView) {
        super(mView);
        videoHotModel = new VideoHotModel();
    }
    public void getHotVideoInfo(String page){
        if(!TextUtils.isEmpty(page))
        {
        videoHotModel.getUserInfo(page, new BaseCallBack() {
            @Override
            public void loadSucess(Object data) {
                if(data!=null)
                {
                    mView.sucData(data);
                }
            }

            @Override
            public void loadFail(String msg) {
mView.failure(msg);
            }
        });
        }
    }
}
