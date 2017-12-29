package zhanghegang.com.bawei.onetime.utils;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.File;

/**
 * current package:zhanghegang.com.bawei.testdebug_release
 * Created by Mr.zhang
 * date: 2017/12/26
 * decription:开发
 */

public class DownFile {
    public static void downLoadAPK(Context context, String url) {

        if (TextUtils.isEmpty(url)) {
            return;
        }

        try {
            String serviceString = Context.DOWNLOAD_SERVICE;
            final DownloadManager downloadManager = (DownloadManager) context.getSystemService(serviceString);

            Uri uri = Uri.parse(url);
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.allowScanningByMediaScanner();
            request.setVisibleInDownloadsUi(true);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setMimeType("application/vnd.android.package-archive");


            File file=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"","book.apk");
            if(file.exists())
            {
                file.delete();
            }
            request.setDestinationInExternalPublicDir("", "book.apk");
            int refernece = (int) downloadManager.enqueue(request);
            System.out.println("========="+"===========refernece====num============"+refernece);
            SharePrefrenceUtils.putData("refernece", refernece);
        } catch (Exception exception) {
            exception.printStackTrace();
            Toast.makeText(context, "更新失败", Toast.LENGTH_SHORT).show();
        }

    }



}
