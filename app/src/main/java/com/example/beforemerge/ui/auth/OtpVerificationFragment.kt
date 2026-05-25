package com.example.beforemerge.ui.auth

import android.graphics.Typeface
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.beforemerge.R

class OtpVerificationFragment : Fragment(R.layout.fragment_otp_verification) {

    private var countDownTimer: CountDownTimer? = null
    private var hasOtpError = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClickListeners(view)
        setupOtpInputs(view)
        setupOtpTimer(view)
        setupResendText(view)
        setupKeyboardAwareBottomButton(view)
    }

    override fun onDestroyView() {
        countDownTimer?.cancel()
        countDownTimer = null
        super.onDestroyView()
    }

    private fun initClickListeners(view: View) {
        val btnBack = view.findViewById<ImageButton>(R.id.btnBack)
        val btnVerify = view.findViewById<TextView>(R.id.btnVerify)
        val tvOtpError = view.findViewById<TextView>(R.id.tvOtpError)

        val otpInputs = getOtpInputs(view)

        btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        btnVerify.setOnClickListener {
            val otpCode = getOtpCode(otpInputs)

            if (otpCode != TEMP_VALID_OTP_CODE) {
                showOtpError(
                    otpInputs = otpInputs,
                    tvOtpError = tvOtpError
                )
                return@setOnClickListener
            }

            hideOtpError(
                otpInputs = otpInputs,
                tvOtpError = tvOtpError
            )

            // TODO: 추후 인증번호 검증 API 연동 예정
            (requireActivity() as AuthActivity).moveToNewPassword()
        }
    }

    private fun setupOtpInputs(view: View) {
        val otpInputs = getOtpInputs(view)
        val tvOtpError = view.findViewById<TextView>(R.id.tvOtpError)

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
                    if (hasOtpError) {
                        hideOtpError(
                            otpInputs = otpInputs,
                            tvOtpError = tvOtpError
                        )
                    } else {
                        updateOtpInputBackgrounds(otpInputs)
                    }

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

    private fun setupOtpTimer(view: View) {
        val tvOtpTimerValue = view.findViewById<TextView>(R.id.tvOtpTimerValue)
        startOtpTimer(tvOtpTimerValue)
    }

    private fun setupResendText(view: View) {
        val tvResendCode = view.findViewById<TextView>(R.id.tvResendCode)
        val tvOtpTimerValue = view.findViewById<TextView>(R.id.tvOtpTimerValue)
        val tvOtpError = view.findViewById<TextView>(R.id.tvOtpError)
        val otpInputs = getOtpInputs(view)

        applyResendTextStyle(tvResendCode)

        tvResendCode.setOnClickListener {
            // TODO: 추후 인증번호 재전송 API 연동 예정
            clearOtpInputs(otpInputs)
            hideOtpError(
                otpInputs = otpInputs,
                tvOtpError = tvOtpError
            )
            startOtpTimer(tvOtpTimerValue)
            otpInputs.firstOrNull()?.requestFocus()
        }
    }

    private fun startOtpTimer(tvOtpTimerValue: TextView) {
        countDownTimer?.cancel()

        updateTimerText(
            tvOtpTimerValue = tvOtpTimerValue,
            remainingMillis = OTP_TIMER_TOTAL_MILLIS
        )

        countDownTimer = object : CountDownTimer(
            OTP_TIMER_TOTAL_MILLIS,
            OTP_TIMER_INTERVAL_MILLIS
        ) {
            override fun onTick(millisUntilFinished: Long) {
                updateTimerText(
                    tvOtpTimerValue = tvOtpTimerValue,
                    remainingMillis = millisUntilFinished
                )
            }

            override fun onFinish() {
                updateTimerText(
                    tvOtpTimerValue = tvOtpTimerValue,
                    remainingMillis = 0L
                )
            }
        }.start()
    }

    private fun updateTimerText(
        tvOtpTimerValue: TextView,
        remainingMillis: Long
    ) {
        val totalSeconds = remainingMillis / 1000
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60

        tvOtpTimerValue.text = "%02d:%02d".format(minutes, seconds)
    }

    private fun applyResendTextStyle(tvResendCode: TextView) {
        val fullText = getString(R.string.auth_otp_resend_text)
        val actionText = "재전송"

        val spannable = SpannableString(fullText)
        val actionStartIndex = fullText.indexOf(actionText)

        if (actionStartIndex >= 0) {
            val actionEndIndex = actionStartIndex + actionText.length

            spannable.setSpan(
                ForegroundColorSpan(
                    ContextCompat.getColor(requireContext(), R.color.auth_text_primary)
                ),
                actionStartIndex,
                actionEndIndex,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            spannable.setSpan(
                StyleSpan(Typeface.BOLD),
                actionStartIndex,
                actionEndIndex,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            spannable.setSpan(
                UnderlineSpan(),
                actionStartIndex,
                actionEndIndex,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        tvResendCode.text = spannable
    }

    private fun showOtpError(
        otpInputs: List<EditText>,
        tvOtpError: TextView
    ) {
        hasOtpError = true
        tvOtpError.visibility = View.VISIBLE

        otpInputs.forEach { editText ->
            editText.setBackgroundResource(R.drawable.bg_auth_otp_error)
        }
    }

    private fun hideOtpError(
        otpInputs: List<EditText>,
        tvOtpError: TextView
    ) {
        hasOtpError = false
        tvOtpError.visibility = View.GONE
        updateOtpInputBackgrounds(otpInputs)
    }

    private fun updateOtpInputBackgrounds(otpInputs: List<EditText>) {
        if (hasOtpError) {
            otpInputs.forEach { editText ->
                editText.setBackgroundResource(R.drawable.bg_auth_otp_error)
            }
            return
        }

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

    private fun getOtpInputs(view: View): List<EditText> {
        return listOf(
            view.findViewById(R.id.etOtp1),
            view.findViewById(R.id.etOtp2),
            view.findViewById(R.id.etOtp3),
            view.findViewById(R.id.etOtp4)
        )
    }

    private fun getOtpCode(otpInputs: List<EditText>): String {
        return otpInputs.joinToString(separator = "") { editText ->
            editText.text.toString()
        }
    }

    private fun clearOtpInputs(otpInputs: List<EditText>) {
        otpInputs.forEach { editText ->
            editText.text?.clear()
        }
        updateOtpInputBackgrounds(otpInputs)
    }

    private fun setupKeyboardAwareBottomButton(view: View) {
        val btnVerify = view.findViewById<TextView>(R.id.btnVerify)
        view.applyKeyboardAwareBottomMargin(btnVerify)
    }

    companion object {
        private const val TEMP_VALID_OTP_CODE = "1234"

        private const val OTP_TIMER_TOTAL_MILLIS = 3 * 60 * 1000L
        private const val OTP_TIMER_INTERVAL_MILLIS = 1000L
    }
}