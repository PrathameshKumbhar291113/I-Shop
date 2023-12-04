package com.ishop.network.requests

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody

data class AddProductRequest(
    @SerializedName("product_name")
    val product_name: String,
    @SerializedName("product_type")
    val product_type: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("tax")
    val tax: String,

    val images: MultipartBody.Part? = null
)
fun createAddProductRequestBody(
    productName: String,
    productType: String,
    price: String,
    tax: String,
    image: MultipartBody.Part?
): MultipartBody {
    val builder = MultipartBody.Builder()
        .setType(MultipartBody.FORM)
        .addFormDataPart("product_name", productName)
        .addFormDataPart("product_type", productType)
        .addFormDataPart("price", price)
        .addFormDataPart("tax", tax)

    image?.let {
        builder.addPart(it)
    }

    return builder.build()
}
