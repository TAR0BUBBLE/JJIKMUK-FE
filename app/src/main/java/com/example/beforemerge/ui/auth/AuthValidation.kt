package com.example.beforemerge.ui.auth

import android.util.Patterns

fun String.isValidEmailFormat(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(trim()).matches()
}