package com.droidpro.foodbuddy.base;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

/**
 * Created by deepak on 19/4/17.
 * All application activities are extends by this activity
 * All common things are handling from base activity level
 */

public abstract class BaseActivity extends AppCompatActivity {

    public static final String TAG = BaseActivity.class.getSimpleName();
    private View mBannerView;
    //private CustomTextView mTxtBanner;
    private String mBannerMessage;
    private boolean mIsRemoveAnim = true;
    private static final int BANNER_ANIMATION_DURATION = 1000;
    private static final int BANNER_TIME = 7000;

    protected abstract boolean isUserSessionRequired();

    /**
     * Replace an existing fragment that was added to a container.  This is
     * currently added fragments that were added with the same containerViewId, It will
     * replace the current fragment and last fragment will not be available in back stack
     *
     * @param id          Identifier of the container whose fragment(s) are
     *                    to be replaced.
     * @param fragment    The new fragment to place in the container.
     * @param fragmentTag Optional tag name for the fragment, to later retrieve the
     *                    fragment with {@link FragmentManager#findFragmentByTag(String)
     *                    FragmentManager.findFragmentByTag(String)}.
     */
    public void changeFragmentWithOutBackStack(int id, Fragment fragment, String fragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(id, fragment, fragmentTag)
                .commit();
    }

    /**
     * Add a fragment to back stack.
     *
     * @param id          Identifier of the container whose fragment(s) are
     *                    to be replaced.
     * @param fragment    The new fragment to place in the container.
     * @param fragmentTag Optional tag name for the fragment, to later retrieve the
     *                    fragment with {@link FragmentManager#findFragmentByTag(String)
     *                    FragmentManager.findFragmentByTag(String)}.
     */
    public void changeFragmentWithBackStack(int id, @NonNull Fragment fragment, @NonNull String fragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(id, fragment)
                .addToBackStack(fragmentTag)
                .commit();
    }

    /**
     * Add a fragment.
     *
     * @param id          Identifier of the container whose fragment(s) are
     *                    to be replaced.
     * @param fragment    The new fragment to place in the container.
     * @param fragmentTag Optional tag name for the fragment, to later retrieve the
     *                    fragment with {@link FragmentManager#findFragmentByTag(String)
     *                    FragmentManager.findFragmentByTag(String)}.
     */
    protected void addFragmentWithOutBackStack(int id, @NonNull Fragment fragment, String fragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(id, fragment)
                .commit();
    }

    /**
     * Add a fragment.
     *
     * @param id          Identifier of the container whose fragment(s) are
     *                    to be replaced.
     * @param fragment    The new fragment to place in the container.
     * @param fragmentTag Optional tag name for the fragment, to later retrieve the
     *                    fragment with {@link FragmentManager#findFragmentByTag(String)
     *                    FragmentManager.findFragmentByTag(String)}.
     */
    protected void addFragmentWithBackStack(int id, @NonNull Fragment fragment, String fragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(id, fragment)
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }


    /**
     * Method to display toast message for short period of time
     *
     * @param message - Accept message string which it needs to display
     */
    protected void showToast(String message) {
        Toast.makeText(BaseActivity.this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mBannerView = LayoutInflater.from(this).inflate(R.layout.layout_banner, null, false);
       /* mTxtBanner = (CustomTextView) mBannerView.findViewById(R.id.txt_banner);
        registerUserSession();*/
    }

    @Override
    protected void onDestroy() {
       /* unregisterUserSession();
        UIUtils.hideProgress();*/
       /* if (null != AWSManager.getInstance())
            AWSManager.getInstance().unSubscribeFromChannels(UserSettingPrefManager.getInstance().readPreference(MolekuleConstants.PREF.LAST_SELECTED_DEVICE_SERIAL));
        */
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
               // UIUtils.hideKeyboard(this);
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*public void updateAppState(int state) {
        if (AppStatePrefManager.getInstance() != null) {
            AppStatePrefManager.getInstance().writePreference(MolekuleConstants.PREF.APP_STATE, state);
        }
    }*/


    /*public void showBanner(@NonNull final FrameLayout container, @NonNull final Bundle bundle) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                populateBanner(container, bundle);
            }
        });

    }

    public void showBannerWithoutRemoveAnimation(@NonNull final FrameLayout container, @NonNull final Bundle bundle, final boolean isRemoveAnim) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mIsRemoveAnim = isRemoveAnim;
                populateBanner(container, bundle);
            }
        });

    }
*/
    /*private void populateBanner(FrameLayout container, Bundle bundle) {
        if (null != bundle && null != container) {
            int color = bundle.getInt(MolekuleConstants.ARGS.BANNER_BACKGROUND_COLOR, -1);
            if (color == -1) {
                color = R.color.green_color;
            }
            String bannerText = bundle.getString(MolekuleConstants.ARGS.BANNER_TEXT);
            if (TextUtils.equals(bannerText, mBannerMessage)) {
                return;
            }
            mBannerMessage = bannerText;
            mBannerView.setBackgroundColor(UIUtils.getColor(color));
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            mBannerView.setMinimumHeight((int) UIUtils.convertDpToPixel(MolekuleConstants.ViewConstants.BANNER_MIN_HEIGHT));
            if (!TextUtils.isEmpty(bannerText)) {
                mTxtBanner.setText(bannerText);
                mBannerView.setTranslationY(-200.0f);
                container.removeView(mBannerView);
                container.addView(mBannerView, layoutParams);
                startingAnimation(container);
            }
        }
    }*/

    private void startingAnimation(final FrameLayout container) {
        ObjectAnimator translateY = ObjectAnimator.ofFloat(mBannerView, "y", 0);
        translateY.setDuration(BANNER_ANIMATION_DURATION);
        //translateY.setInterpolator(new LinearOutSlowInInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translateY);

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                mBannerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (null != container) {
                            if (mIsRemoveAnim) {
                                removeAnimation(container);
                            } else {
                                mIsRemoveAnim = true;
                            }
                        }
                    }
                }, BANNER_TIME);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                if (null != container) {
                    removeAnimation(container);
                }
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animatorSet.start();
    }

    private void removeAnimation(final FrameLayout container) {
        ObjectAnimator translateY = ObjectAnimator.ofFloat(mBannerView, "y", 0, -500);
        translateY.setDuration(1000);
        translateY.setInterpolator(new LinearOutSlowInInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translateY);

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (null != container) {
                    container.removeView(mBannerView);
                    mBannerMessage = "";
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                if (null != container) {
                    container.removeView(mBannerView);
                }
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animatorSet.start();

    }

    public void removeImmediateBanner(final FrameLayout container) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (container != null && mBannerView != null) {
                    container.removeView(mBannerView);
                    mBannerMessage = "";
                }
            }
        });

    }

/*    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(TextUtils.equals(action, MolekuleConstants.ARGS.USER_SESSION_EXPIRED) && isUserSessionRequired()) {
                handleUserSessionExpiry();
            }
        }
    };

    private void handleUserSessionExpiry() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                CommonUtil.launchLoginActivity(BaseActivity.this);
                finish();
            }
        });

    }
    private void registerUserSession() {
        IntentFilter intentFilter = new IntentFilter(MolekuleConstants.ARGS.USER_SESSION_EXPIRED);
        LocalBroadcastManager.getInstance(this).registerReceiver(mBroadcastReceiver,intentFilter);
    }*/

   /* private void unregisterUserSession() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBroadcastReceiver);
    }
*/

}
