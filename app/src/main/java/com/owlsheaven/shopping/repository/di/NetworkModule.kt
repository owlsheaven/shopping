package com.owlsheaven.shopping.repository.di

import com.owlsheaven.shopping.repository.api.ProductQuery
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideProductQuery(): ProductQuery {
        return ProductQuery.create()
    }
}