package com.example.products_mvvm.product_details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.products_mvvm.R
import com.example.products_mvvm.model.ProductModel

class ProductDetailsActivity : AppCompatActivity() {
    lateinit var headerImg : ImageView
    lateinit var headerTitle : TextView
    lateinit var headerDesc : TextView
    lateinit var product:ProductModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_details)
        initUi()
        product = intent.extras?.getSerializable("product") as ProductModel
        Glide.with(this).load(product.thumbnail).into(headerImg)
        headerTitle.text = product.title
        headerDesc.text = product.description
    }
    private fun initUi(){
        headerImg = findViewById(R.id.header)
        headerTitle = findViewById(R.id.headerTitle)
        headerDesc = findViewById(R.id.headerDesc)
    }
}