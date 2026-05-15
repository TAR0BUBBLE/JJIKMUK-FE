package com.example.beforemerge.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.beforemerge.R

class PasswordChangeCompleteFragment : Fragment(R.layout.fragment_password_change_complete) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClickListeners(view)
    }

    private fun initClickListeners(view: View) {
        val btnGoLogin = view.findViewById<TextView>(R.id.btnGoLogin)

        btnGoLogin.setOnClickListener {
            (requireActivity() as AuthActivity).moveToLoginEmail()
        }
    }
}