package com.example.paleteriadjn

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_producto.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductoActivity : AppCompatActivity() {

    private lateinit var database: AppDatabase
    private lateinit var producto: Producto
    private lateinit var productoLiveData: LiveData<Producto>
    private val EDIT_ACTIVITY = 49

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producto)

        val editarpro_crud : Button = findViewById(R.id.crudpro_btneditar)
        val elipro_crud : Button = findViewById(R.id.crudpro_btnelimianar)

        editarpro_crud.setOnClickListener(){
            val intent = Intent(this, NuevoProductoActivity::class.java)
            intent.putExtra("producto", producto)
            startActivityForResult(intent, EDIT_ACTIVITY)
            //Toast.makeText(baseContext, "Producto modificado correctamente", Toast.LENGTH_SHORT).show()
        }

        elipro_crud.setOnClickListener(){
            productoLiveData.removeObservers(this)

            CoroutineScope(Dispatchers.IO).launch {
                database.productos().delete(producto)
                //Toast.makeText(baseContext, "Producto eliminado correctamente", Toast.LENGTH_SHORT).show()
                ImageController.deleteImage(this@ProductoActivity, producto.idProducto.toLong())
                this@ProductoActivity.finish()
                  }

        }


        //val producto = intent.getSerializableExtra("producto") as Producto

        database = AppDatabase.getDatabase(this)

        val idProducto = intent.getIntExtra("id", 0)

        val imageUri = ImageController.getImageUri(this, idProducto.toLong())
        imagen.setImageURI(imageUri)

        productoLiveData = database.productos().get(idProducto)

        productoLiveData.observe(this, Observer {
            producto = it
            nombre_producto.text = producto.nombre
            precio_producto.text = "$${producto.precio}"
            descripcion_pro.text = producto.descripcion

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.producto_menu, menu)
        return super.onCreateOptionsMenu(menu)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.edit_item -> {
                val intent = Intent(this, NuevoProductoActivity::class.java)
                intent.putExtra("producto", producto)
                startActivityForResult(intent, EDIT_ACTIVITY)
            }

            R.id.delete_item -> {
                productoLiveData.removeObservers(this)

                CoroutineScope(Dispatchers.IO).launch {
                    database.productos().delete(producto)
                    ImageController.deleteImage(this@ProductoActivity, producto.idProducto.toLong())
                    this@ProductoActivity.finish()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when {
            requestCode == EDIT_ACTIVITY && resultCode == Activity.RESULT_OK -> {
                imagen.setImageURI(data!!.data)
            }
        }
    }



}