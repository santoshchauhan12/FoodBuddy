package com.droidpro.foodbuddy.base;

import android.support.annotation.NonNull;

import rx.SingleSubscriber;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Nagendra on 18/04/17.
 * Base interactor
 */

public  class BaseInteractor<T> implements InteractorCancel{


    private final CompositeSubscription subscriptionQueue = new CompositeSubscription();
    protected Subscription mSubscription;

    @NonNull
    protected <E extends Object> Subscriber<E> getSubscriber(@NonNull final RxSubscriberEvents subscriberEvents) {
        return new Subscriber<E>() {
            @Override
            public void onCompleted() {
                subscriberEvents.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                subscriberEvents.onError(e);
            }

            @Override
            public void onNext(E next) {
                subscriberEvents.onNext(next);
            }
        };
    }

    //SingleSubscriber for better performance!!!
    @NonNull
    protected <E extends Object> SingleSubscriber<E> getSingleSubscriber(@NonNull final RxSingleSubscriberEvents singleSubscriberEvents) {
        return new SingleSubscriber<E>() {

            @Override
            public void onSuccess(E value) {
                singleSubscriberEvents.onSuccess(value);
            }

            @Override
            public void onError(Throwable error) {
                singleSubscriberEvents.onError(error);
            }
        };
    }

    public void queueSubscriptionForRemoval(@NonNull Subscription subscription) {
        subscriptionQueue.add(subscription);
    }

    //Queue multiple subscriptions for removing
    public void queueSubscriptionsForRemoval(@NonNull Subscription... subscriptions) {
        for (Subscription subscription : subscriptions) {
            subscriptionQueue.add(subscription);
        }
    }

    public void clearSubscriptionQueue() {
        subscriptionQueue.clear();
    }


    public void removeSubscription(Subscription subscription) {
        subscriptionQueue.remove(subscription);
    }

    public boolean cancelInteractor() {
        if(null != mSubscription && !mSubscription.isUnsubscribed()) {
          return   mSubscription.isUnsubscribed();
        }
        return false;
    }
}
