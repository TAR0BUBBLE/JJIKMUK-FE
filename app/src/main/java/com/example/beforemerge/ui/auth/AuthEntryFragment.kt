package com.example.beforemerge.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.beforemerge.R

class AuthEntryFragment : Fragment(R.layout.fragment_auth_entry) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.btnLogin).setOnClickListener {
            (requireActivity() as AuthActivity).moveToLogin()
        }

        view.findViewById<TextView>(R.id.btnSignup).setOnClickListener {
            (requireActivity() as AuthActivity).moveToSignupEmail()
        }
    }
}
