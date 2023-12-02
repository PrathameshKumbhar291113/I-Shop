package com.ishop.get_products_feature.data.di

import com.ishop.get_products_feature.data.repository.GetProductsRepoImpl
import com.ishop.get_products_feature.domain.repository.GetProductsRepository
import com.ishop.network.ApiCommunicator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GetProductsDataModule {
    @Provides
    @Singleton
    fun providesGetProductsRepository(apiCommunicator: ApiCommunicator): GetProductsRepository{
        return GetProductsRepoImpl(apiCommunicator)
    }
}