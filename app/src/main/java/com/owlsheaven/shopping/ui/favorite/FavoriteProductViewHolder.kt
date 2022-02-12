package com.owlsheaven.shopping.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.owlsheaven.shopping.R
import com.owlsheaven.shopping.databinding.ProductItemBinding
import com.owlsheaven.shopping.repository.db.ProductEntity
import com.owlsheaven.shopping.ui.common.CommonViewModel

class FavoriteProductViewHolder (
    private val binding: ProductItemBinding, private val viewModel: CommonViewModel
    ): RecyclerView.ViewHolder(binding.root) {

    fun bind(product: ProductEntity?) {
        if (product == null) return
        binding.product = product
        binding.commonViewModel = viewModel
    }

    companion object {

        fun create(parent: ViewGroup, viewModel: CommonViewModel): FavoriteProductViewHolder {

            val view = DataBindingUtil.inflate<ProductItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.product_item, parent,false
            )

            return FavoriteProductViewHolder(view, viewModel)
        }
    }
}