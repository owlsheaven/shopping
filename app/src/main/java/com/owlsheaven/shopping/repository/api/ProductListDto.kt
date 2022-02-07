package com.owlsheaven.shopping.repository.api

import com.google.gson.annotations.SerializedName


/**
 * 전체 상품 목록 조회 결과
 * + [banners] 와 [products] 목록으로 구성되어 있다.
 * + [banners] 항목은 나타나지 않을 수 있다.
 */
data class ProductListDto(
    @field:SerializedName("banners") val banners:List<BannerDto>? = null, // 베너 목록
    @field:SerializedName("goods") val products:List<ProductDto> // 상품 목록
)