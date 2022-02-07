package com.owlsheaven.shopping.repository.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [BannerEntity::class, ProductEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ShoppingDatabase: RoomDatabase() {

    abstract fun bannerDao(): BannerDao
    abstract fun productDao(): ProductDao

    companion object {
        @Volatile private var INSTANCE: ShoppingDatabase ?= null

        fun getInstance(context: Context): ShoppingDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): ShoppingDatabase {
            return Room.databaseBuilder(context, ShoppingDatabase::class.java, "shopping.db")
                .fallbackToDestructiveMigration()
                .build()
        }


    }
}