package com.example.paleteriadjn

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductosDao {
    @Query("Select * From productos")
    fun getAll(): LiveData<List<Producto>>

    @Query("Select * From productos Where idProducto = :id")
    fun get(id: Int): LiveData<Producto>

    @Insert
    fun insertAll(vararg productos: Producto): List<Long>

    @Update
    fun update(producto: Producto)

    @Delete
    fun delete(producto: Producto)
}