package com.example.proyectofinal.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.R
import com.example.proyectofinal.model.Producto

class AdaptadorCarrito (var lista: ArrayList<Producto>, var context: Context): RecyclerView.Adapter<AdaptadorCarrito.MyHolder>() {

    class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val productoCarritoTextView: TextView = itemView.findViewById(R.id.producto_carrito)
        val precioCarritoTextView: TextView = itemView.findViewById(R.id.precio_carrito)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val vista: View = LayoutInflater.from(context).inflate(R.layout.item_carrito, parent, false)
        return AdaptadorCarrito.MyHolder(vista)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val elementoTemp = lista[position]
        holder.productoCarritoTextView.text = elementoTemp.nombre
        holder.precioCarritoTextView.text = elementoTemp.precio + " €"
    }
}