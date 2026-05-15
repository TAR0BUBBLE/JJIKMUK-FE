package com.example.beforemerge.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.example.beforemerge.R

class NewPasswordFragment : Fragment(R.layout.fragment_new_password) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClickListeners(view)
        setupPasswordValidation(view)
        setupKeyboardAwareBottomButton(view)
    }

    private fun initClickListeners(view: View) {
        val btnBack = view.findViewById<ImageButton>(R.id.btnBack)
        val etNewPassword = view.findViewById<EditText>(R.id.etNewPassword)
        val etConfirmPassword = view.findViewById<EditText>(R.id.etConfirmPassword)
        val tvNewPasswordError = view.findViewById<TextView>(R.id.tvNewPasswordError)
        val tvConfirmPasswordError = view.findViewById<TextView>(R.id.tvConfirmPasswordError)
        val btnConfirm = view.findViewById<TextView>(R.id.btnConfirm)

        btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        btnConfirm.setOnClickListener {
            val isValid = validatePasswords(
                etNewPassword = etNewPassword,
                etConfirmPassword = etConfirmPassword,
                tvNewPasswordError = tvNewPasswordError,
                tvConfirmPasswordError = tvConfirmPasswordError,
                showEmptyError = true
            )

            if (isValid) {
                // TODO: 추후 비밀번호 변경 API 연동 예정
                (requireActivity() as AuthActivity).moveToPasswordChangeComplete()
            }
        }
    }

    private fun setupPasswordValidation(view: View) {
        val etNewPassword = view.findViewById<EditText>(R.id.etNewPassword)
        val etConfirmPassword = view.findViewById<EditText>(R.id.etConfirmPassword)
        val tvNewPasswordError = view.findViewById<TextView>(R.id.tvNewPasswordError)
        val tvConfirmPasswordError = view.findViewById<TextView>(R.id.tvConfirmPasswordError)

        etNewPassword.doAfterTextChanged {
            validatePasswords(
                etNewPassword = etNewPassword,
                etConfirmPassword = etConfirmPassword,
                tvNewPasswordError = tvNewPasswordError,
                tvConfirmPasswordError = tvConfirmPasswordError,
                showEmptyError = false
            )
        }

        etConfirmPassword.doAfterTextChanged {
            validatePasswords(
                etNewPassword = etNewPassword,
                etConfirmPassword = etConfirmPassword,
                tvNewPasswordError = tvNewPasswordError,
                tvConfirmPasswordError = tvConfirmPasswordError,
                showEmptyError = false
            )
        }
    }

    private fun validatePasswords(
        etNewPassword: EditText,
        etConfirmPassword: EditText,
        tvNewPasswordError: TextView,
        tvConfirmPasswordError: TextView,
        showEmptyError: Boolean
    ): Boolean {
        val newPassword = etNewPassword.text.toString()
        val confirmPassword = etConfirmPassword.text.toString()

        val shouldShowMinLengthError = if (showEmptyError) {
            newPassword.length < MIN_PASSWORD_LENGTH
        } else {
            newPassword.isNotEmpty() && newPassword.length < MIN_PASSWORD_LENGTH
        }

        val shouldShowMismatchError =
            confirmPassword.isNotEmpty() && newPassword != confirmPassword

        setInputErrorState(
            editText = etNewPassword,
            errorTextView = tvNewPasswordError,
            hasError = shouldShowMinLengthError
        )

        setInputErrorState(
            editText = etConfirmPassword,
            errorTextView = tvConfirmPasswordError,
            hasError = shouldShowMismatchError
        )

        return !shouldShowMinLengthError &&
                !shouldShowMismatchError &&
                newPassword.length >= MIN_PASSWORD_LENGTH &&
                confirmPassword.isNotEmpty()
    }

    private fun setInputErrorState(
        editText: EditText,
        errorTextView: TextView,
        hasError: Boolean
    ) {
        editText.setBackgroundResource(
            if (hasError) {
                R.drawable.bg_auth_input_error
            } else {
                R.drawable.bg_auth_input
            }
        )

        errorTextView.visibility = if (hasError) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private fun setupKeyboardAwareBottomButton(view: View) {
        val btnConfirm = view.findViewById<TextView>(R.id.btnConfirm)
        view.applyKeyboardAwareBottomMargin(btnConfirm)
    }

    companion object {
        private const val MIN_PASSWORD_LENGTH = 6
    }
}