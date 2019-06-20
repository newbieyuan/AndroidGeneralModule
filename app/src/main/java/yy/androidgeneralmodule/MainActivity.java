package yy.androidgeneralmodule;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import yy.androidgeneralmodule.baseUi.UIActivity;
import yy.androidgeneralmodule.softwareUpgrade.SilentInstall;

public class MainActivity extends AppCompatActivity {
    SilentInstall mInstall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInstall = new SilentInstall(this);
    }

    public void silentInstall(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String copyPath = Environment.getExternalStorageDirectory() + "/App.apk";
                mInstall.copyApkFromAssets(copyPath);
                mInstall.install(copyPath);
            }
        }).start();
    }

    public void baseUi(View view) {
        startActivity(new Intent(this, UIActivity.class));
    }

    public void shutdown(View view) {
        mInstall.shutdown();
    }
}
