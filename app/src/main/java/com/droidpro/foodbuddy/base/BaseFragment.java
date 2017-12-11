package com.droidpro.foodbuddy.base;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

/**
 * Created by deepak on 19/4/17.
 * All application fragments has to extend base fragment
 */

public class BaseFragment extends Fragment {

    private static final int BANNER_ANIMATION_DURATION = 1000;
    private static final int BANNER_TIME = 7000;
    private View mBannerView;
    //private CustomTextView mTxtBanner;
    private String mBannerMessage;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*mBannerView = LayoutInflater.from(view.getContext()).inflate(R.layout.layout_banner, null, false);
        mTxtBanner = (CustomTextView) mBannerView.findViewById(R.id.txt_banner);*/
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    protected boolean checkForPermissions(@NonNull final String permission,
                                          final int requstCode, final String type, @NonNull Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            final int hasPermission = activity.checkSelfPermission(permission);
            if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                if (!activity.shouldShowRequestPermissionRationale(permission)) {

                    requestPermissions(new String[]{permission}, requstCode);

                } else {
                    requestPermissions(new String[]{permission}, requstCode);
                }
                return false;
            }
            return true;
        }
        return true;
    }

    public boolean onBackPressed() {
        Log.i("base fragment", "added log on back pressed");
        return false;
    }

}
