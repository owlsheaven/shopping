package com.owlsheaven.shopping.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.owlsheaven.shopping.repository.api.ProductQuery
import com.owlsheaven.shopping.repository.db.BannerEntity
import com.owlsheaven.shopping.repository.db.ProductEntity
import com.owlsheaven.shopping.repository.db.ShoppingDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 상품 목록 가져오기
 */
@Singleton
class Repository @Inject constructor(
    private val db: ShoppingDatabase,
    private val query: ProductQuery
) {

    @OptIn(ExperimentalPagingApi::class)
    fun products(pageSize: Int = DEFAULT_PAGE_SIZE): Flow<PagingData<ProductEntity>> {
        return Pager(
            config = PagingConfig(pageSize),
            remoteMediator = PageKeyedRemoteMediator(db, query)
        ) {
            db.productDao().products()
        }.flow
    }

    @OptIn(ExperimentalPagingApi::class)
    fun favoriteProducts(pageSize: Int = DEFAULT_PAGE_SIZE): Flow<PagingData<ProductEntity>> {
        return Pager(
            config = PagingConfig(pageSize),
            remoteMediator = PageKeyedRemoteMediator(db, query)
        ) {
            db.productDao().favoriteProducts()
        }.flow
    }

    suspend fun updateFavorite(productId: Long, isFavorite: Boolean) {
        try {
            db.productDao().updateFavorite(productId, if (isFavorite) 1 else 0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun banners(): Flow<List<BannerEntity>> {
        return db.bannerDao().getBanners()
    }

    companion object {
        private const val DEFAULT_PAGE_SIZE = 10
    }
}