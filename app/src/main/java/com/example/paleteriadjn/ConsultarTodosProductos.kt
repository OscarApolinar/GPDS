package com.example.paleteriadjn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_consultar_todos_productos.*

class ConsultarTodosProductos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultar_todos_productos)
        var url= "http://paleteriadjn.online/public/api/productos"
        var list= ArrayList<ItemProduct>()

        var rq: RequestQueue=Volley.newRequestQueue(this)
        var jar= JsonArrayRequest(Request.Method.GET, url, null, Response.Listener { response ->
            for (x in 0..response.length()-1)
                list.add(ItemProduct(response.getJSONObject(x).getInt("id"),response.getJSONObject(x).getString("nombre"),
                    response.getJSONObject(x).getString("descripcion"), response.getJSONObject(x).getDouble("precio"),
                    response.getJSONObject(x).getInt("stock")))
            var adp= ItemProductAdapter(this, list)
            productos.layoutManager=LinearLayoutManager(this)
            productos.adapter=adp
        }, Response.ErrorListener { error ->
            Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()

        })
        rq.add(jar)
    }
}