package com.example.paleteriadjn

class ItemProduct {
    var id: Int = 0
    var nombre : String = ""
    var descripcion : String = ""
    var precio : Double = 0.0
    var stock : Int =0

    constructor(id: Int, nombre: String, descripcion: String, precio: Double, stock: Int){
        this.id = id
        this.nombre = nombre
        this.descripcion = descripcion
        this.precio = precio
        this.stock = stock
    }

}