package com.example.paleteriadjn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_lista_productos.*


class ListaProductosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_productos)



        var listaProductos = emptyList<Producto>()

        val database = AppDatabase.getDatabase(this)

        database.productos().getAll().observe(this, Observer {
            listaProductos = it
            val adapter = ProductosAdapter(this, listaProductos)
            lista.adapter = adapter
        })


        val adapter = ProductosAdapter(this, listaProductos)
        lista.adapter = adapter

        lista.setOnItemClickListener{parent, view, position, id ->
            val intent = Intent(this, ProductoActivity::class.java)
            intent.putExtra("id", listaProductos[position].idProducto)
            startActivity(intent)
        }

        floatingActionButton.setOnClickListener{
            val intent = Intent(this, NuevoProductoActivity::class.java)
            startActivity(intent)
        }
    }
}