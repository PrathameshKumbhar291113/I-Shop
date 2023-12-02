package com.ishop.get_products_feature.domain.repository


import com.ishop.network.models.GetProductsResponse
import retrofit2.Response

interface GetProductsRepository {
    suspend fun getProductList(): Response<List<GetProductsResponse>>
}