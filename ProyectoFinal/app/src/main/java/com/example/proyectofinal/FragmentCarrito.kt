package com.example.proyectofinal

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectofinal.adapter.AdaptadorCarrito
import com.example.proyectofinal.databinding.FragmentCarritoBinding
import com.example.proyectofinal.model.Producto
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FragmentCarrito: Fragment(), DataDownloadListener {


    private var _binding: FragmentCarritoBinding? = null

    //private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var userUID: String

    private lateinit var nombreProducto: String
    private lateinit var precioProducto: String
    private lateinit var listaProductos: ArrayList<Producto>
    private lateinit var adaptadorCarrito: AdaptadorCarrito

    private val binding get() = _binding!!


    override fun onAttach(context: Context) {
        super.onAttach(context)
        userUID = arguments?.getString("uid") ?: "no uid"

        //auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance("https://jbgutad2223-default-rtdb.firebaseio.com/")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCarritoBinding.inflate(inflater, container, false)
        listaProductos = ArrayList()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getElementosBBDD(this)


        binding.botonConfirmar.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage("¿Estás seguro de que quieres confirmar la compra?")

            builder.setPositiveButton("Sí") { dialog, which ->

                val databaseRef = database.reference.child("usuarios").child(userUID).child("productos")
                databaseRef.removeValue()
                    .addOnSuccessListener {
                        // Deletion successful
                        Snackbar.make(binding.root, "Compra completada", Snackbar.LENGTH_SHORT).show()
                        getElementosBBDD(this)
                        findNavController().navigate(R.id.action_Third2Fragment_to_First2Fragment2)
                    }
                    .addOnFailureListener { error ->
                        // Error occurred while deleting the data
                        Log.d("TAG", "Error deleting data: $error")
                    }

            }

            builder.setNegativeButton("No") { dialog, which ->
                dialog.dismiss()
            }

            val dialog = builder.create()
            dialog.show()
        }

        binding.botonVolver.setOnClickListener {
            findNavController().navigate(R.id.action_Third2Fragment_to_First2Fragment2)
        }


    }
    fun getElementosBBDD(listener: DataDownloadListener){
        val databaseRef = database.reference.child("usuarios")
            .child(userUID)
            .child("productos")

            databaseRef.get().addOnSuccessListener {
                if(it.exists()){
                    for (producto in it.children){
                        nombreProducto = producto.child("nombre").value.toString()
                        precioProducto = producto.child("precio").value.toString()
                        listaProductos.add(Producto(0, nombreProducto, precioProducto, "", 0.0, 0.0, 0, "", "", ""))
                    }
                }
                listener.onDataDownloaded(listaProductos)
        }
    }




    override fun onDataDownloaded(listaProductos: List<Producto>){
        adaptadorCarrito = AdaptadorCarrito(listaProductos as ArrayList<Producto>, requireContext())

        binding.recyclerViewCarrito.adapter = adaptadorCarrito
        binding.recyclerViewCarrito.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

    }

}
interface DataDownloadListener {
    fun onDataDownloaded(listaProductos: List<Producto>)
}