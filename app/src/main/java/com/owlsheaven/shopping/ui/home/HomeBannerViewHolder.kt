package com.owlsheaven.shopping.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.owlsheaven.shopping.R
import com.owlsheaven.shopping.databinding.BannerItemBinding
import com.owlsheaven.shopping.repository.db.BannerEntity

class HomeBannerViewHolder(private val binding: BannerItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(banner: BannerEntity?) {
        if (banner == null) return
        binding.banner = banner
    }

    companion object {
        fun create(parent: ViewGroup): HomeBannerViewHolder {

            val view = DataBindingUtil.inflate<BannerItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.banner_item, parent, false
            )

            return HomeBannerViewHolder(view)
        }
    }
}