package com.soict.hoangviet.firebase.ui.view.impl

import android.content.DialogInterface
import android.content.Intent
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth
import com.soict.hoangviet.baseproject.extension.hasNetworkConnection
import com.soict.hoangviet.baseproject.extension.inResourceString
import com.soict.hoangviet.baseproject.extension.launchActivity
import com.soict.hoangviet.baseproject.extension.toast
import com.soict.hoangviet.firebase.R
import com.soict.hoangviet.firebase.ui.presenter.SplashPresenter
import com.soict.hoangviet.firebase.ui.view.SplashView
import com.soict.hoangviet.firebase.utils.DialogUtil
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashView {
    @Inject
    lateinit var mPresenter: SplashPresenter

    override fun initListener() {
    }

    companion object {
        const val SPLASH_TIME = 1500L
    }

    override fun initView() {
        mPresenter.onAttach(this)
        Handler().postDelayed({
            checkNetworkConnection()
        }, SPLASH_TIME)
    }

    private fun checkNetworkConnection() {
        if (hasNetworkConnection()) {
            mPresenter.checkFirstTimeForApp()
        } else {
            showAlertNoNetworkConnection()
        }
    }

    override fun checkCurrentUser() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            mPresenter.saveCurrentUser()
        } else {
            goToLogin()
        }
    }

    override fun goToHomeScreen() {
        startActivity(Intent(this, MainActivity::class.java).apply {
        })
        mPresenter.onDetach()
        finish()
    }

    override fun showError() {
        toast(inResourceString {
            getString(R.string.error_happen)
        })
    }

    override fun goToTutorialScreen() {
        launchActivity<TutorialActivity>()
        finish()
    }

    private fun goToLogin() {
        launchActivity<LoginActivity>()
        finish()
    }

    private fun showAlertNoNetworkConnection() {
        DialogUtil.showMessageDialog(
            this,
            getString(R.string.splash_notification_title),
            getString(R.string.splash_message_alert),
            false,
            getString(R.string.splash_positive_title),
            object : DialogUtil.BaseDialogInterface {
                override fun onPositiveClick(dialog: DialogInterface) {
                    finish()
                }

                override fun onNegativeClick(dialog: DialogInterface) {
                }

                override fun onItemClick(dialog: DialogInterface, position: Int) {
                }
            }
        )
    }

    override fun onFragmentAttached() {
    }

    override fun onFragmentDetached(tag: String) {
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDetach()
    }
}