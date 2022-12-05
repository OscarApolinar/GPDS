package com.example.paleteriadjn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_eliminar_producto.*

class EliminarProducto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eliminar_producto)

        btn_eliminarproducto.setOnClickListener{
            val ur:String="http://paleteriadjn.online/public/api/eliminarproducto/" + txt_Eliminarproductoid.text.toString()
            val rq: RequestQueue = Volley.newRequestQueue(this)
            val sr= StringRequest(Request.Method.DELETE, ur, Response.Listener { respose ->
                Toast.makeText(this, "Producto eliminado correctamente", Toast.LENGTH_LONG).show()
            }
            , Response.ErrorListener { error ->
                    Toast.makeText(this, "El producto no se pudo eliminar", Toast.LENGTH_LONG).show()
                })
            rq.add(sr)
        }
    }
}