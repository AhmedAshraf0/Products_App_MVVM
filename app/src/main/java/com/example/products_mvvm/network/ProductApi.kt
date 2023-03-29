package com.example.products_mvvm.network

import com.example.products_mvvm.model.ProductJson
import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {
    @GET("products")
    suspend fun getProducts():Response<ProductJson>
}