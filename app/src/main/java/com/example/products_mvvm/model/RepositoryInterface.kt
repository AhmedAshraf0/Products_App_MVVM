package com.example.products_mvvm.model

import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RepositoryInterface {
    fun getOnlineProducts() : Flow<Response<ProductJson>>
    suspend fun getLocalProducts() : Flow<List<ProductModel>>
    suspend fun insertProduct(product:ProductModel)
    suspend fun deleteProduct(product:ProductModel)
}