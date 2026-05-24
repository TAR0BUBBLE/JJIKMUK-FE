package com.example.beforemerge.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beforemerge.R

class AllergySelectionFragment : Fragment(R.layout.fragment_allergy_selection) {

    private lateinit var allergyAdapter: AllergySelectionAdapter

    private var allergyItems: List<AllergyItem> = emptyList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        allergyItems = AllergyDataProvider.getAllergyItems()

        initRecyclerView(view)
        initClickListeners(view)
        updateCompleteButton(view)
    }

    private fun initRecyclerView(view: View) {
        val rvAllergy = view.findViewById<RecyclerView>(R.id.rvAllergy)

        allergyAdapter = AllergySelectionAdapter { clickedItem ->
            toggleAllergyItem(clickedItem, view)
        }

        rvAllergy.layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT)
        rvAllergy.adapter = allergyAdapter
        rvAllergy.addItemDecoration(
            GridSpacingItemDecoration(
                spanCount = SPAN_COUNT,
                spacing = resources.getDimensionPixelSize(R.dimen.auth_allergy_grid_spacing)
            )
        )

        allergyAdapter.submitList(allergyItems)
    }

    private fun initClickListeners(view: View) {
        val btnBack = view.findViewById<ImageButton>(R.id.btnBack)
        val btnComplete = view.findViewById<TextView>(R.id.btnComplete)

        btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        btnComplete.setOnClickListener {
            // TODO: 추후 회원가입 API 또는 프로필 저장 API 연동
            val selectedIds = getSelectedAllergies()
                .map { it.id }
                .let { ArrayList(it) }

            val nickname = (requireActivity() as AuthActivity).tempNickname

            (requireActivity() as AuthActivity).moveToProfileSetting(
                nickname = nickname,
                selectedAllergyIds = selectedIds
            )
        }
    }

    private fun toggleAllergyItem(
        clickedItem: AllergyItem,
        view: View
    ) {
        allergyItems = allergyItems.map { item ->
            if (item.id == clickedItem.id) {
                item.copy(isSelected = !item.isSelected)
            } else {
                item
            }
        }

        allergyAdapter.submitList(allergyItems)
        updateCompleteButton(view)
    }

    private fun updateCompleteButton(view: View) {
        val btnComplete = view.findViewById<TextView>(R.id.btnComplete)
        val selectedCount = allergyItems.count { it.isSelected }

        btnComplete.text = getString(
            R.string.auth_allergy_complete_format,
            selectedCount
        )
    }

    private fun getSelectedAllergies(): List<AllergyItem> {
        return allergyItems.filter { it.isSelected }
    }

    private fun createAllergyItems(): List<AllergyItem> {
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

    companion object {
        private const val SPAN_COUNT = 3
    }
}