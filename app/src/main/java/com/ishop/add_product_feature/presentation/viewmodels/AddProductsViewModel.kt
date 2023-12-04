package com.ishop.add_product_feature.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ishop.add_product_feature.domain.use_case.AddProductsUseCase
import com.ishop.network.models.AddProductsResponse
import com.ishop.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AddProductsViewModel @Inject constructor(private val addProductsUseCase: AddProductsUseCase) :
    ViewModel() {

    private val _addProductResponse =
        MutableLiveData<NetworkResult<Response<AddProductsResponse>>>()
    val addProductsResponse: LiveData<NetworkResult<Response<AddProductsResponse>>> =
        _addProductResponse

    private val _image = MutableLiveData<MultipartBody.Part>()
    val image: LiveData<MultipartBody.Part> = _image

    fun getImage(image: MultipartBody.Part) {
        _image.value = image
    }

    fun addProducts(productDetails: MultipartBody) {
        addProductsUseCase(productDetails).onEach {
            _addProductResponse.postValue(it)
        }.launchIn(viewModelScope)
    }
}