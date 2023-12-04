package com.ishop.add_product_feature.domain.use_case

import com.ishop.add_product_feature.domain.repository.AddProductsRepository
import com.ishop.network.models.AddProductsResponse
import com.ishop.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

class AddProductsUseCase @Inject constructor(private val addProductsRepository: AddProductsRepository) {
    operator fun invoke(addProductRequest: MultipartBody): Flow<NetworkResult<Response<AddProductsResponse>>> = flow<NetworkResult<Response<AddProductsResponse>>> {
        emit(NetworkResult.Loading())
        emit(NetworkResult.Success(data = addProductsRepository.addProducts(addProductRequest)))
    }.catch {
        emit(NetworkResult.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}