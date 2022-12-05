package com.example.paleteriadjn

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_nuevo_producto.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NuevoProductoActivity : AppCompatActivity() {

    private val SELECT_ACTIVITY = 50
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_producto)
        
        var idProducto: Int? = null

        if (intent.hasExtra("producto")) {
            val producto = intent.extras?.getSerializable("producto") as Producto

            nombre_pro.setText(producto.nombre)
            precio_pro.setText(producto.precio.toString())
            desc_pro.setText(producto.descripcion)
            idProducto = producto.idProducto

            val imageUri = ImageController.getImageUri(this, idProducto.toLong())
            imageSelect_pro.setImageURI(imageUri)

        }

        val database = AppDatabase.getDatabase(this)

        save_btn.setOnClickListener {
            val nombre = nombre_pro.text.toString()
            val precio = precio_pro.text.toString().toDouble()
            val descripcion = desc_pro.text.toString()

            val producto = Producto(nombre, precio, descripcion, R.drawable.galaxyfold)

            if (idProducto != null) {
                CoroutineScope(Dispatchers.IO).launch {
                    producto.idProducto = idProducto

                    database.productos().update(producto)
                    imageUri?.let {
                        val intent = Intent()
                        intent.data = it
                        setResult(Activity. RESULT_OK, intent)
                        ImageController.saveImage(this@NuevoProductoActivity, idProducto.toLong(), it)
                    }

                    this@NuevoProductoActivity.finish()
                }
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    val id = database.productos().insertAll(producto)[0]
                    this@NuevoProductoActivity.finish()
                    imageUri?.let {
                        ImageController.saveImage(this@NuevoProductoActivity, id, it)
                    }
                }
            }
        }

        imageSelect_pro.setOnClickListener {
            ImageController.selectPhotoFromGallery(this, SELECT_ACTIVITY)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when {
            requestCode == SELECT_ACTIVITY && resultCode == Activity.RESULT_OK -> {
                imageUri = data!!.data

                imageSelect_pro.setImageURI(imageUri)
            }
        }
    }
}