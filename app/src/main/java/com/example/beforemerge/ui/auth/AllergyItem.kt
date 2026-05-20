package com.example.beforemerge.ui.auth

import androidx.annotation.DrawableRes

data class AllergyItem(
    val id: String,
    val name: String,
    @DrawableRes val imageResId: Int,
    val isSelected: Boolean = false
)