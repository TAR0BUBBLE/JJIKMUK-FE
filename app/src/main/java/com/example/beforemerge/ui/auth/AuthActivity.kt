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

    fun moveToPasswordChangeComplete() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.authFragmentContainer, PasswordChangeCompleteFragment())
            .addToBackStack(null)
            .commit()
    }

    fun moveToLoginEmail() {
        supportFragmentManager.popBackStack(
            null,
            androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
        )

        supportFragmentManager.beginTransaction()
            .replace(R.id.authFragmentContainer, LoginEmailFragment())
            .commit()
    }

    fun moveToCreateNickname() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.authFragmentContainer, CreateNicknameFragment())
            .addToBackStack(null)
            .commit()
    }

    fun moveToCreatePassword() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.authFragmentContainer, CreatePasswordFragment())
            .addToBackStack(null)
            .commit()
    }

    fun moveToAllergySelection() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.authFragmentContainer, AllergySelectionFragment())
            .addToBackStack(null)
            .commit()
    }
}