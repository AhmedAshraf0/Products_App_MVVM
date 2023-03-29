package com.example.products_mvvm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.products_mvvm.model.ProductModel

@Database(entities = arrayOf(ProductModel::class), version = 1)
abstract class ProductsDB : RoomDatabase() {
    abstract fun getProductDao() : ProductsDao
    companion object{
        private var INSTANCE : ProductsDB? = null
        fun getInstance(context:Context) : ProductsDB{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductsDB::class.java,
                    "products_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}