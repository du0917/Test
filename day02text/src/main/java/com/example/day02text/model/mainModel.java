package com.example.day02text.model;

import com.example.day02text.ApiService;
import com.example.day02text.Bean;
import com.example.day02text.RecentBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 雪碧 on 2019/11/19.
 */

public class mainModel {
    public static void getData(final ResultCallback resultCallback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        retrofit.create(ApiService.class)
                .getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bean recentBean) {
                        resultCallback.onSuccess(recentBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        resultCallback.onFail(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
