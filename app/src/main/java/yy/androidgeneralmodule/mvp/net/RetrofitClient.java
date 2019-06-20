package yy.androidgeneralmodule.mvp.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by YY on 2019/6/15.
 */

public class RetrofitClient {
    private ApiServer mApiServer;

    private String baseUrl = "http://www.wanandroid.com/";

    private RetrofitClient() {
    }

    public static RetrofitClient getInstance() {
        return RetrofitClientHolder.sInstance;
    }

    //静态内部类
    private static class RetrofitClientHolder {
        private static final RetrofitClient sInstance = new RetrofitClient();
    }

    /**
     * 设置 Header
     *
     * @return
     */
    private Interceptor getHeaderInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder builder = original.newBuilder()
                        //添加Token
                        .header("token", "");
                Request request = builder.build();
                return chain.proceed(request);
            }
        };
    }

    private Interceptor getInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        //显示日志
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }
}
