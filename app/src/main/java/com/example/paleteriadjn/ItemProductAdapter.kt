package com.example.paleteriadjn

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class ItemProductAdapter(var context: Context, var list: ArrayList<ItemProduct>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var v: View=LayoutInflater.from(context).inflate(R.layout.item_row, parent, false)
        return ItemHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemHolder).bind(list[position].nombre,list[position].descripcion, list[position].precio, list[position].stock)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    class ItemHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(n: String, d: String, p: Double, s: Int){
            itemView.nombre.text= n
            itemView.descripcion.text= d
            itemView.precio.text= p.toString()
            itemView.stock.text= s.toString()


        }
    }
}