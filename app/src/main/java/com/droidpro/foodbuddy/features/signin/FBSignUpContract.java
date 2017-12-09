package com.droidpro.foodbuddy.features.signin;

import com.droidpro.foodbuddy.base.BasePresenter;
import com.droidpro.foodbuddy.base.BaseView;

public interface FBSignUpContract {

    interface View extends BaseView {
        void onFBSignUpSuccess();

        void onFBSignUpFailed(String errorMessage);
    }

    interface Presenter extends BasePresenter<View> {
        void signUpWithFB();
    }

    interface Interactor {
        void fbSignUp(FBSignUpContract.Interactor.FBSignUpListener listener);

        interface FBSignUpListener {
            void success();

            void failed();
        }
    }
}