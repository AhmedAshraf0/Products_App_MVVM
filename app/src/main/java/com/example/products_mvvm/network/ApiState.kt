package com.example.products_mvvm.network

import com.example.products_mvvm.model.ProductModel

sealed class ApiState{
    class Success(val data : List<ProductModel>) : ApiState()
    class Failure(val msg : Throwable) : ApiState()
    object Loading : ApiState()
}
