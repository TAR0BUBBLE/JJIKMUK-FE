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

class LoginEmailFragment : Fragment(R.layout.fragment_login_email) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitleStyle(view)
        initClickListeners(view)
    }

    private fun initClickListeners(view: View) {
        val btnBack = view.findViewById<ImageButton>(R.id.btnBack)
        val btnConfirm = view.findViewById<TextView>(R.id.btnConfirm)

        btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        btnConfirm.setOnClickListener {
            // TODO: 추후 이메일 입력값 검증 및 API 연동 예정
            (requireActivity() as AuthActivity).moveToLoginPassword()
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