package com.example.beforemerge.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.beforemerge.R

class NewPasswordFragment : Fragment(R.layout.fragment_new_password) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClickListeners(view)
        setupKeyboardAwareBottomButton(view)
    }

    private fun initClickListeners(view: View) {
        val btnBack = view.findViewById<ImageButton>(R.id.btnBack)
        val btnConfirm = view.findViewById<TextView>(R.id.btnConfirm)

        btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        btnConfirm.setOnClickListener {
            // TODO: 비밀번호 변경 완료 화면 구현 후 이동 처리
        }
    }

    private fun setupKeyboardAwareBottomButton(view: View) {
        val btnConfirm = view.findViewById<TextView>(R.id.btnConfirm)
        view.applyKeyboardAwareBottomMargin(btnConfirm)
    }
}