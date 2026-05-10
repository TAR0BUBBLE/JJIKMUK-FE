package com.example.beforemerge.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.beforemerge.R
import com.example.beforemerge.ui.model.RecommendProduct

class RecommendProductAdapter(
    private val onItemClick: (RecommendProduct) -> Unit
) : ListAdapter<RecommendProduct, RecommendProductAdapter.RecommendProductViewHolder>(
    RecommendProductDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recommend_product, parent, false)

        return RecommendProductViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: RecommendProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class RecommendProductViewHolder(
        itemView: View,
        private val onItemClick: (RecommendProduct) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val tvProductCategory: TextView =
            itemView.findViewById(R.id.tvProductCategory)

        private val tvProductName: TextView =
            itemView.findViewById(R.id.tvProductName)

        private val tvAllergyTag1: TextView =
            itemView.findViewById(R.id.tvAllergyTag1)

        private val tvAllergyTag2: TextView =
            itemView.findViewById(R.id.tvAllergyTag2)

        private val ivProductImage: ImageView =
            itemView.findViewById(R.id.ivProductImage)

        fun bind(product: RecommendProduct) {
            tvProductCategory.text = product.category
            tvProductName.text = product.name
            ivProductImage.setImageResource(product.imageResId)

            val firstTag = product.allergyTags.getOrNull(0)
            val secondTag = product.allergyTags.getOrNull(1)

            if (firstTag != null) {
                tvAllergyTag1.visibility = View.VISIBLE
                tvAllergyTag1.text = firstTag
            } else {
                tvAllergyTag1.visibility = View.GONE
            }

            if (secondTag != null) {
                tvAllergyTag2.visibility = View.VISIBLE
                tvAllergyTag2.text = secondTag
            } else {
                tvAllergyTag2.visibility = View.GONE
            }

            itemView.setOnClickListener {
                onItemClick(product)
            }
        }
    }

    private class RecommendProductDiffCallback : DiffUtil.ItemCallback<RecommendProduct>() {
        override fun areItemsTheSame(
            oldItem: RecommendProduct,
            newItem: RecommendProduct
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RecommendProduct,
            newItem: RecommendProduct
        ): Boolean {
            return oldItem == newItem
        }
    }
}