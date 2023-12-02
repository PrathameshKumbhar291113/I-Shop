package com.ishop.get_products_feature.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ishop.get_products_feature.domain.use_case.GetProductsUseCase
import com.ishop.network.models.GetProductsResponse
import com.ishop.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class GetProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {
    private val _getProductsList =
        MutableLiveData<NetworkResult<Response<List<GetProductsResponse>>>>()
    val getProductsList: LiveData<NetworkResult<Response<List<GetProductsResponse>>>> get() = _getProductsList

    init {
        getProductsList()
    }

    private fun getProductsList() {
        getProductsUseCase().onEach {
            when (it) {
                is NetworkResult.Loading -> {}
                is NetworkResult.Success -> {
                    _getProductsList.postValue(it)
                }
                is NetworkResult.Error -> {}
            }

        }.launchIn(viewModelScope)
    }
}