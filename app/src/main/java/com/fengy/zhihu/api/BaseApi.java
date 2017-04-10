package com.fengy.zhihu.api;

import android.support.annotation.NonNull;
import android.util.Log;

import org.reactivestreams.Publisher;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Cancellable;
import io.reactivex.internal.schedulers.IoScheduler;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by fengyun on 2017/4/10.
 */

public class BaseApi {

    private static final String TAG = "BaseComApi";

    /**
     * @param call
     * @param <T>
     * @return
     */
    public static <T> Flowable<T> create(@NonNull final Call<T> call) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(final FlowableEmitter<T> e) throws Exception {
                //设置取消监听
                e.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Exception {
                        Log.e(TAG, "cancel: ");
                        if (!call.isCanceled()) {
                            call.cancel();
                        }
                    }
                });

                //同步执行请求，把线程管理交给Rx
                try {
                    Response<T> response = call.execute();
                    Log.e(TAG, "onResponse: ");
                    if (!e.isCancelled()) {
                        e.onNext(response.body());
                        e.onComplete();
                    }
                } catch (Exception exception) {
                    Log.e(TAG, "exception with: exception = [" + exception.getMessage() + "]");
                    if (!e.isCancelled()) {
                        Log.e(TAG, "onResponse: no cancel");
                        e.onError(exception);
                        e.onComplete();
                    }
                }
            }
        }, BackpressureStrategy.BUFFER);
    }


    /**
     * 后台线程执行同步，主线程执行异步操作
     * 拦截所有错误，不让app崩溃
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> background() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream.subscribeOn(new IoScheduler())
                        .observeOn(AndroidSchedulers.mainThread())
                        .onErrorResumeNext(Flowable.<T>empty());
            }
        };
    }

}