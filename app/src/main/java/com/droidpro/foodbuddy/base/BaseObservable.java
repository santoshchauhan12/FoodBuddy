package com.droidpro.foodbuddy.base;

import android.support.annotation.NonNull;

import com.droidpro.foodbuddy.network.FoodBuddyRestApi;
import com.droidpro.foodbuddy.network.RetrofitProvider;

import rx.Observable;
import rx.Single;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by deepak on 22/4/17.
 * Base observable class.
 */

public class BaseObservable {

    protected FoodBuddyRestApi mRestReadApi = RetrofitProvider.getInstance().getReadRestApi();
    protected FoodBuddyRestApi mRestWriteApi = RetrofitProvider.getInstance().getWriteRestApi();

    @NonNull
    protected <T> Observable.Transformer<T, T> applyCommonSchedulers() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(@NonNull Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    @NonNull
    protected <T> Single.Transformer<T, T> applyCommonSchedulersSingle() {
        return new Single.Transformer<T, T>() {
            @Override
            public Single<T> call(@NonNull Single<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
