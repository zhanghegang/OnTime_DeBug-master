package zhanghegang.com.bawei.onetime;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhanghegang.com.bawei.onetime.base.BaseActivity;
import zhanghegang.com.bawei.onetime.presenter.PublishPresenter;
import zhanghegang.com.bawei.onetime.utils.SharePrefrenceBack;
import zhanghegang.com.bawei.onetime.utils.SharePrefrenceUtils;
import zhanghegang.com.bawei.onetime.view.PublishVedioView;

public class PublishServerActivity extends BaseActivity<PublishPresenter> implements PublishVedioView, AMapLocationListener {




    //定位需要的声明
    private AMapLocationClient mLocationClient = null;//定位发起端
    private AMapLocationClientOption mLocationOption = null;//定位参数


    //标识，用于判断是否只显示一次定位信息和用户重新定位
    private boolean isFirstLoc = true;


    @BindView(R.id.publish_back)
    ImageView publishBack;
    @BindView(R.id.pubilish_cover)
    ImageView pubilishCover;
    @BindView(R.id.tv_set_cover)
    TextView tvSetCover;
    @BindView(R.id.ll_qq_kongjian)
    LinearLayout llQqKongjian;
    @BindView(R.id.ll_friend)
    LinearLayout llFriend;
    @BindView(R.id.iv_suo)
    ImageView ivSuo;
    @BindView(R.id.et_video_dec)
    EditText etVideoDec;
    @BindView(R.id.btn_aite)
    Button btnAite;
    @BindView(R.id.tv_publish_video)
    TextView tvPublishVideo;
    private String url;
    private byte[] firstFrames;
    private FileOutputStream fileOutputStream;
    private double latitude;
    private double longitude;

    @Override
    public PublishPresenter initPresenter() {
        return new PublishPresenter(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_publish_server;
    }

    @Override
    public void initView() {
        setStatus(true);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        url = extras.getString("url");

        firstFrames = extras.getByteArray("firstFrame");
        System.out.println("name====url====="+url);
        pubilishCover.setImageBitmap(BitmapFactory.decodeByteArray(firstFrames,0,firstFrames.length));
       //写入文件
        try {
File file=new File(Environment.getExternalStorageDirectory()+"/firstFrame.jpg");
if(!file.exists())
{
   file.createNewFile();
}
            System.out.println("name========="+file.getName());
            fileOutputStream = new FileOutputStream(file);

            fileOutputStream.write(firstFrames);

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
//            try {
//                fileOutputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        initMap();
    }

    private void initMap() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();



    }

    @Override
    protected void onResume() {
        super.onResume();

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
        String s = data.toString();
        if("0".equals(s))
{
   showToast("发表成功");
   start(PublishSucActivity.class,true);
}
else if("1".equals(s))
        {
            showToast("发表失败");
        }
        else if("2".equals(s))
        {
            Intent intent = new Intent(this, OtherRegActivity.class);
            intent.putExtra("publish",true);
            startActivityForResult(intent,1000);
        }
    }


    @OnClick({R.id.publish_back, R.id.pubilish_cover, R.id.tv_set_cover, R.id.ll_qq_kongjian, R.id.ll_friend, R.id.btn_aite, R.id.tv_publish_video})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.publish_back:
                finish();
                break;
            case R.id.pubilish_cover:
                break;
            case R.id.tv_set_cover:
                break;
            case R.id.ll_qq_kongjian:
                break;
            case R.id.ll_friend:
                break;
            case R.id.btn_aite:
                break;
            case R.id.tv_publish_video:
                String uid = (String) SharePrefrenceUtils.getData(SharePrefrenceBack.String, "uid");
                if(!TextUtils.isEmpty(uid))
                {
                    File videoFile=new File(url);
                    File coverFile=new File(Environment.getExternalStorageDirectory()+"/firstFrame.jpg");
                    if(!videoFile.exists())
                    {
                       showToast("video文件有误");
                    }
                    else if(!coverFile.exists()){
                        showToast("cover文件有误");
                    }
                    else {
                        if(latitude!=0&&longitude!=0)
                        {
                            String trim = etVideoDec.getText().toString().trim();
                            presenter.getPublishVideo(uid,videoFile,coverFile,trim,latitude+"",longitude+"");
                        }

                    }
                }
                else {

                }
                break;
        }
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见官方定位类型表
                //获取纬度
                latitude = amapLocation.getLatitude();
                //获取经度
                longitude = amapLocation.getLongitude();
                amapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(amapLocation.getTime());
                df.format(date);//定位时间

                String address = amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                String country = amapLocation.getCountry();//国家信息
                String province = amapLocation.getProvince();//省信息
                String city = amapLocation.getCity();//城市信息
                String district = amapLocation.getDistrict();//城区信息
                String street = amapLocation.getStreet();//街道信息
                String streetNum = amapLocation.getStreetNum();//街道门牌号信息
                String cityCode = amapLocation.getCityCode();//城市编码
                String adCode = amapLocation.getAdCode();//地区编码
                System.out.println("纬度============"+latitude+"经度======="+longitude);

            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());

                Toast.makeText(getApplicationContext(), "定位失败", Toast.LENGTH_LONG).show();
            }
        }



    }
}
