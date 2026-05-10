package com.example.beforemerge.ui.auth

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.beforemerge.R

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)

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
}