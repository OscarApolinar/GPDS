package com.example.paleteriadjn

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_nuevo_proveedor.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NuevoProveedorActivity : AppCompatActivity() {

    private val SELECT_ACTIVITY = 50
    private var imageUri: Uri? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_nuevo_proveedor)

            var idProveedor: Int? = null

            if (intent.hasExtra("proveedor")) {
                val proveedor = intent.extras?.getSerializable("proveedor") as Proveedor

                nombreprov.setText(proveedor.nombre)
                empresaprov.setText(proveedor.empresa)
                direccionprove.setText(proveedor.direccion)
                idProveedor = proveedor.idProveedor

                val imageUri = ImageControllerProv.getImageUri(this, idProveedor.toLong())
                imageSelect_pro.setImageURI(imageUri)

            }

            val database = AppDatabaseProv.getDatabase(this)

            save_btn.setOnClickListener {
                val nombreprov = nombreprov.text.toString()
                val empresaprov = empresaprov.text.toString()
                val direccionprov = direccionprove.text.toString()

                val proveedor = Proveedor(nombreprov, empresaprov, direccionprov, R.drawable.galaxyfold)

                if (idProveedor != null) {
                    CoroutineScope(Dispatchers.IO).launch {
                        proveedor.idProveedor = idProveedor

                        database.proveedores().update(proveedor)
                        imageUri?.let {
                            val intent = Intent()
                            intent.data = it
                            setResult(Activity. RESULT_OK, intent)
                            ImageControllerProv.saveImage(this@NuevoProveedorActivity, idProveedor.toLong(), it)
                        }

                        this@NuevoProveedorActivity.finish()
                    }
                } else {
                    CoroutineScope(Dispatchers.IO).launch {
                        val id = database.proveedores().insertAll(proveedor)[0]
                        this@NuevoProveedorActivity.finish()
                        imageUri?.let {
                            ImageControllerProv.saveImage(this@NuevoProveedorActivity, id, it)
                        }
                    }
                }
            }

            imageSelect_pro.setOnClickListener {
                ImageControllerProv.selectPhotoFromGallery(this, SELECT_ACTIVITY)
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