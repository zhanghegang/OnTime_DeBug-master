package zhanghegang.com.bawei.onetime.presenter;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import zhanghegang.com.bawei.onetime.base.BaseCallBack;
import zhanghegang.com.bawei.onetime.base.BasePresenter;
import zhanghegang.com.bawei.onetime.model.PublishVedioModel;
import zhanghegang.com.bawei.onetime.view.PublishVedioView;

/**
 * current package:zhanghegang.com.bawei.onetime.presenter
 * Created by Mr.zhang
 * date: 2017/12/5
 * decription:开发
 */

public class PublishPresenter extends BasePresenter<PublishVedioView> {

    private final PublishVedioModel publishVedioModel;

    public PublishPresenter(PublishVedioView mView) {
        super(mView);
        publishVedioModel = new PublishVedioModel();
    }
    public void getPublishVideo(String uid, File videoFile,File coverFile,String dec,String latitude,String longtude){
        MultipartBody.Builder builder=new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("uid",uid);
        builder.addFormDataPart("videoDesc",dec);
        builder.addFormDataPart("latitude",latitude);
        builder.addFormDataPart("longitude",longtude);
        if(videoFile!=null)
        {
            RequestBody requestBody=RequestBody.create(MediaType.parse("multipart/formdata"),videoFile);
            builder.addFormDataPart("videoFile",videoFile.getName(),requestBody);
        }
        if(coverFile!=null)
        {
            RequestBody requestBody=RequestBody.create(MediaType.parse("multipart/formdata"),coverFile);
            builder.addFormDataPart("coverFile",coverFile.getName(),requestBody);
        }

        publishVedioModel.getUserInfo(builder.build().parts(), new BaseCallBack() {
            @Override
            public void loadSucess(Object data) {
                if(data!=null)
                {
                    mView.sucData(data);
                }
                else {
                    mView.failure("上传有误");
                }
            }

            @Override
            public void loadFail(String msg) {
mView.failure(msg);
            }
        });


    }
}
