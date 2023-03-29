package com.example.products_mvvm.favorites.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.products_mvvm.model.RepositoryInterface

class FavoritesViewModelFactory (
    private val _repo : RepositoryInterface,
    private val context: Context
)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(FavoritesViewModel::class.java)){
            FavoritesViewModel(_repo,context) as T
        }else{
            throw IllegalArgumentException("FavoritesViewModel isn't existed")
        }
    }
}