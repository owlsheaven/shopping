package com.owlsheaven.shopping.repository.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 서버로 부터 상품 목록 가져오기
 */
interface ProductQuery {

    @GET(INIT_PRODUCT)
    suspend fun getInitProductList(
    ): ProductListDto

    @GET(NEXT_PRODUCT)
    suspend fun getNextProductList(
        @Query("lastId") lastId: Long
    ): ProductListDto

    companion object {
        private const val BASE_URL = "https://d2bab9i9pr8lds.cloudfront.net/"
        private const val INIT_PRODUCT = "api/home"
        private const val NEXT_PRODUCT = "$INIT_PRODUCT/goods"

        fun create(): ProductQuery {
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ProductQuery::class.java)
        }
   }
}