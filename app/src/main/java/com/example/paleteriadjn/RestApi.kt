package com.example.paleteriadjn

import retrofit2.Call
import retrofit2.http.*

interface RestApi {
    //@Headers("Content-Type: application/json")
    @POST("agregarproducto")
    fun addProducts(@Body productoData: ProductoInfo): Call<ProductoInfo>

    @DELETE("eliminarproducto/{id}")
    fun deleteProduct(@Path("id")id: Int): Call<String>
}