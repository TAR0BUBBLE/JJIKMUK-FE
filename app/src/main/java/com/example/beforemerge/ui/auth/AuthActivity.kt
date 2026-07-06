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
                .replace(R.id.authFragmentContainer, AuthEntryFragment())
                .commit()
        }
    }

    fun moveToLogin() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.authFragmentContainer, LoginPasswordFragment())
            .addToBackStack(null)
            .commit()
    }

    fun moveToSignupEmail() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.authFragmentContainer, SignupEmailFragment())
            .addToBackStack(null)
            .commit()
    }

    fun moveToForgotPassword() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.authFragmentContainer, ForgotPasswordFragment())
            .addToBackStack(null)
            .commit()
    }

    fun moveToPasswordResetOtpVerification() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.authFragmentContainer,
                OtpVerificationFragment.newInstance(OtpVerificationFragment.Mode.PASSWORD_RESET)
            )
            .addToBackStack(null)
            .commit()
    }

    fun moveToSignupOtpVerification() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.authFragmentContainer,
                OtpVerificationFragment.newInstance(OtpVerificationFragment.Mode.SIGNUP)
            )
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

    fun moveToAuthEntry() {
        supportFragmentManager.popBackStack(
            null,
            androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
        )

        supportFragmentManager.beginTransaction()
            .replace(R.id.authFragmentContainer, AuthEntryFragment())
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

    var tempNickname: String = ""

    fun moveToProfileSetting(
        nickname: String,
        selectedAllergyIds: ArrayList<String>
    ) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.authFragmentContainer,
                ProfileSettingFragment.newInstance(
                    nickname = nickname,
                    selectedAllergyIds = selectedAllergyIds
                )
            )
            .addToBackStack(null)
            .commit()
    }

    fun moveToSignupComplete() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.authFragmentContainer, SignupCompleteFragment())
            .addToBackStack(null)
            .commit()
    }
}
