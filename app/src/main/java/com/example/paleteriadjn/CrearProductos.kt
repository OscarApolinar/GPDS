package com.example.paleteriadjn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_crear_productos.*


class CrearProductos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_productos)

        crearproducto.setOnClickListener{
            val apiService = RestApiService()
            val productoInfo = ProductoInfo(
                productoId = null,
                productonombre = txt_nuevoNombre.text.toString(),
                productodescripcion = txt_nuevoDescripcion.text.toString(),
                productoprecio = txt_nuevoPrecio.text.toString().toDouble(),
                productostock = txt_nuevoStock.text.toString().toInt()

            )
            apiService.addProducto(productoInfo){
                if(it?.productoId != null){
                    Toast.makeText(this,"Producto agregado correctamente", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this,"Error al crear producto", Toast.LENGTH_LONG).show()
                }
            }
        }
    }


}