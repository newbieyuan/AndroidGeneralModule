package yy.androidgeneralmodule.softwareUpgrade;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;

/**
 * Created by YY on 2019/5/14.
 * 静默安装的实现类，调用install()方法执行具体的静默安装逻辑。
 * 原文地址：http://blog.csdn.net/guolin_blog/article/details/47803149
 *
 * @author guolin
 * @since 2015/12/7
 */

public class SilentInstall {
    private static final String TAG = SilentInstall.class.getSimpleName();

    private Context mContext;

    public SilentInstall(Context context) {
        mContext = context;
    }

    /**
     * 判断手机是否有root权限
     */
    public boolean hasRootPermission() {
        PrintWriter PrintWriter = null;
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("su");
            PrintWriter = new PrintWriter(process.getOutputStream());
            PrintWriter.flush();
            PrintWriter.close();
            int value = process.waitFor();
            return returnResult(value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
        return false;
    }

    public boolean install(String apkPath) {
        // 判断是否有root权限
        if (hasRootPermission()) {
            // 有root权限，利用静默安装实现
            return clientInstall(apkPath);
        } else {
            // 没有root权限，利用意图进行安装
            File file = new File(apkPath);
            if (!file.exists())
                return false;
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            mContext.startActivity(intent);
            return true;
        }
    }

    /**
     * 关机
     *
     * @return
     */
    public boolean shutdown() {
        // 判断是否有root权限
        if (hasRootPermission()) {
            boolean result = false;
            DataOutputStream dataOutputStream = null;
            BufferedReader errorStream = null;
            try {
                // 申请su权限
                Process process = Runtime.getRuntime().exec("su");
                dataOutputStream = new DataOutputStream(process.getOutputStream());
                // 执行pm install命令
                String command = "reboot -p\n";
                dataOutputStream.write(command.getBytes(Charset.forName("utf-8")));
                dataOutputStream.flush();
                dataOutputStream.writeBytes("exit\n");
                dataOutputStream.flush();
                process.waitFor();
                errorStream = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                String msg = "";
                String line;
                // 读取命令的执行结果
                while ((line = errorStream.readLine()) != null) {
                    msg += line;
                }
                Log.d("TAG", "reboot msg is " + msg);
                // 如果执行结果中包含Failure字样就认为是安装失败，否则就认为安装成功
                if (!msg.contains("Failure")) {
//                Intent intent1=mContext.getPackageManager().getLaunchIntentForPackage("com.landwell.keyboxonline");
//                mContext.startActivity(intent1);
                    result = true;
                }
            } catch (Exception e) {
                Log.e("TAG", e.getMessage(), e);
            } finally {
                try {
                    if (dataOutputStream != null) {
                        dataOutputStream.close();
                    }
                    if (errorStream != null) {
                        errorStream.close();
                    }
                } catch (IOException e) {
                    Log.e("TAG", e.getMessage(), e);
                }
            }
            return result;
        }
        return false;
    }

    /**
     * 执行具体的静默安装逻辑，需要手机ROOT。
     *
     * @param apkPath 要安装的apk文件的路径
     * @return 安装成功返回true，安装失败返回false。
     */
    public boolean clientInstall(String apkPath) {
        boolean result = false;
        DataOutputStream dataOutputStream = null;
        BufferedReader errorStream = null;
        try {
            // 申请su权限
            Process process = Runtime.getRuntime().exec("su");
            dataOutputStream = new DataOutputStream(process.getOutputStream());
            // 执行pm install命令
            String command = "pm install -r " + apkPath + "\n";
            dataOutputStream.write(command.getBytes(Charset.forName("utf-8")));
            dataOutputStream.flush();
            dataOutputStream.writeBytes("exit\n");
            dataOutputStream.flush();
            process.waitFor();
            errorStream = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String msg = "";
            String line;
            // 读取命令的执行结果
            while ((line = errorStream.readLine()) != null) {
                msg += line;
            }
            Log.d("TAG", "install msg is " + msg);
            // 如果执行结果中包含Failure字样就认为是安装失败，否则就认为安装成功
            if (!msg.contains("Failure")) {
//                Intent intent1=mContext.getPackageManager().getLaunchIntentForPackage("com.landwell.keyboxonline");
//                mContext.startActivity(intent1);
                result = true;
            }
        } catch (Exception e) {
            Log.e("TAG", e.getMessage(), e);
        } finally {
            try {
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                if (errorStream != null) {
                    errorStream.close();
                }
            } catch (IOException e) {
                Log.e("TAG", e.getMessage(), e);
            }
        }
        return result;
    }

    public void copyApkFromAssets(String path) {
        byte[] data = new byte[1024];
        OutputStream fos = null;
        File file = new File(path);
        InputStream is;
        if (file.exists()) {
            Log.e(TAG, "文件已存在");
            file.delete();
        }
        try {
            is = mContext.getAssets().open("app-release.apk");
            Log.e(TAG, file.getPath());
            fos = new FileOutputStream(file);
            int length;
            while ((length = is.read(data)) != -1) {
                fos.write(data, 0, length);
            }
            fos.flush();
            is.close();
            Log.e(TAG, "文件copy完成");
        } catch (IOException e) {
            Log.e(TAG, "copy file error!");
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private static boolean returnResult(int value) {
        // 代表成功
        if (value == 0) {
            return true;
        } else if (value == 1) { // 失败
            return false;
        } else { // 未知情况
            return false;
        }
    }
}
