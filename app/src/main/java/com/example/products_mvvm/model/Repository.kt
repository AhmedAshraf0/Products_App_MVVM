package com.example.products_mvvm.model

import com.example.products_mvvm.db.ConcreteLocalSource
import com.example.products_mvvm.network.ConcreteRemoteSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class Repository private constructor(
    private val concreteLocalSource: ConcreteLocalSource,
    private val concreteRemoteSource: ConcreteRemoteSource
) : RepositoryInterface{
    companion object{
        private var INSTANCE : Repository? = null
        fun getInstance(concreteLocalSource: ConcreteLocalSource,
                        concreteRemoteSource: ConcreteRemoteSource) : Repository{
            return INSTANCE ?: synchronized(this){
                val instance = Repository(concreteLocalSource,concreteRemoteSource)
                INSTANCE = instance
                instance
            }

        }
    }

    override fun getOnlineProducts(): Flow<Response<ProductJson>> {
        return flow {
            emit(concreteRemoteSource.getProductsOverNetwork())
        }
    }

    override suspend fun getLocalProducts(): Flow<List<ProductModel>> {
        return concreteLocalSource.getFavorites()
    }

    override suspend fun insertProduct(product: ProductModel) {
        concreteLocalSource.insertFavProduct(product)
    }

    override suspend fun deleteProduct(product: ProductModel) {
        concreteLocalSource.deleteFavProduct(product)
    }

}