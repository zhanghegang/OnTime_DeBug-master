package zhanghegang.com.bawei.onetime.presenter;

import java.util.HashMap;
import java.util.Map;

import zhanghegang.com.bawei.onetime.base.BaseCallBack;
import zhanghegang.com.bawei.onetime.base.BasePresenter;
import zhanghegang.com.bawei.onetime.model.TuijianModel;
import zhanghegang.com.bawei.onetime.view.HotVIew;

/**
 * current package:zhanghegang.com.bawei.onetime.presenter
 * Created by Mr.zhang
 * date: 2017/12/4
 * decription:开发
 */

public class TuijianPresenter extends BasePresenter<HotVIew> {

    private final TuijianModel tuijianModel;

    public TuijianPresenter(HotVIew mView) {
        super(mView);
        tuijianModel = new TuijianModel();
    }
    public void getHot_look(String uid,String type,String page){
        final Map<String,String> map=new HashMap<>();
        if(type.equals("2"))
        {
            map.put("uid",uid);
        }

        map.put("type",type);
        map.put("page",page);
        tuijianModel.getHot_look(map, new BaseCallBack() {
            @Override
            public void loadSucess(Object data) {
                if(data!=null)
                {
                    mView.sucHot(data);
                }
            }

            @Override
            public void loadFail(String msg) {
mView.failure(msg);
            }
        });
    }
}
