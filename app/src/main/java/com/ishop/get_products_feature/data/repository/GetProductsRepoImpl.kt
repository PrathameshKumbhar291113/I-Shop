package com.ishop.get_products_feature.data.repository

import com.ishop.get_products_feature.domain.repository.GetProductsRepository
import com.ishop.network.ApiCommunicator
import com.ishop.network.models.GetProductsResponse
import retrofit2.Response
import javax.inject.Inject

class GetProductsRepoImpl @Inject constructor(private val apiCommunicator: ApiCommunicator) : GetProductsRepository{
    override suspend fun getProductList(): Response<List<GetProductsResponse>> {
        return apiCommunicator.getProductsList()
    }
}