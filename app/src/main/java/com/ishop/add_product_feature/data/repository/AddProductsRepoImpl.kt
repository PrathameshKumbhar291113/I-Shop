package com.ishop.add_product_feature.data.repository

import com.ishop.add_product_feature.domain.repository.AddProductsRepository
import com.ishop.network.ApiCommunicator
import com.ishop.network.models.AddProductsResponse
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

class AddProductsRepoImpl @Inject constructor(private val apiCommunicator: ApiCommunicator) : AddProductsRepository{
    override suspend fun addProducts(productDetails: MultipartBody): Response<AddProductsResponse> {
        return apiCommunicator.addProduct(productDetails)
    }
}