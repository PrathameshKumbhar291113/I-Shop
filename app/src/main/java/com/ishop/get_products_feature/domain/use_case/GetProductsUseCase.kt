package com.ishop.get_products_feature.domain.use_case

import com.ishop.get_products_feature.domain.repository.GetProductsRepository
import com.ishop.network.models.GetProductsResponse
import com.ishop.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(private val getProductsRepository: GetProductsRepository){
    operator fun invoke() = flow<NetworkResult<Response<List<GetProductsResponse>>>> {
        emit(NetworkResult.Loading())
        emit(NetworkResult.Success(data = getProductsRepository.getProductList()))
    }.catch {
        emit(NetworkResult.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}