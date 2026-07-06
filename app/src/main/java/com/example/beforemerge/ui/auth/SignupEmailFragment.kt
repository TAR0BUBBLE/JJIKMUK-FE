package com.example.beforemerge.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.beforemerge.R

class SignupEmailFragment : Fragment(R.layout.fragment_signup_email) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClickListeners(view)
        setupKeyboardAwareBottomButton(view)
    }

    private fun initClickListeners(view: View) {
        val btnBack = view.findViewById<ImageButton>(R.id.btnBack)
        val etEmail = view.findViewById<EditText>(R.id.etEmail)
        val tvEmailError = view.findViewById<TextView>(R.id.tvEmailError)
        val btnSendCode = view.findViewById<TextView>(R.id.btnSendCode)

        btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        etEmail.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                hideEmailError(etEmail, tvEmailError)
            }
        }

        btnSendCode.setOnClickListener {
            val email = etEmail.text.toString().trim()

            if (!email.isValidEmailFormat()) {
                showEmailError(etEmail, tvEmailError)
                return@setOnClickListener
            }

            hideEmailError(etEmail, tvEmailError)
            (requireActivity() as AuthActivity).moveToSignupOtpVerification()
        }
    }

    private fun setupKeyboardAwareBottomButton(view: View) {
        val btnSendCode = view.findViewById<TextView>(R.id.btnSendCode)
        view.applyKeyboardAwareBottomMargin(btnSendCode)
    }

    private fun showEmailError(
        etEmail: EditText,
        tvEmailError: TextView
    ) {
        etEmail.setBackgroundResource(R.drawable.bg_auth_input_error)
        tvEmailError.visibility = View.VISIBLE
    }

    private fun hideEmailError(
        etEmail: EditText,
        tvEmailError: TextView
    ) {
        etEmail.setBackgroundResource(R.drawable.bg_auth_input)
        tvEmailError.visibility = View.GONE
    }
}
