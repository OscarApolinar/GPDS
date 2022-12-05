package com.example.paleteriadjn

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ConsultarProducto : AppCompatActivity()
    //, NavigationView.OnNavigationItemSelectedListener
    {
    private lateinit var firebaseAuth: FirebaseAuth
    //private lateinit var drawer: DrawerLayout
    //private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultar_producto)
        firebaseAuth= Firebase.auth

        /*val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)

        toggle = ActionBarDrawerToggle(this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)*/
    }

    fun buscarproducto(view:View)
    {
        val idproducto = findViewById<EditText>(R.id.edit_id)
        val nombrepro = findViewById<TextView>(R.id.nombreproduct)
        val descripcionpro = findViewById<TextView>(R.id.DescripcionProduct)
        val preciopro = findViewById<TextView>(R.id.PrecioProduct)
        val stock = findViewById<TextView>(R.id.Stock)

        val url:String="http://paleteriadjn.online/public/api/producto/" + idproducto.text.toString()
        //val url:String="https://jsonplaceholder.typicode.com/posts/" + idproducto.text.toString()
        val rq:RequestQueue= Volley.newRequestQueue(this)
        val jor=JsonObjectRequest(Request.Method.GET,url,null, Response.Listener { response ->
            if(response.has("nombre")) {

                nombrepro.text = response.getString("nombre")
                descripcionpro.text = response.getString("descripcion")
                preciopro.text = response.getString("precio")
                stock.text = response.getString("stock")

            }else{
                Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_LONG).show()
            }

        },Response.ErrorListener { error ->
               // Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_LONG).show()
           nombrepro.text=error.message
        })
        rq.add(jor)
    }

    /*override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val i= Intent(this, ConsultarProducto::class.java)
        val crearpro= Intent(this, CrearProductos::class.java)
        val eliminarpro= Intent(this, EliminarProducto::class.java)
        val modificarpro= Intent(this, ModificarProducto::class.java)
        val consultartodospro= Intent(this, ConsultarTodosProductos::class.java)

        when (item.itemId){
            R.id.nav_item_one -> startActivity(i)
            R.id.nav_item_eleven -> startActivity(consultartodospro)
            R.id.nav_item_twelve -> startActivity(crearpro)
            R.id.nav_item_trece -> startActivity(modificarpro)
            R.id.nav_item_catorce -> startActivity(eliminarpro)


            R.id.nav_item_two -> Toast.makeText(this, "Proveedores", Toast.LENGTH_SHORT).show()
            R.id.nav_item_three -> Toast.makeText(this, "Productos", Toast.LENGTH_SHORT).show()
            R.id.nav_item_four -> Toast.makeText(this, "Nueva Venta", Toast.LENGTH_SHORT).show()

            R.id.nav_item_six ->   signOut()

        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged((newConfig))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private  fun signOut(){
        firebaseAuth.signOut()
        Toast.makeText(baseContext, "Sesión cerrada correctamente", Toast.LENGTH_SHORT).show()
        val i=Intent(this, MainActivity::class.java)
        startActivity(i)

    }*/




}