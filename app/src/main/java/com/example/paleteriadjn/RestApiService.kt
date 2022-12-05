package com.example.paleteriadjn

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestApiService {
    fun addProducto(productoData: ProductoInfo, onResult: (ProductoInfo?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.addProducts(productoData).enqueue(
            object : Callback<ProductoInfo>{
                override fun onFailure(call: Call<ProductoInfo>, t: Throwable) {
                    onResult(null)
                }

                override fun onResponse(
                    call: Call<ProductoInfo>,
                    response: Response<ProductoInfo>,
                ) {
                    val addedProduct= response.body()
                    onResult(addedProduct)
                }

            }
        )
    }
}