package com.droidpro.foodbuddy.base;

/**
 * Created by Nagendra on 18/04/17
 */

public interface RxSubscriberEvents<T> extends RxErrorEvents {

    void onNext(T data);

    void onCompleted();

}
