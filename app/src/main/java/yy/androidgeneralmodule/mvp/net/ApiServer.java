package yy.androidgeneralmodule.mvp.net;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import yy.androidgeneralmodule.mvp.bean.BaseObjectBean;
import yy.androidgeneralmodule.mvp.bean.Login;

/**
 * Created by YY on 2019/6/15.
 */

public interface ApiServer {
    /**
     * 登陆接口
     *
     * @param userName
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("user/login")
    Flowable<BaseObjectBean<Login>> login(@Field("username") String userName, @Field("password") String password);
}
