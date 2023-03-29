package com.example.products_mvvm.all_products.view

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.products_mvvm.R
import com.example.products_mvvm.all_products.viewmodel.AllProductsViewModel
import com.example.products_mvvm.all_products.viewmodel.AllProductsViewModelFactory
import com.example.products_mvvm.db.ConcreteLocalSource
import com.example.products_mvvm.model.Repository
import com.example.products_mvvm.network.ApiState
import com.example.products_mvvm.network.ConcreteRemoteSource
import com.example.products_mvvm.product_details.ProductDetailsActivity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

class ProductsActivity : AppCompatActivity() {
    private val TAG = "ProductsActivity"
    private lateinit var allProductsViewModel : AllProductsViewModel
    private lateinit var factory: AllProductsViewModelFactory

    //UI
    private lateinit var progressBar : ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var productsAdapter: ProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        initUi()
        factory = AllProductsViewModelFactory(
            Repository.getInstance(
                ConcreteLocalSource(this),
                ConcreteRemoteSource()
            ),
            this
        )
        productsAdapter = ProductsAdapter(
            this,
            {//card clicked
                val intent = Intent(this, ProductDetailsActivity::class.java)
                intent.putExtra("product",it)
                startActivity(intent)
            },
            {//fav btn clicked
                allProductsViewModel.addProduct(it)
            }
        )
        allProductsViewModel = ViewModelProvider(this,factory).get(AllProductsViewModel::class.java)

        productsAdapter.submitList(listOf())

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)
            adapter = productsAdapter
        }

        //observing on list on the viewModel
/*        allProductsViewModel.productsList.observe(this){
            Log.i(TAG, "onCreate: i can't believe it${it.size}")
            progressBar.visibility = View.GONE
            productsAdapter.submitList(it)
        }*/
        lifecycleScope.launchWhenCreated {
            allProductsViewModel.productsList.collectLatest{
                when(it){
                    is ApiState.Success ->{
                        productsAdapter.submitList(it.data)
                        progressBar.visibility = View.GONE
                    }
                    is ApiState.Loading ->{
                        progressBar.visibility = View.VISIBLE
                    }
                    else ->{
                        progressBar.visibility = View.GONE
                        Toast.makeText(this@ProductsActivity,"Error in api",Toast.LENGTH_SHORT).show()
                        Log.i(TAG, "onCreate: error in getting the data")
                    }
                }
            }
        }
    }

    private fun initUi(){
        progressBar = findViewById(R.id.progressBar)
        recyclerView = findViewById(R.id.recyclerView)
    }
}