package com.example.products_mvvm.network

import com.example.products_mvvm.API
import com.example.products_mvvm.model.ProductJson
import com.example.products_mvvm.model.ProductModel
import retrofit2.Response

class ConcreteRemoteSource : RemoteSource{

    override suspend fun getProductsOverNetwork(): Response<ProductJson> {
        return API.retrofitService.getProducts()//return could be null
    }
}