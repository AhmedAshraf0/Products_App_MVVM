package com.example.products_mvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.products_mvvm.all_products.view.ProductsActivity
import com.example.products_mvvm.favorites.view.FavoritesActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val stateFlow = MutableStateFlow("test")

        println("hiiiii")
        //subscribe then emit
        lifecycleScope.launchWhenResumed {
            //now it's lifecycle aware when it reach specific state when launchwhenX

            //but when using lifecycleScope.launch only it because of dispatcher
            stateFlow.collect{
                println("value: $it")
            }
        }
        stateFlow.value = "test val1"
        stateFlow.value = "test val2"
        stateFlow.value = "test val3"//save only latest value
    }
    fun goToProductsScreen(view: View){
        intent = Intent(this, ProductsActivity::class.java)
        startActivity(intent)
    }
    fun goToFavorites(view:View){
        intent = Intent(this, FavoritesActivity::class.java)
        startActivity(intent)
    }
    fun terminateApp(view:View){
        finishAffinity()
    }

    override fun onResume() {
        super.onResume()
        println("we are here")
    }
}