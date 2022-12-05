package com.example.paleteriadjn

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProveedoresDao {
    @Query("Select * From proveedores")
    fun getAll(): LiveData<List<Proveedor>>

    @Query("Select * From proveedores Where idProveedor = :id")
    fun get(id: Int): LiveData<Proveedor>

    @Insert
    fun insertAll(vararg proveedor: Proveedor): List<Long>

    @Update
    fun update(proveedor: Proveedor)

    @Delete
    fun delete(proveedor: Proveedor)
}