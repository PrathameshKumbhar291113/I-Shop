package com.ishop.get_products_feature.domain.di

import com.ishop.get_products_feature.domain.repository.GetProductsRepository
import com.ishop.get_products_feature.domain.use_case.GetProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GetProductsDomainModule {
    @Provides
    @Singleton
    fun getProducts(
        getProductsRepository: GetProductsRepository
    ): GetProductsUseCase{
        return GetProductsUseCase(getProductsRepository)
    }
}