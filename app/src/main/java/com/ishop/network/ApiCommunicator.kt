package com.ishop.network


import com.ishop.network.models.GetProductsResponse
import com.ishop.utils.RestConstants
import retrofit2.Response
import retrofit2.http.GET

interface ApiCommunicator {
    @GET(RestConstants.GET_PRODUCT)
    suspend fun getProductsList(): Response<List<GetProductsResponse>>
}