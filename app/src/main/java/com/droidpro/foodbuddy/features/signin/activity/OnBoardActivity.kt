package com.droidpro.foodbuddy.features.signin.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.droidpro.foodbuddy.R
import com.droidpro.foodbuddy.constants.FbdConstants
import com.droidpro.foodbuddy.features.HomeActivity
import com.droidpro.foodbuddy.features.signin.FBSignUpContract
import com.droidpro.foodbuddy.manager.PreferenceManager
import com.droidpro.foodbuddy.utils.UIUtils.hideProgress
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class OnBoardActivity : AppCompatActivity(), FBSignUpContract.View {
    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun cancelProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFBSignUpSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFBSignUpFailed(errorMessage: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    lateinit var mLoginBtn: LoginButton
    lateinit var mSignUpBtn: LoginButton
    lateinit var mFullName: String
    lateinit var mFbImgUrl: String
    lateinit var mEmail: String
    lateinit var mFbId: String
    lateinit var mFbAccessToken: String
    lateinit var mDeviceId: String

    lateinit var mCallbackManager: CallbackManager

    companion object {
        val EMAIL = "email"
        val USER_POSTS = "user_posts"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_board)
        initView();
        setListeners();
    }

    private fun initView() {
        mLoginBtn = findViewById<LoginButton>(R.id.login_button)
        mSignUpBtn = findViewById<LoginButton>(R.id.btn_fb_signup)

        // Set the initial permissions to request from the user while logging in
        mLoginBtn.setReadPermissions(Arrays.asList<String>(EMAIL, USER_POSTS))
        mCallbackManager = CallbackManager.Factory.create()
    }

    private fun setListeners() {
        mLoginBtn.setOnClickListener(View.OnClickListener {

            //fbSignIn()
        })
        mSignUpBtn.setOnClickListener(View.OnClickListener {
            fbSignUp()
        })

    }

    private fun fbSignUp() {
        val permissions = Arrays.asList("public_profile", "email")

        mSignUpBtn.registerCallback(mCallbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                val profile = Profile.getCurrentProfile()
                if (profile != null) {
                    mFullName = profile.name
                }
                requestUserProfileData()
            }

            override fun onCancel() {
                /* hideProgress()layout_progress_dialog.xml
                 LoginManager.getInstance().logOut()
                 Toast.makeText(this@FbSignUpActivity, getString(R.string.signup_cancel), Toast.LENGTH_LONG).show()
             */
            }

            override fun onError(e: FacebookException) {
                /* hideProgress()
                 LoginManager.getInstance().logOut()
                 if (!TextUtils.isEmpty(e.message)) {
                     Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
                 } else {
                     Toast.makeText(applicationContext, getString(R.string.fb_exception), Toast.LENGTH_SHORT).show()
                 }*/
            }
        })
        LoginManager.getInstance().logOut()
        LoginManager.getInstance().logInWithReadPermissions(this, permissions)
    }

    private fun fbSignIn() {
        mLoginBtn.registerCallback(mCallbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                setResult(Activity.RESULT_OK)
                val intent = Intent(this@OnBoardActivity, HomeActivity::class.java)
                startActivity(intent)
                //finish()
            }

            override fun onCancel() {
                setResult(Activity.RESULT_CANCELED)
                finish()
            }

            override fun onError(e: FacebookException) {
                // Handle exception
            }
        })
    }

    private fun requestUserProfileData() {
        val request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken()
        ) { `object`, response ->
            val jsonObject = response.jsonObject
            if (AccessToken.getCurrentAccessToken() != null) {
                mFbAccessToken = AccessToken.getCurrentAccessToken().token
                getFbData(jsonObject)
                setPreferncesData()
            }
        }
        val parameters = Bundle()
        parameters.putString("fields", "id, first_name, last_name, email,gender")
        request.parameters = parameters
        request.executeAsync()
    }

    private fun setPreferncesData() {
       /* hideProgress()
        PreferenceManager.set(this, FbdConstants.Signup.FB_ID, mFbId)
        PreferenceManager.set(this, AppConstants.Signup.FB_ACCESS_TOKEN, mFbAccessToken)
        PreferenceManager.set(this, AppConstants.User.NAME, mFullName)
        PreferenceManager.set(this, AppConstants.User.EMAIL, mEmail)
        PreferenceManager.set(this, AppConstants.User.IMAGE_URL, mFbImgUrl)
        startActivity(Intent(this@FbSignUpActivity, FbSignUpDetailsActivity::class.java))
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)*/
    }


    private fun getFbData(jsonObject: JSONObject) {
        if (jsonObject != null) {
            try {
                if (TextUtils.isEmpty(mFullName)) {
                    mFullName = (jsonObject.getString(getString(R.string.fb_fname)) + " " +
                            jsonObject.getString(getString(R.string.fb_lname)))
                }
                mFbId = jsonObject.getString(getString(R.string.fb_id))
                mFbImgUrl = "https://graph.facebook.com/$mFbId/picture?type=large"
                if (jsonObject.getString(getString(R.string.fb_email)) != null) {
                    mEmail = jsonObject.getString(getString(R.string.fb_email))
                }
            } catch (e: JSONException) {
                hideProgress()
                e.printStackTrace()
            }

        }
    }

}