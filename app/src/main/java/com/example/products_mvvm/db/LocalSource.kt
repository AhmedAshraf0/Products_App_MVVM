package com.example.products_mvvm.db

import com.example.products_mvvm.model.ProductModel
import kotlinx.coroutines.flow.Flow

interface LocalSource {
    suspend fun getFavorites() : Flow<List<ProductModel>>
    suspend fun insertFavProduct(product:ProductModel)
    suspend fun deleteFavProduct(product:ProductModel)
}