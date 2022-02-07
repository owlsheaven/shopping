package com.owlsheaven.shopping.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.owlsheaven.shopping.repository.db.BannerEntity

class HomeBannerAdapter: RecyclerView.Adapter<HomeBannerViewHolder>() {

    private val items = mutableListOf<BannerEntity>()

    fun submit(banners: List<BannerEntity>) {
        items.clear()
        items.addAll(banners)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeBannerViewHolder {
        return HomeBannerViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: HomeBannerViewHolder, position: Int) {
        holder.bind(items.getOrNull(position))
    }

    override fun getItemCount(): Int {
        return items.size
    }

}