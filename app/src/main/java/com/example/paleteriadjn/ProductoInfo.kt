package com.example.paleteriadjn

import com.google.gson.annotations.SerializedName

data class ProductoInfo(
    @SerializedName("id") val productoId: Int?,
    @SerializedName("nombre") val productonombre: String?,
    @SerializedName("descripcion") val productodescripcion: String?,
    @SerializedName("precio") val productoprecio: Double?,
    @SerializedName("stock") val productostock: Int?,

)