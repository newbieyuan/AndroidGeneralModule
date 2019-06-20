package yy.androidgeneralmodule.mvp.base;

/**
 * Created by YY on 2019/6/15.
 * BasePresenter
 */

public class BasePresenter<V extends BaseView> {
    private V mView;

    /**
     * 绑定View 一般在初始化中调用
     *
     * @param view 视图控件
     */
    public void attachView(V view) {
        this.mView = view;
    }

    /**
     * 解除绑定View 一般在Destroy中调用
     */
    public void detachView() {
        this.mView = null;
    }

    /**
     * View 是否绑定
     *
     * @return 返回View 的绑定状态
     */
    public boolean isViewAttached() {
        return mView != null;
    }
}
