package com.owlsheaven.shopping.utils

import java.text.NumberFormat
import java.util.*

object TextUtil {
    private val NUM_FMT = NumberFormat.getInstance(Locale.getDefault()).apply {
        maximumFractionDigits = 2
    }

    fun formattedNumber(number: Int): String {
        return NUM_FMT.format(number)
    }

    /**
     * 원래 가격 [actualPrice] 값 과 할인된 가격 [price] 값으로 할인율 문자열을 생성한다.
     * + 비교값은 0 보다 크고 같지 않아야한다.
     */
    fun formattedDiscount(actualPrice: Int, price: Int): String {
        val ap = actualPrice.toDouble()
        val p = price.toDouble()

        val percent = ((ap - p) / ap) * 100f

        return "${percent.toInt()}%"
    }
}