package com.example.paleteriadjn

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.item_proveedor.view.*

class ProveedoresAdapter(private val mContext: Context, private val listaProveedores: List<Proveedor>) :
    ArrayAdapter<Proveedor>(mContext, 0, listaProveedores) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.item_proveedor, parent,false)

        val proveedor = listaProveedores[position]

        layout.nombre.text = proveedor.nombre
        layout.empresa.text = proveedor.empresa
        val imageUri = ImageControllerProv.getImageUri(mContext, proveedor.idProveedor.toLong())
        layout.imageView.setImageURI(imageUri)


        return layout
    }
}