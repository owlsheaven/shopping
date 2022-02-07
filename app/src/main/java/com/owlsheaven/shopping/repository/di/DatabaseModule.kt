package com.owlsheaven.shopping.repository.di

import android.content.Context
import com.owlsheaven.shopping.repository.db.BannerDao
import com.owlsheaven.shopping.repository.db.ProductDao
import com.owlsheaven.shopping.repository.db.ShoppingDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideShoppingDatabase(@ApplicationContext context: Context): ShoppingDatabase {
        return ShoppingDatabase.getInstance(context)
    }

    @Provides
    fun provideBannerDao(database: ShoppingDatabase): BannerDao {
        return database.bannerDao()
    }

    @Provides
    fun provideProductDao(database: ShoppingDatabase): ProductDao {
        return database.productDao()
    }
}