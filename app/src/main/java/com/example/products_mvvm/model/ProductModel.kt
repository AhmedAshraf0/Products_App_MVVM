package com.example.products_mvvm.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity("favorites", primaryKeys = ["id"])
data class ProductModel(@NonNull var id:Int=0, @ColumnInfo var price:Int=0, @ColumnInfo var discountPercentage:Float=0f, @ColumnInfo var rating:Float=0f, @ColumnInfo var stock:Float=0f
                        , @ColumnInfo var title:String="", @ColumnInfo var description:String="", @ColumnInfo  var  thumbnail:String="", @ColumnInfo var  brand:String="", @ColumnInfo var category:String=""
                        , @Ignore var images:List<String> = listOf()): Serializable

data class ProductJson(val products : List<ProductModel>, val total : Int,
                       val skip : Int, val limit : Int)