package com.example.beforemerge.ui.auth

import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.beforemerge.R

fun View.applyKeyboardAwareBottomMargin(bottomButton: View) {
    val normalBottomMargin = resources.getDimensionPixelSize(
        R.dimen.auth_button_bottom_margin
    )
    val keyboardGap = resources.getDimensionPixelSize(
        R.dimen.auth_button_keyboard_bottom_margin
    )

    ViewCompat.setOnApplyWindowInsetsListener(this) { _, insets ->
        val imeInsets = insets.getInsets(WindowInsetsCompat.Type.ime())
        val navigationBarInsets = insets.getInsets(WindowInsetsCompat.Type.navigationBars())

        val keyboardHeight = (imeInsets.bottom - navigationBarInsets.bottom).coerceAtLeast(0)

        val layoutParams = bottomButton.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.bottomMargin = if (keyboardHeight > 0) {
            keyboardHeight + keyboardGap
        } else {
            normalBottomMargin
        }

        bottomButton.layoutParams = layoutParams

        insets
    }

    ViewCompat.requestApplyInsets(this)
}