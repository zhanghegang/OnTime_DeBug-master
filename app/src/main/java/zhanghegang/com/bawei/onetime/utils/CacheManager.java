package zhanghegang.com.bawei.onetime.utils;

import android.content.Context;

import java.io.File;

/**
 * current package:zhanghegang.com.bawei.onetime.utils
 * Created by Mr.zhang
 * date: 2017/12/11
 * decription:开发
 */

public class CacheManager {
    public static String clearCacheFile(Context context){
        File cacheDir = context.getCacheDir();
        String s = deleteDirector(cacheDir);
        return s;
    }
    public static String deleteDirector(File wileDelete){
        if(wileDelete!=null&&wileDelete.exists()&&wileDelete.isDirectory())
        {
            for (File file : wileDelete.listFiles()) {
                System.out.println("fileName========"+file.getName());
                if(file.isDirectory())
                {
                    deleteDirector(file);
                }
                else {
                    file.delete();
                }

            }
            return "0";
        }
        else {
            return "你传的肯定不是文件夹，或者文件夹不存在";
        }
    }
    public static long getFileSize(File file){
        long size=0;
        try {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if(files[i].isDirectory())
                {
                    size=size+getFileSize(files[i]);
                }
                else {
                    size+=files[i].length();
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return size;

    }
}
