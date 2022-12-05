package com.example.paleteriadjn

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
//import android.widget.Toolbar
//import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class PantPrincipal : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pant_principal)
        firebaseAuth= Firebase.auth

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)

        toggle = ActionBarDrawerToggle(this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        return
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val i= Intent(this, ConsultarProducto::class.java)
        val crearpro= Intent(this, CrearProductos::class.java)
        val eliminarpro= Intent(this, EliminarProducto::class.java)
        val modificarpro= Intent(this, ModificarProducto::class.java)
        val consultartodospro= Intent(this, ConsultarTodosProductos::class.java)

        //productos
        val vistaProd= Intent(this, ListaProductosActivity::class.java)

        //proveedores
        val vistaprov = Intent(this, ListaProveedoresActivity::class.java)


        when (item.itemId){

            //api
            R.id.nav_item_one -> startActivity(i)
            R.id.nav_item_eleven -> startActivity(consultartodospro)
            R.id.nav_item_twelve -> startActivity(crearpro)
            R.id.nav_item_trece -> startActivity(modificarpro)
            R.id.nav_item_catorce -> startActivity(eliminarpro)

            // Crud Productos
            R.id.nav_item_three -> startActivity(vistaProd)

            //Crud de proveedores

            R.id.nav_item_dos -> startActivity(vistaprov)




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

    }
}