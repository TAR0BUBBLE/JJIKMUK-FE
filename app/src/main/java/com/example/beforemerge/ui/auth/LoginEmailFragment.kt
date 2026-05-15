package com.example.beforemerge.ui.auth

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.beforemerge.R
import android.widget.EditText

class LoginEmailFragment : Fragment(R.layout.fragment_login_email) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitleStyle(view)
        initClickListeners(view)
        setupKeyboardAwareBottomButton(view)
    }

    private fun setupKeyboardAwareBottomButton(view: View) {
        val btnConfirm = view.findViewById<TextView>(R.id.btnConfirm)
        view.applyKeyboardAwareBottomMargin(btnConfirm)
    }

    private fun initClickListeners(view: View) {
        val btnBack = view.findViewById<ImageButton>(R.id.btnBack)
        val etEmail = view.findViewById<EditText>(R.id.etEmail)
        val tvEmailError = view.findViewById<TextView>(R.id.tvEmailError)
        val btnConfirm = view.findViewById<TextView>(R.id.btnConfirm)

        btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        etEmail.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                hideEmailError(etEmail, tvEmailError)
            }
        }

        btnConfirm.setOnClickListener {
            val email = etEmail.text.toString().trim()

            if (!email.isValidEmailFormat()) {
                showEmailError(etEmail, tvEmailError)
                return@setOnClickListener
            }

            hideEmailError(etEmail, tvEmailError)

            // TODO: 추후 이메일 확인 API 연동 후 실제 계정 존재 여부로 교체
            val isExistingAccountEmail = email == TEMP_EXISTING_EMAIL

            if (isExistingAccountEmail) {
                (requireActivity() as AuthActivity).moveToLoginPassword()
            } else {
                (requireActivity() as AuthActivity).moveToCreatePassword()
            }
        }
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

    companion object {
        private const val TEMP_EXISTING_EMAIL = "email@example.com"
    }
}