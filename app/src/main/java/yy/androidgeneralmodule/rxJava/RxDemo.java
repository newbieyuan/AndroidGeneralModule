package yy.androidgeneralmodule.rxJava;


import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by YY on 2019/6/14.
 * 测试使用RxJava
 */

public final class RxDemo {

    private final String TAG = this.getClass().getSimpleName();

    private boolean state;

    /**
     * 创建 Observer
     * Observer 即观察者，它决定事件触发的时候将有怎样的行为
     */
    private void demo() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.w(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(String s) {
                Log.w(TAG, "onNext: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.w(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.w(TAG, "onComplete: ");
            }
        };

        Subscriber<String> stringSubscriber = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                Log.w(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(String s) {
                Log.w(TAG, "onNext: ");
            }

            @Override
            public void onError(Throwable t) {
                Log.w(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.w(TAG, "onComplete: ");
            }
        };
    }

//    private void observable(final Activity context){
//        Observable.just(getDrawableFromNet())
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Drawable>() {
//                    @Override
//                    public void accept(Drawable drawable) throws Exception {
//                        ((ImageView)context.findViewById(R.id.imageView)).setImageDrawable(drawable);
//                    }
//                });
//    }

    public List<Object> getDrawableFromNet() {

        return new ArrayList<>();
    }
}
