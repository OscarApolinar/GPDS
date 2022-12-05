package com.example.paleteriadjn

import android.telecom.Call
import com.android.volley.Response
import com.example.paleteriadjn.Model.Posts
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface APIService {

    /*@POST
    fun getEVERYTHING(@Body crearProdto: CrearProdto): Call<>

    @FormUrlEncoded
    @POST("posts")
    suspend fun pushPost(
        @Field("nombre")
        @Field("descripcion"),
        @Field("precio"),
        @Field("stock")
    ): Response<Posts>*/

}

//data class CrearProdto(val nombre:String, val descripcion:String, val precio:String, val stock:String )