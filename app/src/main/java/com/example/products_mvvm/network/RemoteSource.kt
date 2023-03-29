package com.example.products_mvvm.network

import com.example.products_mvvm.model.ProductJson
import com.example.products_mvvm.model.ProductModel
import retrofit2.Response

interface RemoteSource {
    suspend fun getProductsOverNetwork() : Response<ProductJson>
}