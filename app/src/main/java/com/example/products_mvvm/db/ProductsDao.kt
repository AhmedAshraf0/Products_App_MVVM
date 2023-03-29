package com.example.products_mvvm.db

import androidx.room.*
import com.example.products_mvvm.model.ProductModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {
    @Query("SELECT * FROM favorites")
    fun getAllProducts() : Flow<List<ProductModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product:ProductModel)

//    @Query("DELETE FROM favorites WHERE id = :productId")
//    suspend fun deleteProduct(productId:Int)
    @Delete
    suspend fun deleteProduct(product:ProductModel)
}