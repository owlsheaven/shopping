package com.owlsheaven.shopping.repository.api

import com.google.gson.annotations.SerializedName

/**
 * 베너 정보
 */
data class BannerDto(
    @field:SerializedName("id") val id:Long, // 상품 ID
    @field:SerializedName("image") val imageUrl:String // 이미지 링크 (https://...)
)
