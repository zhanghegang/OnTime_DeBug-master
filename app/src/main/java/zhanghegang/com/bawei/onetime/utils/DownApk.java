package zhanghegang.com.bawei.onetime.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import org.greenrobot.greendao.query.QueryBuilder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import zhanghegang.com.bawei.onetime.MyApp;
import zhanghegang.com.bawei.onetime.bean.VerSionDownTable;
import zhanghegang.com.bawei.onetime.bean.VerSionDownTableDao;

/**
 * current package:zhanghegang.com.bawei.onetime.utils
 * Created by Mr.zhang
 * date: 2017/12/15
 * decription:开发
 */

public class DownApk {
    private final File file;
    private String TAGDOWN="DOWNFILE";
    private String url;
    private int threadCount;
    private Context context;
    private OkHttpClient okHttpClient;
    private long contentLength;

    public DownApk(String url, int threadCount, Context context,File file) {
        this.url = url;
        this.threadCount = threadCount;
        this.context = context;
        this.file=file;
    }

    public void downNewVersion(){
       getOkHttpClient();
        //开始请求初始值
        if(!TextUtils.isEmpty(url))
        {
        Request.Builder request=new Request.Builder().url(url).get();
            Request initRequest = request.build();
            Call call = okHttpClient.newCall(initRequest);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println(TAGDOWN+e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    int code = response.code();
                    if(code==200)
                    {
                        contentLength = response.body().contentLength();
                        if(onBackProCess!=null)
                        {
                            System.out.println(TAGDOWN+"contentLenth=========="+ contentLength);
                            onBackProCess.onContentLenth((int) contentLength);
                        }
                        long blockSize = contentLength / threadCount;
                        RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rwd");
                        randomAccessFile.setLength(contentLength);
                        randomAccessFile.close();
                        VerSionDownTableDao verSionDownTableDao = MyApp.getIntence().getDaoSession().getVerSionDownTableDao();
                        for (int i = 0; i < threadCount; i++) {
                            int startIndex =0;
                            int endIndex=0;
                            startIndex= (int) (blockSize*i);
                            endIndex= (int) (blockSize*(i+1))-1;
                            if(i==threadCount-1)
                            {
                                endIndex= (int) (contentLength -1);
                            }
                            VerSionDownTable load = verSionDownTableDao.load((long) (i+1));
//                            Long id = load.getId();
//                            System.out.println("数据库===id======="+id);
                            if(load!=null)
                            {
                                System.out.println("数据库=========="+startIndex+"========"+endIndex);
                                startIndex = load.getStartIndex();
                               endIndex=load.getEndIndex();
                            }
                          downBolock(startIndex,endIndex,"thread"+i,i+1);

                        }
                    }
                }
            });
        }
        else {
            System.out.println(TAGDOWN+"DownFile逗我呢，没地址");
        }


    }
    private long allSize=0;
    public void downBolock(final int startIndex, final int endIndex, final String name, final int key){
        OkHttpClient okHttpClient = getOkHttpClient();
        final Request.Builder request=new Request.Builder().header("RANGE","bytes="+startIndex+"-"+endIndex).url(url);
        Request bolockRequest = request.build();
        final VerSionDownTableDao verSionDownTableDao = MyApp.getIntence().getDaoSession().getVerSionDownTableDao();
        VerSionDownTable load = verSionDownTableDao.load((long) key);
        if(load==null)
        {
            VerSionDownTable version=new VerSionDownTable();
            version.setStartIndex(startIndex);
            version.setEndIndex(endIndex);
            version.setThreadId(name);
            verSionDownTableDao.insert(version);
        }
        okHttpClient.newCall(bolockRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(TAGDOWN+e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                int code = response.code();
                if(code!=206){
                    System.out.println(TAGDOWN+"请求失败");
                }
                else {
                    RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rwd");
                    randomAccessFile.seek(startIndex);
                    InputStream inputStream = response.body().byteStream();
                    byte[] buffer=new byte[64];
                    int len=-1;
                    int total=0;
                    while ((len=inputStream.read(buffer))!=-1)
                    {

                        total+=len;
                        allSize+=len;
                        System.out.println(TAGDOWN+"total-----"+total);
                        if(onBackProCess!=null)
                        {


                            onBackProCess.onBack(len);
                        }
                        if(pause)
                        {
                            VerSionDownTable version=new VerSionDownTable();
                            version.setId((long) key);
                            version.setStartIndex(startIndex+total);
                            version.setEndIndex(endIndex);
                            version.setThreadId(name);
                            verSionDownTableDao.update(version);
                            return;
                        }
                        //返回字节数

                        randomAccessFile.write(buffer,0,len);

                    }
                    randomAccessFile.close();

                }
            }
        });

    }
    public OnBackProCess onBackProCess;
    public void setOnBackProCess(OnBackProCess onBackProCess){
        this.onBackProCess=onBackProCess;
    }
    public interface OnBackProCess{
        void onBack(int current);
        void onContentLenth(int contentLenth);
    }

    public void onPause(boolean flag){
        pause=flag;
    }

    public boolean pause=false;
    public OkHttpClient getOkHttpClient(){
        HttpLoggingInterceptor.Logger logger=new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("DOWN********",message+"*************");
            }
        };
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(logger);
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder=new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor);
        okHttpClient = builder.build();
        return okHttpClient;
    }
}
