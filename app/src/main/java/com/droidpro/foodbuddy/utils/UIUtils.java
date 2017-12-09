package com.droidpro.foodbuddy.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;


import com.droidpro.foodbuddy.FoodBuddyApp;
import com.droidpro.foodbuddy.R;
import com.droidpro.foodbuddy.intr.IntrDialogClickListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by deepak on 19/4/17.
 * All ui related utils will come in this class.
 */

public class UIUtils {


    private static String TAG = UIUtils.class.getSimpleName();
    @Nullable
    private static ProgressDialog sProgressDialog;


    /**
     * To show progress and block the user
     *
     * @param activity : activity
     */
    public static void showProgress(@Nullable Activity activity) {

        if (null != activity) {
            if (null == sProgressDialog) {
                sProgressDialog = new ProgressDialog(activity);
                sProgressDialog.setProgressStyle(R.style.FoodBuddyProgressTheme);
            }
            sProgressDialog.setIndeterminate(true);
            sProgressDialog.setCancelable(false);
            if (sProgressDialog.getWindow() != null) {
                sProgressDialog.getWindow()
                        .setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            }

            try {
                if (!sProgressDialog.isShowing())
                    sProgressDialog.show();
            } catch (WindowManager.BadTokenException e) {
                Log.d(TAG, "WindowManager.BadTokenException:" + e);
            }
            sProgressDialog.setContentView(R.layout.layout_progress_dialog);

        }
    }

    /**
     * To dismiss the progress
     */
    public static void hideProgress() {
        if (null != sProgressDialog) {
            sProgressDialog.dismiss();

            sProgressDialog = null;
        }
    }

    public static int getColor(int id) {
        Context context = FoodBuddyApp.getInstance();
        int color = id;
        if (null != context) {
            color = ContextCompat.getColor(context, id);
        }
        return color;
    }


    /**
     * @param view Show keyboard when view is in focus
     */
    public static void showKeyboard(@Nullable View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);

        }
    }

    /**
     * This method hides the soft keyboard.
     *
     * @param view view of the calling method
     */
    public static void hideKeyboard(@Nullable View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void hideKeyboard(@NonNull Activity activity) {
        // Check if no view has focus:
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    /**
     * Converts passed dp value to pixels.
     *
     * @param dp dp to be converted in pixel
     */
    public static float convertDpToPixel(float dp) {
        Context context = FoodBuddyApp.getInstance();
        if (context != null) {
            Resources resources = context.getResources();
            DisplayMetrics metrics = resources.getDisplayMetrics();
            return dp * (metrics.densityDpi / 160f);
        }
        return 0;
    }

    @NonNull
    public static String getString(int string) {
        Context context = FoodBuddyApp.getInstance();
        String str = "";
        if (null != context) {
            str = context.getString(string);
        }
        return str;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    /*
     * This method is used to get drawables
     */
    public static Drawable getDrawable(int resId) {
        //noinspection deprecation
        return FoodBuddyApp.getInstance().getResources().getDrawable(resId);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    /*
     * This method is used to get drawables
     *
     */
    public static Drawable getDrawable(int resId, @NonNull Context context) {
        //noinspection deprecation
        return ContextCompat.getDrawable(context, resId);
    }

    /**
     * This method is used to get color from resource id
     *
     * @param resId Resource ID
     * @return int
     */
    @SuppressWarnings("deprecation")
    public static int getColor(int resId, @NonNull Context context) {
        try {
            int version = Build.VERSION.SDK_INT;
            if (version >= 23) {
                return ContextCompat.getColor(context, resId);

            } else {
                return context.getResources().getColor(resId);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }


    public static String hexToASCII(String hexValue) {
        if (!TextUtils.isEmpty(hexValue)) {
            hexValue = hexValue.replace(" ", "");
            StringBuilder output = new StringBuilder("");
            for (int i = 0; i < hexValue.length(); i += 2) {
                String str = hexValue.substring(i, i + 2);

                output.append((char) Integer.parseInt(str, 16));
            }
            return output.toString();
        }
        return "";
    }

    public static void showAlertDialog(String title, String messege, @NonNull final Context context,
                                       @NonNull final IntrDialogClickListener iDialogButtonClickListener,
                                       @NonNull final String rightButtonText, @NonNull final String leftButtonText) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_custom_permission);
        dialog.setCancelable(false);
        ((TextView) dialog.findViewById(R.id.text_dialog_msg)).setText(messege);
        ((TextView) dialog.findViewById(R.id.text_dialog_title)).setText(title);
        TextView allowTxt = (TextView) dialog.findViewById(R.id.text_btn_positive);
        allowTxt.setText(rightButtonText);
        TextView denyTxt = (TextView) dialog.findViewById(R.id.text_btn_negative);
        denyTxt.setText(leftButtonText);
        allowTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                iDialogButtonClickListener.onDialogOkClicked();
            }
        });
        denyTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                iDialogButtonClickListener.onDialogCancelClicked();

            }
        });
        dialog.show();
    }

    public static void setStatusBarColor(Activity activity, int color, boolean isLightStatusBar) {
        if (activity != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                activity.getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_VISIBLE);
                Window window = activity.getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(color);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    int flags = activity.getWindow().getDecorView().getSystemUiVisibility();
                    if (isLightStatusBar) {
                        flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                    } else {
                        flags &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                    }
                    activity.getWindow().getDecorView().setSystemUiVisibility(flags);
                }
            }
        }
    }

    public static void setTransparentStatusBarWithIconColor(Activity activity, boolean isLightStatusBar) {
        if (activity != null) {
            activity.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(Color.TRANSPARENT);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                int flags = activity.getWindow().getDecorView().getSystemUiVisibility();
                if (isLightStatusBar) {
                    flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                } else {
                    flags &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                }
                activity.getWindow().getDecorView().setSystemUiVisibility(flags);
            }
        }
    }

    public static int getStatusBarHeight(Activity activity) {
        Rect rectangle = new Rect();
        Window window = activity.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
        int statusBarHeight = rectangle.top;
        Log.d(TAG, "StatusBar Height= " + statusBarHeight);
        return rectangle.top;
    }
    public static String getCurrentTime(Activity activity) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat;
        if (DateFormat.is24HourFormat(activity)) {
            simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            return simpleDateFormat.format(calendar.getTime());
        } else {
            simpleDateFormat = new SimpleDateFormat("h:mm a", Locale.getDefault());
            return simpleDateFormat.format(calendar.getTime());
        }
    }

    public static String getFormattedHourMinute(Activity activity, long milliseconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        SimpleDateFormat simpleDateFormat;
        if (DateFormat.is24HourFormat(activity)) {
            simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            return simpleDateFormat.format(calendar.getTime());
        } else {
            simpleDateFormat = new SimpleDateFormat("h:mm a", Locale.getDefault());
            return simpleDateFormat.format(calendar.getTime());
        }
    }
}
