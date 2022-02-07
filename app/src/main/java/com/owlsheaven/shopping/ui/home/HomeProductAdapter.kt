package com.owlsheaven.shopping.ui.home

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.owlsheaven.shopping.repository.db.ProductEntity
import com.owlsheaven.shopping.viewmodel.CommonViewModel

class HomeProductAdapter(private val viewModel: CommonViewModel)
    : PagingDataAdapter<ProductEntity, HomeProductViewHolder>(ProductEntity.DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: HomeProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeProductViewHolder {
        return HomeProductViewHolder.create(parent, viewModel)
    }

}