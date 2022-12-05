package com.example.paleteriadjn

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "proveedores")
class Proveedor (
    val nombre: String,
    val empresa: String,
    val direccion: String,
    val imagenprov: Int,
    @PrimaryKey(autoGenerate = true)
    var idProveedor: Int = 0
) : Serializable