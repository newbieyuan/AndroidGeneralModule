package yy.androidgeneralmodule.mvp;

import io.reactivex.Flowable;
import yy.androidgeneralmodule.mvp.base.BaseView;
import yy.androidgeneralmodule.mvp.bean.BaseObjectBean;

/**
 * Created by YY on 2019/6/15.
 * Login 合约
 */

public interface LoginContract {
    interface Model {
        Flowable<BaseObjectBean> login(String userName, String password);
    }

    interface View extends BaseView {
        @Override
        void showLoading();

        @Override
        void hideLoading();

        @Override
        void onError(Throwable throwable);

        void onSuccess(BaseObjectBean bean);
    }

    interface Presenter {
        void login(String userName, String password);
    }
}
