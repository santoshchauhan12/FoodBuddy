package com.droidpro.foodbuddy.base;

/**
 * Created by Nagendra on 4/21/17.
 */

public interface RxSingleSubscriberEvents<T> extends RxErrorEvents {
    void onSuccess(T value);
}
