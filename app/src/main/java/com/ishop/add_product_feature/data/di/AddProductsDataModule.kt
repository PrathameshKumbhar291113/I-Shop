package com.ishop.add_product_feature.data.di

import com.ishop.add_product_feature.data.repository.AddProductsRepoImpl
import com.ishop.add_product_feature.domain.repository.AddProductsRepository
import com.ishop.network.ApiCommunicator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AddProductsDataModule {
    @Provides
    @Singleton
    fun providesAddProductsRepository(apiCommunicator: ApiCommunicator): AddProductsRepository {
        return AddProductsRepoImpl(apiCommunicator)
    }
}