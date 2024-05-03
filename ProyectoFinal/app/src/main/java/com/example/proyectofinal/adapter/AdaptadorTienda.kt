package com.example.proyectofinal.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.proyectofinal.R
import com.example.proyectofinal.databinding.ActivitySecondBinding
import com.example.proyectofinal.model.Producto
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.google.firebase.database.FirebaseDatabase


class AdaptadorTienda   (var lista: ArrayList<Producto>, var context: Context, private var navController: NavController, private var uid: String): RecyclerView.Adapter<AdaptadorTienda.MyHolder>() {
    class MyHolder(itemView: View): ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imagen_album)
        val productoTextView: TextView = itemView.findViewById(R.id.nombre_producto)
        val precioTextView: TextView = itemView.findViewById(R.id.precio_producto)
        val botonComprar: TextView = itemView.findViewById(R.id.boton_compra)
        val botonDetalle: TextView = itemView.findViewById(R.id.boton_detalle)

    }

    private lateinit var database: FirebaseDatabase



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val vista: View = LayoutInflater.from(context).inflate(R.layout.item_compra, parent, false)
        database = FirebaseDatabase.getInstance("https://jbgutad2223-default-rtdb.firebaseio.com/")
        return MyHolder(vista)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val elementoTemp = lista[position]
        holder.productoTextView.text = elementoTemp.nombre
        holder.precioTextView.text = elementoTemp.precio + "€"
        holder.botonComprar.setOnClickListener {

            val databaseRef = database.reference.child("usuarios")
                .child(uid)
                .child("productos")
                .child(holder.productoTextView.text.toString())

            val productoData = mapOf(
                "nombre" to elementoTemp.nombre,
                "precio" to elementoTemp.precio,
                "descripcion" to elementoTemp.descripcion,
                "marca" to elementoTemp.marca,
                "categoria" to elementoTemp.categoria
            )

            databaseRef.setValue(productoData)

            val bundle: Bundle = Bundle()
            bundle.putString("nombre", elementoTemp.nombre)
            bundle.putString("precio", elementoTemp.precio)
            bundle.putString("imagen", elementoTemp.imagen)
            bundle.putString("descripcion", elementoTemp.descripcion)
            bundle.putInt("id", elementoTemp.id)
            bundle.putString("categoria", elementoTemp.categoria)
            bundle.putInt("stock", elementoTemp.stock)
            bundle.putDouble("descuento", elementoTemp.descuento)
            bundle.putDouble("rating", elementoTemp.rating)
            bundle.putString("marca", elementoTemp.marca)
            bundle.putString("uid", uid)
            navController.navigate(R.id.action_First2Fragment_to_Third2Fragment, bundle)

        }
        holder.botonDetalle.setOnClickListener {
            val bundle: Bundle = Bundle()
            bundle.putString("nombre", elementoTemp.nombre)
            bundle.putString("precio", elementoTemp.precio)
            bundle.putString("imagen", elementoTemp.imagen)
            bundle.putString("descripcion", elementoTemp.descripcion)
            bundle.putInt("id", elementoTemp.id)
            bundle.putString("categoria", elementoTemp.categoria)
            bundle.putInt("stock", elementoTemp.stock)
            bundle.putDouble("descuento", elementoTemp.descuento)
            bundle.putDouble("rating", elementoTemp.rating)
            bundle.putString("marca", elementoTemp.marca)
            navController.navigate(R.id.action_First2Fragment_to_Second2Fragment, bundle)
        }
        Glide.with(context).load(elementoTemp.imagen).placeholder(R.drawable.ic_launcher_background).into(holder.imageView)

    }

}

