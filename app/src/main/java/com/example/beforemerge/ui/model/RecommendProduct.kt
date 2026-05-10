package com.example.beforemerge.ui.model

data class RecommendProduct(
    val id: String,
    val category: String,
    val name: String,
    val imageResId: Int,
    val allergyTags: List<String>
)