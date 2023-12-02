package com.ishop.network.models


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class AddProductsResponse(
    @SerializedName("message")
    var message: String?, // Product added Successfully!
    @SerializedName("product_details")
    var productDetails: ProductDetails?,
    @SerializedName("product_id")
    var productId: Int?, // 2657
    @SerializedName("success")
    var success: Boolean? // true
) : Parcelable {
    @Parcelize
    class ProductDetails : Parcelable
}