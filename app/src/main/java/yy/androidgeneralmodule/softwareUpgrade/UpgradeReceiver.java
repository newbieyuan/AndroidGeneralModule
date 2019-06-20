package yy.androidgeneralmodule.softwareUpgrade;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import yy.androidgeneralmodule.MainActivity;

/**
 * Created by YY on 2019/5/14.
 */

public class UpgradeReceiver extends BroadcastReceiver {
    private static final String TAG = UpgradeReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        //PACKAGE_REPLACED —-替换apk
        if (intent.getAction().equals("android.intent.action.PACKAGE_REPLACED")) {
            Log.e(TAG, "升级了一个安装包，重新启动此程序");
            Intent intent2 = new Intent(context, MainActivity.class);
            intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent2);
        }
        //接收安装广播
        //PACKAGE_ADDED—添加apk
        if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
            String packageName = intent.getDataString();
            Log.e(TAG, "安装了:" + packageName + "包名的程序");
//            Intent intent1 = intent.setClassName("com.landwell.keyboxonline", "com.landwell.keyboxonline.MainActivityz");
//            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Intent intent1 = context.getPackageManager().getLaunchIntentForPackage("com.landwell.keyboxonline");
            context.startActivity(intent1);
        }
        //接收卸载广播
        //PACKAGE_REMOVED–删除apk
        if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
            String packageName = intent.getDataString();
            Log.e(TAG, "卸载了:" + packageName + "包名的程序");
        }
    }
}
