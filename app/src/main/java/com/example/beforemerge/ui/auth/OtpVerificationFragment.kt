package com.example.beforemerge.ui.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.beforemerge.R

class OtpVerificationFragment : Fragment(R.layout.fragment_otp_verification) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClickListeners(view)
        setupOtpInputs(view)
        setupKeyboardAwareBottomButton(view)
    }

    private fun initClickListeners(view: View) {
        val btnBack = view.findViewById<ImageButton>(R.id.btnBack)
        val btnVerify = view.findViewById<TextView>(R.id.btnVerify)

        btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        btnVerify.setOnClickListener {
            // TODO: 추후 인증번호 검증 API 연동 예정
            (requireActivity() as AuthActivity).moveToNewPassword()
        }
    }

    private fun setupOtpInputs(view: View) {
        val otpInputs = listOf(
            view.findViewById<EditText>(R.id.etOtp1),
            view.findViewById<EditText>(R.id.etOtp2),
            view.findViewById<EditText>(R.id.etOtp3),
            view.findViewById<EditText>(R.id.etOtp4)
        )

        updateOtpInputBackgrounds(otpInputs)

        otpInputs.forEachIndexed { index, editText ->
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) = Unit

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    updateOtpInputBackgrounds(otpInputs)

                    if (!s.isNullOrEmpty() && index < otpInputs.lastIndex) {
                        otpInputs[index + 1].requestFocus()
                    }
                }

                override fun afterTextChanged(s: Editable?) = Unit
            })

            editText.setOnKeyListener { _, keyCode, event ->
                if (
                    keyCode == KeyEvent.KEYCODE_DEL &&
                    event.action == KeyEvent.ACTION_DOWN &&
                    editText.text.isEmpty() &&
                    index > 0
                ) {
                    otpInputs[index - 1].requestFocus()
                    otpInputs[index - 1].text?.clear()
                    updateOtpInputBackgrounds(otpInputs)
                    true
                } else {
                    false
                }
            }
        }
    }

    private fun updateOtpInputBackgrounds(otpInputs: List<EditText>) {
        val isAllEmpty = otpInputs.all { it.text.isNullOrEmpty() }

        otpInputs.forEachIndexed { index, editText ->
            val shouldBeActive = editText.text.isNotEmpty() || (isAllEmpty && index == 0)

            editText.setBackgroundResource(
                if (shouldBeActive) {
                    R.drawable.bg_auth_otp_active
                } else {
                    R.drawable.bg_auth_otp_inactive
                }
            )
        }
    }

    private fun setupKeyboardAwareBottomButton(view: View) {
        val btnVerify = view.findViewById<TextView>(R.id.btnVerify)
        view.applyKeyboardAwareBottomMargin(btnVerify)
    }
}