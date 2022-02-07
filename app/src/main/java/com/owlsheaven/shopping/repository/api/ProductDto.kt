package com.owlsheaven.shopping.repository.api

import com.google.gson.annotations.SerializedName

/**
 * 상품 정보
 */
data class ProductDto(
    @field:SerializedName("id") val id:Long, // 상품 ID
    @field:SerializedName("name") val name:String, // 상품명
    @field:SerializedName("image") val imageUrl:String, // 이미지 링크
    @field:SerializedName("is_new") val isNew:Boolean, // 신규 여부
    @field:SerializedName("sell_count") val sellCount:Int, // 판매 갯수
    @field:SerializedName("actual_price") val actualPrice:Int, // 상품 가격
    @field:SerializedName("price") val price:Int, // 판매 가격 (할인 적용됨)
)