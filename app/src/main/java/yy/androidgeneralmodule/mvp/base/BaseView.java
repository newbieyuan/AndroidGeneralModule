package yy.androidgeneralmodule.mvp.base;

/**
 * Created by YY on 2019/6/15.
 * BaseView
 */

public interface BaseView {
    /**
     * 显示加载对话框
     */
    void showLoading();

    /**
     * 隐藏加载对话框
     */
    void hideLoading();

    /**
     * 数据获取失败
     *
     * @param throwable 异常信息
     */
    void onError(Throwable throwable);

//    /**
//     * 绑定Android生命周期,防止RxJava内存泄漏
//     * @param <T>
//     * @return
//     */
//    <T> AutoDisposeConverter<T> bindAutoDispose();
}
