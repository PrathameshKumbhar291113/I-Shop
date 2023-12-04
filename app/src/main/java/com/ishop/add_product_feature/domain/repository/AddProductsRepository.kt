package com.ishop.add_product_feature.domain.repository

import com.ishop.network.models.AddProductsResponse
import okhttp3.MultipartBody
import retrofit2.Response

interface AddProductsRepository {
    suspend fun addProducts(productDetails: MultipartBody): Response<AddProductsResponse>
}