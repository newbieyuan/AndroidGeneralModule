package yy.androidgeneralmodule.baseUi;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import yy.androidgeneralmodule.ActivityManger;

/**
 * BaseActivity是所有Activity的基类，把一些公共的方法放到里面，如基础样式设置，权限封装，网络状态监听等
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
//    public static NetBroadcastReceiver.NetChangeListener netEvent;// 网络状态改变监听事件
    /***获取TAG的activity名称**/
    protected final String TAG = this.getClass().getSimpleName();
    /**
     * 是否禁止旋转屏幕
     **/
    private boolean isAllowScreenRoate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_base);
        // 隐藏标题栏
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();

        // 沉浸效果
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        //设置屏幕显示方式:横屏 or 竖屏
        if (!isAllowScreenRoate) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        // 添加到Activity工具类
        ActivityManger.getInstance().addActivity(this, this.getClass());

        // 初始化netEvent
//        netEvent = this;
    }

    // 抽象 - 初始化方法，可以对数据进行初始化
    protected abstract void initView();

    protected abstract void initData();

    protected Context getContext() {
        return this;
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        initView();
        initData();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Resources resources = this.getResources();
//        Configuration configuration = resources.getConfiguration();
//        configuration.fontScale = 1;
//        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    @Override
    protected void onDestroy() {
        // Activity销毁时，提示系统回收
        // System.gc();
//        netEvent = null;
        // 移除Activity
        ActivityManger.getInstance().removeActivity(this);
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 点击手机上的返回键，返回上一层
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 移除Activity
            ActivityManger.getInstance().removeActivity(this);
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 权限检查方法，false代表没有该权限，ture代表有该权限
     */
    public boolean hasPermission(String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }


    /**
     * 权限请求方法
     */
    public void requestPermission(int code, String... permissions) {
        ActivityCompat.requestPermissions(this, permissions, code);
    }

    /**
     * 处理请求权限结果事件
     *
     * @param requestCode  请求码
     * @param permissions  权限组
     * @param grantResults 结果集
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doRequestPermissionsResult(requestCode, grantResults);
    }

    /**
     * 处理请求权限结果事件
     *
     * @param requestCode  请求码
     * @param grantResults 结果集
     */
    public void doRequestPermissionsResult(int requestCode, @NonNull int[] grantResults) {
    }

    private boolean fastClick() {
        long lastClick = 0;
        if (System.currentTimeMillis() - lastClick <= 1000) {
            return false;
        }
        lastClick = System.currentTimeMillis();
        return true;
    }

    /**
     * View点击
     **/
    public abstract void widgetClick(View v);

    @Override
    public void onClick(View v) {
        if (fastClick())
            widgetClick(v);
    }
}
