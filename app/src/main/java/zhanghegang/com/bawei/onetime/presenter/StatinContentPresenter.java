package zhanghegang.com.bawei.onetime.presenter;

import android.text.TextUtils;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import zhanghegang.com.bawei.onetime.base.BasePresenter;
import zhanghegang.com.bawei.onetime.model.StatinContentCallBack;
import zhanghegang.com.bawei.onetime.model.StatinContentInfoModel;
import zhanghegang.com.bawei.onetime.view.StatinContentView;

/**
 * current package:zhanghegang.com.bawei.onetime.presenter
 * Created by Mr.zhang
 * date: 2017/11/29
 * decription:开发
 */

public class StatinContentPresenter extends BasePresenter<StatinContentView> {

    private final StatinContentInfoModel statinContentInfoModel;

    public StatinContentPresenter(StatinContentView mView) {
        super(mView);
        statinContentInfoModel = new StatinContentInfoModel();
    }
    public void getStatinContentInfo(String uid,String content,List<String> file){


        MultipartBody.Builder builder=new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("uid",uid)
                .addFormDataPart("content",content);
        if(file!=null)
        {
            for (int i = 0; i < file.size(); i++) {
                File file1=new File(file.get(i));
                System.out.println("filename=========="+file1.getName());
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file1);
                builder.addFormDataPart("jokeFiles",file1.getName(),requestBody);
            }

        }
        MultipartBody build = builder.build();
        List<MultipartBody.Part> parts = build.parts();
        statinContentInfoModel.getStatinContentInfo(parts, new StatinContentCallBack() {
            @Override
            public void loadSucess(String data) {
                if(!TextUtils.isEmpty(data))
                {
                    mView.sucStatinContent(data);
                }
                else {
                    mView.failure("数据为空");
                }
            }

            @Override
            public void loadFail(String msg) {
mView.failure(msg);
            }
        });
    }
}
