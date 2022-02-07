package com.owlsheaven.shopping.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.owlsheaven.shopping.R
import com.owlsheaven.shopping.databinding.ProductItemBinding
import com.owlsheaven.shopping.repository.db.ProductEntity
import com.owlsheaven.shopping.viewmodel.CommonViewModel

class HomeProductViewHolder(
    private val binding: ProductItemBinding, private val viewModel: CommonViewModel
    ): RecyclerView.ViewHolder(binding.root) {

    /**
     * 바인드된 상품 정보. favorite 갱신시 사용
     */
    private lateinit var current: BindingProduct

    fun bind(product: ProductEntity?) {
        if (product == null) return
        binding.product = product
        binding.commonViewModel = viewModel
    }

    private data class BindingProduct(val id: Long, var isFavorite: Boolean)

    companion object {

        fun create(parent: ViewGroup, viewModel: CommonViewModel): HomeProductViewHolder {

            val view = DataBindingUtil.inflate<ProductItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.product_item, parent,false
            )
            return HomeProductViewHolder(view, viewModel)
        }
    }

}