package com.owlsheaven.shopping.repository.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BannerDao {
    @Query("SELECT * FROM banners")
    fun getBanners(): Flow<List<BannerEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserts(banners: List<BannerEntity>)

    @Query("DELETE FROM banners")
    fun deletes()
}