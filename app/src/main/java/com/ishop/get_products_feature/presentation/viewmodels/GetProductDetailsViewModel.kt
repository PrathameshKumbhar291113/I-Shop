package com.ishop.get_products_feature.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ishop.network.models.GetProductsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GetProductDetailsViewModel @Inject constructor() : ViewModel(){

    private val _productDetails = MutableLiveData<GetProductsResponse>()
    val productDetails : LiveData<GetProductsResponse> = _productDetails

    fun setProductDetails(productsResponse: GetProductsResponse){
        _productDetails.value = productsResponse
    }

}