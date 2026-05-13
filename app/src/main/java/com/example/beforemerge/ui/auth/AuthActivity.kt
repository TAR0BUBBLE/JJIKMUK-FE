package com.example.beforemerge.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.beforemerge.R

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.authFragmentContainer, LoginEmailFragment())
                .commit()
        }
    }

    fun moveToLoginPassword() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.authFragmentContainer, LoginPasswordFragment())
            .addToBackStack(null)
            .commit()
    }

    fun moveToForgotPassword() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.authFragmentContainer, ForgotPasswordFragment())
            .addToBackStack(null)
            .commit()
    }

    fun moveToOtpVerification() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.authFragmentContainer, OtpVerificationFragment())
            .addToBackStack(null)
            .commit()
    }

    fun moveToNewPassword() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.authFragmentContainer, NewPasswordFragment())
            .addToBackStack(null)
            .commit()
    }
}