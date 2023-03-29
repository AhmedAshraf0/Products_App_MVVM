package com.example.products_mvvm.db

import android.content.Context
import com.example.products_mvvm.model.ProductModel
import kotlinx.coroutines.flow.Flow

class ConcreteLocalSource(context : Context): LocalSource{
    private val dao : ProductsDao by lazy {
        val productsDB = ProductsDB.getInstance(context)
        productsDB.getProductDao()
    }
    override suspend fun getFavorites(): Flow<List<ProductModel>> {
        return dao.getAllProducts()
    }

    override suspend fun insertFavProduct(product: ProductModel) {
        dao.insertProduct(product)
    }

    override suspend fun deleteFavProduct(product: ProductModel) {
        dao.deleteProduct(product)
    }
}