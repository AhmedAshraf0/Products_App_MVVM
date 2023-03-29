package com.example.products_mvvm.all_products.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.products_mvvm.model.ProductModel
import com.example.products_mvvm.model.RepositoryInterface
import com.example.products_mvvm.network.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AllProductsViewModel(
    private val _repo : RepositoryInterface,private val context:Context) :ViewModel(){
    private val TAG = "AllProductsViewModel class"

    private var _productsList = MutableStateFlow<ApiState>(ApiState.Loading)//editable here only
    val productsList : MutableStateFlow<ApiState> = _productsList//can't be edited from user only me could reassign it

    init {
        Log.i(TAG, "created AllProductsViewModel class: ")
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            _repo.getOnlineProducts()
                .catch {
                    _productsList.value = ApiState.Failure(it)
                }
                .collect{
                _productsList.value = ApiState.Success(it.body()!!.products)
            }
        }
    }
    fun addProduct(productModel: ProductModel){
        viewModelScope.launch(Dispatchers.IO) {
            _repo.insertProduct(productModel)
            withContext(Dispatchers.Main){
                Log.i(TAG, "inserted product: succeed")
                Toast.makeText(context,"Added Successfully",Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "onCleared")
    }
}