package com.example.products_mvvm.favorites.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.example.products_mvvm.model.ProductModel
import com.example.products_mvvm.model.RepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesViewModel(
    private val _repo : RepositoryInterface,
    private val context: Context) :
    ViewModel() {
    private val TAG = "FavoritesViewModel class"
    private var _favoritesList = MutableLiveData<List<ProductModel>>()//editable here only
    val favoritesList : LiveData<List<ProductModel>> = _favoritesList//can't be edited from user only me could reassign it
    init {
        Log.i(TAG, "created AllProductsViewModel class: ")
        getFavorites()
    }

    private fun getFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            _repo.getLocalProducts().collect{
                _favoritesList.postValue(it)
            }
//            withContext(Dispatchers.Main){
//                _favoritesList.postValue(dumbList.asLiveData().value)
//                Log.i(TAG, "getProducts: ${_favoritesList.value?.size}")
//            }
        }
    }
    fun deleteFavorite(productModel: ProductModel){
        viewModelScope.launch(Dispatchers.IO) {
            _repo.deleteProduct(productModel)
            withContext(Dispatchers.Main){
                Log.i(TAG, "deleted product: succeed")
                Toast.makeText(context,"Deleted Successfully", Toast.LENGTH_SHORT).show()
            }
        }
        getFavorites()//to upgrade current list & UI
    }
    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "onCleared")
    }
}