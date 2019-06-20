package yy.androidgeneralmodule.uitl.log;

import android.text.TextUtils;
import android.util.Log;


import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import yy.androidgeneralmodule.uitl.FileUtils;

public class LogUtil {
    public static boolean isLog = true;//设置是否打开日志
    static String TAG = "info";
    // 用于格式化日期,作为日志文件名的一部分
    private static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String PATH_LOG = "";

    public static void v(String msg) {
        if (isLog) {
            v(TAG, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (isLog) {
            Log.v(tag, msg);
        }
    }

    public static void d(String msg) {
        if (isLog) {
            d(TAG, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (isLog)
            Log.d(tag, msg);
    }

    public static void i(String msg) {
        if (isLog) {
            i(TAG, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (isLog)
            Log.i(tag, msg);
    }

    public static void e(String msg) {
        if (isLog) {
            e(TAG, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isLog)
            Log.e(tag, msg);
    }

    public static void e(String tag, String msg, Exception e) {
        if (isLog)
            Log.e(tag, msg, e);
    }

    public static void elog(String msg) {
        e(msg);
        writeFile(msg);
        if (isLog) {
            e(TAG, msg);
        }
    }

    public static String writeFile(String datas) {
        String time = formatter.format(new Date());
        String fileName = "/log-" + time + ".txt";
        try {
            String path = PATH_LOG;
            File dir = new File(path);
            if (!dir.exists())
                dir.mkdirs();
            File dir2 = new File(path + fileName);
            if (!dir2.exists())
                dir2.createNewFile();

            FileWriter fw = new FileWriter(path + fileName, true);
            //写入数据并换行
            fw.write(datas + "\r\n\n\n\n\n\n\n");
            fw.close();
            FileUtils.deleteOverFile(PATH_LOG);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "****Save Error****");
        }
        return fileName;
    }

    public static void writeFile(String fileName, String datas) {
        if (TextUtils.isEmpty(fileName)) return;
        try {
            String path = PATH_LOG;
            File dir = new File(path);
            if (!dir.exists())
                dir.mkdirs();
            File dir2 = new File(path + File.pathSeparator + fileName);
            if (!dir2.exists())
                dir2.createNewFile();

            FileWriter fw = new FileWriter(path + fileName, true);
            //写入数据并换行
            fw.write(datas + "\r\n\n\n\n\n\n\n");
            fw.close();
            FileUtils.deleteOverFile(PATH_LOG);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "****Save Error****");
        }
    }
}
