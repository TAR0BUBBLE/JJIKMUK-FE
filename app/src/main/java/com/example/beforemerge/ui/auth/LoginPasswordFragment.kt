package com.example.beforemerge.ui.auth

import android.os.Bundle
import android.text.InputType
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.beforemerge.R

class LoginPasswordFragment : Fragment(R.layout.fragment_login_password) {

    private var isPasswordVisible = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitleStyle(view)
        initClickListeners(view)
        setupKeyboardAwareBottomButton(view)
    }

    private fun setupKeyboardAwareBottomButton(view: View) {
        val btnLogin = view.findViewById<TextView>(R.id.btnLogin)
        view.applyKeyboardAwareBottomMargin(btnLogin)
    }

    private fun initClickListeners(view: View) {
        val btnBack = view.findViewById<ImageButton>(R.id.btnBack)
        val etEmail = view.findViewById<EditText>(R.id.etEmail)
        val etPassword = view.findViewById<EditText>(R.id.etPassword)
        val passwordInputContainer = view.findViewById<View>(R.id.passwordInputContainer)
        val tvPasswordError = view.findViewById<TextView>(R.id.tvPasswordError)
        val btnTogglePasswordVisibility =
            view.findViewById<ImageButton>(R.id.btnTogglePasswordVisibility)
        val tvForgotPassword = view.findViewById<TextView>(R.id.tvForgotPassword)
        val btnLogin = view.findViewById<TextView>(R.id.btnLogin)
        val tvSignupLink = view.findViewById<TextView>(R.id.tvSignupLink)

        btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        etEmail.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                hideLoginError(etEmail, passwordInputContainer, tvPasswordError)
            }
        }

        etPassword.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                hideLoginError(etEmail, passwordInputContainer, tvPasswordError)
            }
        }

        btnTogglePasswordVisibility.setOnClickListener {
            togglePasswordVisibility(etPassword, btnTogglePasswordVisibility)
        }

        tvForgotPassword.setOnClickListener {
            (requireActivity() as AuthActivity).moveToForgotPassword()
        }

        btnLogin.setOnClickListener {
            // TODO: 추후 로그인 API 연동 후 실제 비밀번호 검증 결과로 교체
            showLoginError(etEmail, passwordInputContainer, tvPasswordError)
        }

        tvSignupLink.setOnClickListener {
            (requireActivity() as AuthActivity).moveToSignupEmail()
        }
    }

    private fun showLoginError(
        etEmail: EditText,
        passwordInputContainer: View,
        tvPasswordError: TextView
    ) {
        etEmail.setBackgroundResource(R.drawable.bg_auth_input_error)
        passwordInputContainer.setBackgroundResource(R.drawable.bg_auth_input_error)
        tvPasswordError.visibility = View.VISIBLE
    }

    private fun hideLoginError(
        etEmail: EditText,
        passwordInputContainer: View,
        tvPasswordError: TextView
    ) {
        etEmail.setBackgroundResource(R.drawable.bg_auth_input)
        passwordInputContainer.setBackgroundResource(R.drawable.bg_auth_input)
        tvPasswordError.visibility = View.GONE
    }

    private fun togglePasswordVisibility(
        etPassword: EditText,
        btnTogglePasswordVisibility: ImageButton
    ) {
        val selection = etPassword.selectionStart

        isPasswordVisible = !isPasswordVisible

        if (isPasswordVisible) {
            etPassword.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            btnTogglePasswordVisibility.setImageResource(R.drawable.ic_visibility)
        } else {
            etPassword.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            btnTogglePasswordVisibility.setImageResource(R.drawable.ic_visibility_off)
        }

        if (selection >= 0) {
            etPassword.setSelection(selection)
        }
    }

    private fun setTitleStyle(view: View) {
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)

        val title = getString(R.string.auth_login_title)
        val spannable = SpannableString(title)

        val greenColor = ContextCompat.getColor(
            requireContext(),
            R.color.auth_brand_green
        )

        listOf("찍", "먹").forEach { target ->
            val start = title.indexOf(target)

            if (start != -1) {
                spannable.setSpan(
                    ForegroundColorSpan(greenColor),
                    start,
                    start + target.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }

        tvTitle.text = spannable
    }
}
