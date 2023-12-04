package com.ishop.add_product_feature.domain.di

import com.ishop.add_product_feature.domain.repository.AddProductsRepository
import com.ishop.add_product_feature.domain.use_case.AddProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AddProductsDomainModule {

    @Provides
    @Singleton
    fun addProducts(
        addProductsRepository: AddProductsRepository
    ): AddProductsUseCase{
        return AddProductsUseCase(addProductsRepository)
    }
}