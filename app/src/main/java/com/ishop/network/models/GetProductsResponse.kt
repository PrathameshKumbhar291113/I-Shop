package com.ishop.network.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class GetProductsResponse(
    @SerializedName("image")
    var image: String?,
    @SerializedName("price")
    var price: Double?, // 25.42372881
    @SerializedName("product_name")
    var productName: String?, // locke
    @SerializedName("product_type")
    var productType: String?, // Product
    @SerializedName("tax")
    var tax: Double? // 0.2
) : Parcelable

