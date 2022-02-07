package com.owlsheaven.shopping.ui.favorite

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.owlsheaven.shopping.repository.db.ProductEntity
import com.owlsheaven.shopping.viewmodel.CommonViewModel

class FavoriteProductAdapter(private val viewModel: CommonViewModel)
    : PagingDataAdapter<ProductEntity, FavoriteProductViewHolder>(ProductEntity.DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: FavoriteProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteProductViewHolder {
        return FavoriteProductViewHolder.create(parent, viewModel)
    }

}