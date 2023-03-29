package com.example.products_mvvm.all_products.view

import androidx.recyclerview.widget.DiffUtil
import com.example.products_mvvm.model.ProductModel

class MyDiffUtil : DiffUtil.ItemCallback<ProductModel>() {
    override fun areItemsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
        return oldItem == newItem
    }
}