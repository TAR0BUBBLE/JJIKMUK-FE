package com.example.beforemerge.ui.auth

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.beforemerge.R

class AllergySelectionAdapter(
    private val onItemClick: (AllergyItem) -> Unit
) : RecyclerView.Adapter<AllergySelectionAdapter.AllergyViewHolder>() {

    private val items = mutableListOf<AllergyItem>()

    fun submitList(newItems: List<AllergyItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllergyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_allergy, parent, false)

        return AllergyViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: AllergyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class AllergyViewHolder(
        itemView: View,
        private val onItemClick: (AllergyItem) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val cardRoot = itemView.findViewById<FrameLayout>(R.id.cardRoot)
        private val iconCircle = itemView.findViewById<FrameLayout>(R.id.iconCircle)
        private val ivAllergy = itemView.findViewById<ImageView>(R.id.ivAllergy)
        private val tvAllergyName = itemView.findViewById<TextView>(R.id.tvAllergyName)
        private val ivCheckBadge = itemView.findViewById<ImageView>(R.id.ivCheckBadge)

        fun bind(item: AllergyItem) {
            ivAllergy.setImageResource(item.imageResId)
            tvAllergyName.text = item.name

            applySelectedState(item.isSelected)

            cardRoot.setOnClickListener {
                onItemClick(item)
            }
        }

        private fun applySelectedState(isSelected: Boolean) {
            val context = itemView.context

            cardRoot.setBackgroundResource(
                if (isSelected) {
                    R.drawable.bg_allergy_card_selected
                } else {
                    R.drawable.bg_allergy_card_default
                }
            )

            iconCircle.setBackgroundResource(
                if (isSelected) {
                    R.drawable.bg_allergy_icon_circle_selected
                } else {
                    R.drawable.bg_allergy_icon_circle_default
                }
            )

            tvAllergyName.setTextColor(
                ContextCompat.getColor(
                    context,
                    if (isSelected) {
                        R.color.auth_allergy_selected_blue
                    } else {
                        R.color.auth_text_primary
                    }
                )
            )

            ivCheckBadge.visibility = if (isSelected) {
                View.VISIBLE
            } else {
                View.GONE
            }

            ViewCompat.setElevation(
                cardRoot,
                context.resources.getDimension(
                    if (isSelected) {
                        R.dimen.auth_allergy_card_selected_elevation
                    } else {
                        R.dimen.auth_allergy_card_elevation
                    }
                )
            )

            ViewCompat.setElevation(
                iconCircle,
                context.resources.getDimension(
                    if (isSelected) {
                        R.dimen.auth_allergy_icon_circle_selected_elevation
                    } else {
                        R.dimen.auth_allergy_icon_circle_elevation
                    }
                )
            )
        }
    }
}