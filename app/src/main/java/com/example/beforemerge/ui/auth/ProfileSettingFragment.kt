package com.example.beforemerge.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.example.beforemerge.R

class ProfileSettingFragment : Fragment(R.layout.fragment_profile_setting) {

    private val nickname: String by lazy {
        arguments?.getString(ARG_NICKNAME).orEmpty()
    }

    private val selectedAllergyIds: List<String> by lazy {
        arguments?.getStringArrayList(ARG_SELECTED_ALLERGY_IDS).orEmpty()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindProfileInfo(view)
        bindSelectedAllergies(view)
        initClickListeners(view)
        setupKeyboardAwareBottomButton(view)
    }

    private fun bindProfileInfo(view: View) {
        val etNickname = view.findViewById<EditText>(R.id.etNickname)
        val btnClearNickname = view.findViewById<FrameLayout>(R.id.btnClearNickname)

        etNickname.setText(nickname)

        btnClearNickname.visibility = if (etNickname.text.isNullOrEmpty()) {
            View.GONE
        } else {
            View.VISIBLE
        }

        etNickname.doAfterTextChanged {
            btnClearNickname.visibility = if (it.isNullOrEmpty()) {
                View.GONE
            } else {
                View.VISIBLE
            }
        }
    }

    private fun bindSelectedAllergies(view: View) {
        val selectedAllergyContainer =
            view.findViewById<LinearLayout>(R.id.selectedAllergyContainer)
        val tvNoAllergy = view.findViewById<TextView>(R.id.tvNoAllergy)

        val selectedAllergies = AllergyDataProvider.findByIds(selectedAllergyIds)

        selectedAllergyContainer.removeAllViews()

        if (selectedAllergies.isEmpty()) {
            selectedAllergyContainer.visibility = View.GONE
            tvNoAllergy.visibility = View.VISIBLE
            return
        }

        selectedAllergyContainer.visibility = View.VISIBLE
        tvNoAllergy.visibility = View.GONE

        selectedAllergies.forEachIndexed { index, allergy ->
            val chipView = LayoutInflater.from(requireContext())
                .inflate(R.layout.item_selected_allergy_chip, selectedAllergyContainer, false)

            val ivAllergy = chipView.findViewById<ImageView>(R.id.ivAllergy)
            val tvAllergyName = chipView.findViewById<TextView>(R.id.tvAllergyName)

            ivAllergy.setImageResource(allergy.imageResId)
            tvAllergyName.text = allergy.name

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                resources.getDimensionPixelSize(R.dimen.auth_profile_allergy_chip_height)
            )

            if (index > 0) {
                params.marginStart =
                    resources.getDimensionPixelSize(R.dimen.auth_profile_allergy_chip_gap)
            }

            chipView.layoutParams = params
            selectedAllergyContainer.addView(chipView)
        }
    }

    private fun initClickListeners(view: View) {
        val btnBack = view.findViewById<ImageButton>(R.id.btnBack)
        val btnCamera = view.findViewById<View>(R.id.btnCamera)
        val etNickname = view.findViewById<EditText>(R.id.etNickname)
        val btnClearNickname = view.findViewById<FrameLayout>(R.id.btnClearNickname)
        val tvEditAllergy = view.findViewById<TextView>(R.id.tvEditAllergy)
        val btnStart = view.findViewById<TextView>(R.id.btnStart)

        btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        btnCamera.setOnClickListener {
            // TODO: 추후 프로필 이미지 선택 기능 연결
        }

        btnClearNickname.setOnClickListener {
            etNickname.text?.clear()
        }

        tvEditAllergy.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        btnStart.setOnClickListener {
            (requireActivity() as AuthActivity).moveToSignupComplete()
        }
    }
    private fun setupKeyboardAwareBottomButton(view: View) {
        val btnStart = view.findViewById<TextView>(R.id.btnStart)
        view.applyKeyboardAwareBottomMargin(btnStart)
    }

    companion object {
        private const val ARG_NICKNAME = "nickname"
        private const val ARG_SELECTED_ALLERGY_IDS = "selected_allergy_ids"

        fun newInstance(
            nickname: String,
            selectedAllergyIds: ArrayList<String>
        ): ProfileSettingFragment {
            return ProfileSettingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_NICKNAME, nickname)
                    putStringArrayList(ARG_SELECTED_ALLERGY_IDS, selectedAllergyIds)
                }
            }
        }
    }
}