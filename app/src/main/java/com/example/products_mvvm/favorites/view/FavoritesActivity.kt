package com.example.products_mvvm.favorites.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.products_mvvm.R
import com.example.products_mvvm.db.ConcreteLocalSource
import com.example.products_mvvm.favorites.viewmodel.FavoritesViewModel
import com.example.products_mvvm.favorites.viewmodel.FavoritesViewModelFactory
import com.example.products_mvvm.model.Repository
import com.example.products_mvvm.network.ConcreteRemoteSource
import com.example.products_mvvm.product_details.ProductDetailsActivity

class FavoritesActivity : AppCompatActivity() {
    private val TAG = "FavoritesActivity"
    private lateinit var favoritesViewModel: FavoritesViewModel
    private lateinit var factory: FavoritesViewModelFactory
    private lateinit var progressBar : ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var favoritesAdapter: FavoritesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)
        initUi()
        factory = FavoritesViewModelFactory(
            Repository.getInstance(
                ConcreteLocalSource(this),
                ConcreteRemoteSource()
            ),
            this
        )
        favoritesAdapter = FavoritesAdapter(
            this,
            {//card clicked
                val intent = Intent(this, ProductDetailsActivity::class.java)
                intent.putExtra("product",it)
                startActivity(intent)
            },
            {//delete btn clicked
                favoritesViewModel.deleteFavorite(it)
            }
        )

        favoritesViewModel = ViewModelProvider(this,factory).get(FavoritesViewModel::class.java)
        favoritesAdapter.submitList(favoritesViewModel.favoritesList.value)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)
            adapter = favoritesAdapter
        }

        favoritesViewModel.favoritesList.observe(this){
            Log.i(TAG, "onCreate: i can't believe it # favorites: ${it.size}")
            progressBar.visibility = View.GONE
            favoritesAdapter.submitList(it)
            //------------Old list is returned?
            //------------should go to another activity and get back to see the new res
            //------------or click on delete twice?!
        }
    }
    private fun initUi(){
        progressBar = findViewById(R.id.progressBar)
        recyclerView = findViewById(R.id.recyclerView)
    }
}