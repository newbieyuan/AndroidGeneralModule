package yy.androidgeneralmodule;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by YY on 2019/5/15.
 * Activity 管理类
 */

public class ActivityManger {
    /**
     * 存放activity的列表
     */
    public static HashMap<Class<?>, Activity> activities = new LinkedHashMap<>();

    private ActivityManger() {
    }

    public static ActivityManger getInstance() {
        return ActivityMangerHolder.sInstance;
    }

    //静态内部类
    private static class ActivityMangerHolder {
        private static final ActivityManger sInstance = new ActivityManger();
    }

    /**
     * 添加Activity
     *
     * @param activity
     */
    public void addActivity(Activity activity, Class<?> clz) {
        activities.put(clz, activity);
    }

    /**
     * 判断一个Activity 是否存在
     *
     * @param clz
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public <T extends Activity> boolean isActivityExist(Class<T> clz) {
        boolean res;
        Activity activity = getActivity(clz);
        if (activity == null) {
            res = false;
        } else {
            if (activity.isFinishing() || activity.isDestroyed()) {
                res = false;
            } else {
                res = true;
            }
        }

        return res;
    }

    /**
     * 获得指定activity实例
     *
     * @param clazz Activity 的类对象
     * @return
     */
    public <T extends Activity> T getActivity(Class<T> clazz) {
        return (T) activities.get(clazz);
    }

    /**
     * 移除activity,代替finish
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (activities.containsValue(activity)) {
            activities.remove(activity.getClass());
        }
    }

    /**
     * 移除所有的Activity
     */
    public void exitSystem() {
        if (activities != null && activities.size() > 0) {
            Set<Map.Entry<Class<?>, Activity>> sets = activities.entrySet();
            for (Map.Entry<Class<?>, Activity> s : sets) {
                if (!s.getValue().isFinishing()) {
                    s.getValue().finish();
                }
            }
        }
        activities.clear();
        // 退出进程
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
