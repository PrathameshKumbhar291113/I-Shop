package com.ishop.network


import com.ishop.network.models.AddProductsResponse
import com.ishop.network.models.GetProductsResponse
import com.ishop.utils.RestConstants
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiCommunicator {
    @GET(RestConstants.GET_PRODUCT)
    suspend fun getProductsList(): Response<List<GetProductsResponse>>
    @POST(RestConstants.ADD_PRODUCT)
    suspend fun addProduct(@Body productDetails: MultipartBody) : Response<AddProductsResponse>
}