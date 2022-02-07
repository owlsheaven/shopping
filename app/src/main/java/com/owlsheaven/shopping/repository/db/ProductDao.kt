package com.owlsheaven.shopping.repository.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {

    @Query("SELECT * FROM products")
    fun products(): PagingSource<Int, ProductEntity>

    @Query("SELECT * FROM products WHERE is_favorite = 1")
    fun favoriteProducts(): PagingSource<Int, ProductEntity>

    @Query("DELETE FROM products")
    fun deletes()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserts(banners: List<ProductEntity>)

    @Query("UPDATE products SET is_favorite =:isEnabled WHERE product_id =:productId")
    suspend fun updateFavorite(productId: Long, isEnabled: Int)

    @Query("SELECT product_id FROM products WHERE is_favorite = 1")
    fun getFavoriteProductIdList(): List<Long>
}