package com.owlsheaven.shopping.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.owlsheaven.shopping.ui.GlideApp

object BindingAdapterUtil {
    private const val FAVORITE_ENABLED_ALPHA = 1f
    private const val FAVORITE_DISABLED_ALPHA = 0.2f

    @BindingAdapter("product_formatted_price")
    @JvmStatic
    fun bindProductFormattedPrice(view: TextView, price:Int){
        view.text = TextUtil.formattedNumber(price)
    }

    @BindingAdapter("visibility_gone_unless")
    @JvmStatic
    fun bindVisibilityGoneUnless(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

    @BindingAdapter("favorite_alpha")
    @JvmStatic
    fun bindFavoriteAlpha(view: View, isEnabled: Boolean) {
        view.alpha = if (isEnabled) FAVORITE_ENABLED_ALPHA else FAVORITE_DISABLED_ALPHA
    }

    @BindingAdapter("product_selling_description")
    @JvmStatic
    fun bindProductSellingDescription(view: TextView, sellCount:Int){
        view.text = "${TextUtil.formattedNumber(sellCount)}개 구매중"
    }

    @BindingAdapter("image_url")
    @JvmStatic
    fun bindImageUrl(view: ImageView, url: String? = null) {
        if (url?.startsWith("http") == true) {
            GlideApp.with(view.context)
                .load(url)
                .optionalCenterCrop()
                .skipMemoryCache(true) // for offline
                .into(view)
        }
    }

    @BindingAdapter(*["actualPrice","price"], requireAll = true)
    @JvmStatic
    fun bindProductFormattedDiscount(view: TextView, actualPrice: Int, price: Int) {
        view.text = TextUtil.formattedDiscount(actualPrice, price)
    }
}