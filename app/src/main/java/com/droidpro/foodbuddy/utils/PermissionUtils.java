package com.droidpro.foodbuddy.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

/**
 * Created by santosh on 24/4/17.
 * Utility class for handling marshamallow permission.
 */

public class PermissionUtils {

    public static boolean checkForPermissions(@NonNull final String permission,
                                              final int requstCode, final String type, @NonNull Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            final int hasPermission = activity.checkSelfPermission(permission);
            if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                if (!activity.shouldShowRequestPermissionRationale(permission)) {
                    ActivityCompat.requestPermissions(activity, new String[]{permission}, requstCode);

                } else {
                    ActivityCompat.requestPermissions(activity, new String[]{permission}, requstCode);
                }
                return false;
            }
            return true;
        }
        return true;
    }
}
