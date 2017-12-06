package zhanghegang.com.bawei.onetime;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cjt2325.cameralibrary.JCameraView;
import com.cjt2325.cameralibrary.listener.ClickListener;
import com.cjt2325.cameralibrary.listener.ErrorListener;
import com.cjt2325.cameralibrary.listener.JCameraListener;

import java.io.ByteArrayOutputStream;
import java.io.File;

import zhanghegang.com.bawei.onetime.base.BaseActivity;
import zhanghegang.com.bawei.onetime.presenter.PublishPresenter;
import zhanghegang.com.bawei.onetime.view.PublishVedioView;

public class PublishVideoActivity extends BaseActivity<PublishPresenter> implements PublishVedioView {


    private JCameraView jCameraView;

    @Override
    public PublishPresenter initPresenter() {
        return new PublishPresenter(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_publish_video;
    }

    @Override
    public void initView() {
setStatus(true);
initVideo();

    }

    private void initVideo() {

        if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        } else {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(option);
        }


        jCameraView = (JCameraView) findViewById(R.id.jcameraview);

//设置视频保存路径
        jCameraView.setSaveVideoPath(Environment.getExternalStorageDirectory().getPath() + File.separator + "JCamera2");

//设置只能录像或只能拍照或两种都可以（默认两种都可以）
        jCameraView.setFeatures(JCameraView.BUTTON_STATE_BOTH);

//设置视频质量
        jCameraView.setMediaQuality(JCameraView.MEDIA_QUALITY_MIDDLE);

//JCameraView监听
        jCameraView.setErrorLisenter(new ErrorListener() {
            @Override
            public void onError() {
                //打开Camera失败回调
                Log.i("CJT", "open camera error");
            }

            @Override
            public void AudioPermissionError() {
                //没有录取权限回调
                Log.i("CJT", "AudioPermissionError");
            }
        });

        jCameraView.setJCameraLisenter(new JCameraListener() {
            @Override
            public void captureSuccess(Bitmap bitmap) {
                //获取图片bitmap
                Log.i("JCameraView", "bitmap = " + bitmap.getWidth());
            }

            @Override
            public void recordSuccess(String url, Bitmap firstFrame) {
//获取视频路径
                Log.i("CJT", "url = " + url);
                Bundle bundle=new Bundle();
                bundle.putString("url",url);
                ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
               firstFrame.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                byte[] bytes = outputStream.toByteArray();
                bundle.putByteArray("firstFrame",bytes);
                start(PublishServerActivity.class,true,bundle);
            }
        });


//左边按钮点击事件
        jCameraView.setLeftClickListener(new ClickListener() {
            @Override
            public void onClick() {
                PublishVideoActivity.this.finish();
            }
        });
//右边按钮点击事件
        jCameraView.setRightClickListener(new ClickListener() {
                                              @Override
                                              public void onClick() {

                                                  Toast.makeText(PublishVideoActivity.this,"Right",Toast.LENGTH_SHORT).show();
                                              }
                                          }
        );
    }

    @Override
    public void ionDestroy() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hidLoading() {

    }

    @Override
    public void failure(String msg) {

    }

    @Override
    public void sucData(Object data) {

    }
}
