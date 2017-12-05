package zhanghegang.com.bawei.onetime.presenter;

import zhanghegang.com.bawei.onetime.base.BasePresenter;
import zhanghegang.com.bawei.onetime.model.StatinCallBack;
import zhanghegang.com.bawei.onetime.model.StatinModel;
import zhanghegang.com.bawei.onetime.view.SatinView;

/**
 * current package:zhanghegang.com.bawei.onetime.presenter
 * Created by Mr.zhang
 * date: 2017/11/28
 * decription:开发
 */

public class StatinPresenter extends BasePresenter<SatinView> {

    private final StatinModel statinModel;
    public SatinView mView;

    public StatinPresenter(SatinView mView) {
        super(mView);
        statinModel = new StatinModel();
        this.mView=mView;
    }
    public void getStatinInfo(String page){
        System.out.println("size======page===="+page);
        statinModel.getStatinInfo(page, new StatinCallBack() {
            @Override
            public void loadSucess(Object data) {
                if(data!=null)
                {
                    mView.statinSuc(data);
                }
            }

            @Override
            public void loadFail(String msg) {
mView.failure(msg);
            }
        });
    }
}
