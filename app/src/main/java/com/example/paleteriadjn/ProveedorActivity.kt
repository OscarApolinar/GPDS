package com.example.paleteriadjn

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_proveedor.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProveedorActivity : AppCompatActivity() {

        private lateinit var database: AppDatabaseProv
        private lateinit var proveedor: Proveedor
        private lateinit var proveedorLiveData: LiveData<Proveedor>
        private val EDIT_ACTIVITY = 49

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_proveedor)

            val editarproveedor : Button = findViewById(R.id.crudprov_btneditar)
            val borrarproveedor : Button = findViewById(R.id.crudprov_btneliminar)

            editarproveedor.setOnClickListener(){
                val intent = Intent(this, NuevoProveedorActivity::class.java)
                intent.putExtra("proveedor", proveedor)
                startActivityForResult(intent, EDIT_ACTIVITY)
            }

            borrarproveedor.setOnClickListener(){
                proveedorLiveData.removeObservers(this)

                CoroutineScope(Dispatchers.IO).launch {
                    database.proveedores().delete(proveedor)
                    ImageControllerProv.deleteImage(this@ProveedorActivity, proveedor.idProveedor.toLong())
                    this@ProveedorActivity.finish()
                }
            }




            //val producto = intent.getSerializableExtra("producto") as Producto

            database = AppDatabaseProv.getDatabase(this)

            val idProveedor = intent.getIntExtra("id", 0)

            val imageUri = ImageControllerProv.getImageUri(this, idProveedor.toLong())
            imagen.setImageURI(imageUri)

            proveedorLiveData = database.proveedores().get(idProveedor)

            proveedorLiveData.observe(this, Observer {
                proveedor = it
                nombre_prov.setText(proveedor.nombre)
                empresa_prov.setText(proveedor.empresa)
                direccion_prov.setText(proveedor.direccion)

            })
        }

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.proveedor_menu, menu)
            return super.onCreateOptionsMenu(menu)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when(item.itemId) {
                R.id.edit_item -> {
                    val intent = Intent(this, NuevoProveedorActivity::class.java)
                    intent.putExtra("proveedor", proveedor)
                    startActivityForResult(intent, EDIT_ACTIVITY)
                }

                R.id.delete_item -> {
                    proveedorLiveData.removeObservers(this)

                    CoroutineScope(Dispatchers.IO).launch {
                        database.proveedores().delete(proveedor)
                        ImageControllerProv.deleteImage(this@ProveedorActivity, proveedor.idProveedor.toLong())
                        this@ProveedorActivity.finish()
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