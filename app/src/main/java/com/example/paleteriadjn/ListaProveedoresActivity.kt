package com.example.paleteriadjn
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_lista_proveedores.*


class ListaProveedoresActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_proveedores)


        var listaProveedores = emptyList<Proveedor>()

        val database = AppDatabaseProv.getDatabase(this)

        database.proveedores().getAll().observe(this, Observer {
            listaProveedores = it
            val adapter = ProveedoresAdapter(this, listaProveedores)
            lista.adapter = adapter
        })


        val adapter = ProveedoresAdapter(this, listaProveedores)
        lista.adapter = adapter

        lista.setOnItemClickListener{parent, view, position, id ->
            val intent = Intent(this, ProveedorActivity::class.java)
            intent.putExtra("id", listaProveedores[position].idProveedor)
            startActivity(intent)
        }

        floatingActionButton.setOnClickListener{
            val intent = Intent(this, NuevoProveedorActivity::class.java)
            startActivity(intent)
        }
    }
}
