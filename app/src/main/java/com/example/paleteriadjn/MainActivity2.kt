package com.example.paleteriadjn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity2 : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var AuthStateListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }

    override fun onBackPressed() {
        return
    }
   /* override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu,menu)
        return true
    }*/

    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId)
        {
            R.id.menu_Bucar -> {
                getProductbyid()

            }
            R.id.menu_salir ->{
                signOut()
            }

            R.id.menu_nuevoproducto ->{
                newProduct()
            }

            R.id.menu_modificarproducto ->{
                putProduct()
            }

            R.id.menu_eliminarproducto ->{
                deleteProduct()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private  fun signOut(){
        firebaseAuth.signOut()
        Toast.makeText(baseContext, "Sesión cerrada correctamente", Toast.LENGTH_SHORT).show()
        val i= Intent(this, MainActivity::class.java)
        startActivity(i)

    }

    private  fun newProduct(){
        val i= Intent(this, CrearProductos::class.java)
        startActivity(i)

    }
    private  fun getProductbyid(){
        val i= Intent(this, ConsultarProducto::class.java)
        startActivity(i)

    }

    private  fun putProduct(){
        val i= Intent(this, ModificarProducto::class.java)
        startActivity(i)

    }
    private  fun deleteProduct(){
        val i= Intent(this, EliminarProducto::class.java)
        startActivity(i)
    }*/

}