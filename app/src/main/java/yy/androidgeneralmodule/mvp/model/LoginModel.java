package yy.androidgeneralmodule.mvp.model;

import io.reactivex.Flowable;
import yy.androidgeneralmodule.mvp.LoginContract;
import yy.androidgeneralmodule.mvp.bean.BaseObjectBean;

/**
 * Created by YY on 2019/6/15.
 * LoginModel
 */

public class LoginModel implements LoginContract.Model {

    @Override
    public Flowable<BaseObjectBean> login(String userName, String password) {
        //网络请求
        return null;
    }
}
