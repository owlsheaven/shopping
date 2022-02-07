package com.owlsheaven.shopping.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.owlsheaven.shopping.repository.api.ProductQuery
import com.owlsheaven.shopping.repository.db.BannerEntity
import com.owlsheaven.shopping.repository.db.ProductEntity
import com.owlsheaven.shopping.repository.db.ShoppingDatabase

@OptIn(ExperimentalPagingApi::class)
class PageKeyedRemoteMediator(
    private val db: ShoppingDatabase,
    private val query: ProductQuery
): RemoteMediator<Int, ProductEntity>() {

    /**
     * 마지막 키
     */
    private var lastKey = KEY_NONE

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ProductEntity>
    ): MediatorResult {
        try {
            // key
            val key = when(loadType) {
                LoadType.REFRESH -> KEY_INIT
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = false)
                LoadType.APPEND -> {
                    if (lastKey == KEY_NONE) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    lastKey
                }
            }
            // 서버로 쿼리
            val response = if (key == KEY_INIT) {
                query.getInitProductList()
            } else {
                query.getNextProductList(key)
            }

            // 결과를 DB 에 저장
            db.withTransaction {
                // favorite 정보 가져오기
                val favoriteIds = db.productDao().getFavoriteProductIdList()

                // 기존 데이터 삭제
                if (LoadType.REFRESH == loadType) {
                    db.bannerDao().deletes()
                    db.productDao().deletes()
                }

                // banner 저장
                response.banners?.let { dto ->
                    db.bannerDao().inserts(dto.map { BannerEntity.from(it) })
                }
                // product 저장
                response.products.let { dto ->
                    db.productDao().inserts(dto.map { ProductEntity.from(it, favoriteIds.contains(it.id)) })
                }
            }

            // key
            lastKey = response.products.maxByOrNull { it.id }?.id ?: KEY_NONE

            return MediatorResult.Success(endOfPaginationReached = (lastKey == KEY_NONE))

        } catch (e: Exception) {
            e.printStackTrace()
            return MediatorResult.Error(e)
        }
    }

    companion object {
        private const val KEY_NONE = -1L
        private const val KEY_INIT = 0L
    }
}