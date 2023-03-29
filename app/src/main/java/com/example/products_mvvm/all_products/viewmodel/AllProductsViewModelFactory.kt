package com.example.products_mvvm.all_products.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.products_mvvm.model.RepositoryInterface

class AllProductsViewModelFactory(
    private val _repo : RepositoryInterface,
    private val context:Context)
    :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(AllProductsViewModel::class.java)){
            AllProductsViewModel(_repo,context) as T
        }else{
            throw IllegalArgumentException("AllProductsViewModel isn't existed")
        }
    }
}