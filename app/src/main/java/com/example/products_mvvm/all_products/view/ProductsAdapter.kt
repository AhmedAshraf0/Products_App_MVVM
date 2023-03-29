package com.example.products_mvvm.all_products.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.products_mvvm.R
import com.example.products_mvvm.model.ProductModel
import com.google.android.material.card.MaterialCardView

class ProductsAdapter
    (private val context: Context,
     private val onClick:(ProductModel)->Unit,
     private val onFavBtnClicked:(ProductModel)->Unit)
    : ListAdapter<ProductModel, ProductsAdapter.ViewHolder>(MyDiffUtil()) {
    inner class ViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        var productImage : ImageView = itemView.findViewById(R.id.imageView)
        val productName : TextView = itemView.findViewById(R.id.productTitle)
        val productDesc : TextView = itemView.findViewById(R.id.productDesc)
        val productRating : RatingBar = itemView.findViewById(R.id.ratingBar)
        val productPrice : TextView = itemView.findViewById(R.id.price)
        val productCard : MaterialCardView = itemView.findViewById(R.id.productCard)
        val favBtn : Button = itemView.findViewById(R.id.button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.product_card,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentProduct = getItem(position)
        Glide.with(context).load(currentProduct.thumbnail).into(holder.productImage)

        holder.productName.text = currentProduct.title
        holder.productDesc.text = currentProduct.description
        holder.productPrice.text = "$${currentProduct.price}"
        holder.productRating.rating= currentProduct.rating.toFloat()
        holder.productCard.setOnClickListener{
            onClick(currentProduct)
        }
        holder.favBtn.setOnClickListener{
            onFavBtnClicked(currentProduct)
        }
    }

}