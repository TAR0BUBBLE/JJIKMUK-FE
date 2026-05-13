package com.example.beforemerge.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.beforemerge.R

class ForgotPasswordFragment : Fragment(R.layout.fragment_forgot_password) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClickListeners(view)
        setupKeyboardAwareBottomButton(view)
    }

    private fun initClickListeners(view: View) {
        val btnBack = view.findViewById<ImageButton>(R.id.btnBack)
        val btnSendCode = view.findViewById<TextView>(R.id.btnSendCode)

        btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        btnSendCode.setOnClickListener {
            // TODO: 추후 인증번호 전송 API 연동 예정
            (requireActivity() as AuthActivity).moveToOtpVerification()
        }
    }

    private fun setupKeyboardAwareBottomButton(view: View) {
        val btnSendCode = view.findViewById<TextView>(R.id.btnSendCode)
        view.applyKeyboardAwareBottomMargin(btnSendCode)
    }
}