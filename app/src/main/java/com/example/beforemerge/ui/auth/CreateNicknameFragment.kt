package com.example.beforemerge.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.beforemerge.R

class CreateNicknameFragment : Fragment(R.layout.fragment_create_nickname) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClickListeners(view)
        setupKeyboardAwareBottomButton(view)
    }

    private fun initClickListeners(view: View) {
        val btnBack = view.findViewById<ImageButton>(R.id.btnBack)
        val etNickname = view.findViewById<EditText>(R.id.etNickname)
        val tvNicknameError = view.findViewById<TextView>(R.id.tvNicknameError)
        val btnConfirm = view.findViewById<TextView>(R.id.btnConfirm)

        btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        etNickname.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                hideNicknameError(etNickname, tvNicknameError)
            }
        }

        btnConfirm.setOnClickListener {
            val nickname = etNickname.text.toString().trim()

            // TODO: 추후 닉네임 중복 확인 API 연동 후 실제 결과로 교체
            val isExistingNickname = nickname in TEMP_EXISTING_NICKNAMES

            if (isExistingNickname) {
                showNicknameError(etNickname, tvNicknameError)
                return@setOnClickListener
            }

            hideNicknameError(etNickname, tvNicknameError)
            (requireActivity() as AuthActivity).tempNickname = nickname
            (requireActivity() as AuthActivity).moveToAllergySelection()

            // TODO: 추후 회원가입 API 연동 후 메인 화면 또는 온보딩 화면으로 이동
        }
    }

    private fun setupKeyboardAwareBottomButton(view: View) {
        val btnConfirm = view.findViewById<TextView>(R.id.btnConfirm)
        view.applyKeyboardAwareBottomMargin(btnConfirm)
    }

    private fun showNicknameError(
        etNickname: EditText,
        tvNicknameError: TextView
    ) {
        etNickname.setBackgroundResource(R.drawable.bg_auth_input_error)
        tvNicknameError.visibility = View.VISIBLE
    }

    private fun hideNicknameError(
        etNickname: EditText,
        tvNicknameError: TextView
    ) {
        etNickname.setBackgroundResource(R.drawable.bg_auth_input)
        tvNicknameError.visibility = View.GONE
    }

        companion object {
        private val TEMP_EXISTING_NICKNAMES = setOf(
            "찍먹",
            "테스트",
            "test",
            "admin"
        )
    }
}