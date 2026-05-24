package com.example.beforemerge.ui.auth

import com.example.beforemerge.R

object AllergyDataProvider {

    fun getAllergyItems(): List<AllergyItem> {
        return listOf(
            AllergyItem("egg", "계란", R.drawable.ic_allergy_egg),
            AllergyItem("milk", "우유", R.drawable.ic_allergy_milk),
            AllergyItem("soybean", "대두", R.drawable.ic_allergy_soybean),
            AllergyItem("wheat", "밀", R.drawable.ic_allergy_wheat),
            AllergyItem("pork", "돼지고기", R.drawable.ic_allergy_pork),
            AllergyItem("beef", "소고기", R.drawable.ic_allergy_beef),
            AllergyItem("chicken", "닭고기", R.drawable.ic_allergy_chicken),
            AllergyItem("shrimp", "새우", R.drawable.ic_allergy_shrimp),
            AllergyItem("crab", "게", R.drawable.ic_allergy_crab),
            AllergyItem("squid", "오징어", R.drawable.ic_allergy_squid),
            AllergyItem("mackerel", "고등어", R.drawable.ic_allergy_mackerel),
            AllergyItem("shellfish", "조개류", R.drawable.ic_allergy_shellfish),
            AllergyItem("oyster", "굴", R.drawable.ic_allergy_oyster),
            AllergyItem("mussel", "홍합", R.drawable.ic_allergy_mussel),
            AllergyItem("abalone", "전복", R.drawable.ic_allergy_abalone),
            AllergyItem("peach", "복숭아", R.drawable.ic_allergy_peach),
            AllergyItem("tomato", "토마토", R.drawable.ic_allergy_tomato),
            AllergyItem("peanut", "땅콩", R.drawable.ic_allergy_peanut),
            AllergyItem("walnut", "호두", R.drawable.ic_allergy_walnut),
            AllergyItem("buckwheat", "메밀", R.drawable.ic_allergy_buckwheat),
            AllergyItem("pine_nut", "잣", R.drawable.ic_allergy_pine_nut),
            AllergyItem("sulfite", "아황산류", R.drawable.ic_allergy_sulfite),
            AllergyItem("sesame", "참깨", R.drawable.ic_allergy_sesame),
            AllergyItem("almond", "아몬드", R.drawable.ic_allergy_almond),
            AllergyItem("mustard", "머스타드", R.drawable.ic_allergy_mustard),
            AllergyItem("celery", "셀러리", R.drawable.ic_allergy_celery)
        )
    }

    fun findByIds(ids: List<String>): List<AllergyItem> {
        return getAllergyItems().filter { it.id in ids }
    }
}