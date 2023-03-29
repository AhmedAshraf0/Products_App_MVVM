package com.example.products_mvvm

import com.example.products_mvvm.network.ProductApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private const val BASE_URL = "https://www.dummyjson.com/"
    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
}
object API{
    val retrofitService : ProductApi by lazy {
        RetrofitHelper.retrofit.create(ProductApi::class.java)
    }
}