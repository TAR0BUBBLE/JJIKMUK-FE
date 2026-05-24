package com.example.beforemerge.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.beforemerge.R

class SignupCompleteFragment : Fragment(R.layout.fragment_password_change_complete) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindSignupCompleteText(view)
        initClickListeners(view)
    }

    private fun bindSignupCompleteText(view: View) {
        val ivSuccess = view.findViewById<ImageView>(R.id.ivSuccess)
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvDescription = view.findViewById<TextView>(R.id.tvDescription)
        val btnGoLogin = view.findViewById<TextView>(R.id.btnGoLogin)

        ivSuccess.setImageResource(R.drawable.ic_password_change_success)
        ivSuccess.contentDescription = getString(R.string.auth_signup_complete_title)

        tvTitle.text = getString(R.string.auth_signup_complete_title)
        tvDescription.text = getString(R.string.auth_signup_complete_description)
        btnGoLogin.text = getString(R.string.auth_login_action)
    }

    private fun initClickListeners(view: View) {
        val btnGoLogin = view.findViewById<TextView>(R.id.btnGoLogin)

        btnGoLogin.setOnClickListener {
            (requireActivity() as AuthActivity).moveToLoginEmail()
        }
    }
}