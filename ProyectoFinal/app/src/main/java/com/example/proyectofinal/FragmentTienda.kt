package com.example.proyectofinal

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.adapter.AdaptadorTienda
import com.example.proyectofinal.databinding.ActivitySecondBinding
import com.example.proyectofinal.databinding.FragmentTiendaBinding
import com.example.proyectofinal.model.Producto
import com.google.android.gms.common.api.Response
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.InputStreamReader
import java.net.URL

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FragmentTienda : Fragment() {

    private var _binding: FragmentTiendaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    private lateinit var listaProductos: ArrayList<Producto>

    private lateinit var adaptadorTienda: AdaptadorTienda

    private lateinit var userUID: String


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentTiendaBinding.inflate(inflater, container, false)
        val view = binding.root
        userUID = activity?.intent?.getStringExtra("uid").toString()
        obtenerProductos()

        adaptadorTienda = AdaptadorTienda(listaProductos, requireContext(), findNavController(), userUID)
        binding.recyclerView1.adapter = adaptadorTienda
        binding.recyclerView1.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        obtenerProductos()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView1.adapter = null
    }

    private fun obtenerProductos() {

            listaProductos = ArrayList()

            listaProductos.add(Producto(1, "iPhone 9","549","An apple mobile which is nothing like apple", 12.96,4.69, 94, "Apple","smartphone", "https://i.dummyjson.com/data/products/1/thumbnail.jpg"))
            listaProductos.add(Producto(2, "iPhone X","899","SIM-Free, Model A19211 6.5-inch Super Retina HD display with OLED technology A12 Bionic chip with ...", 17.94,4.44, 34, "Apple","smartphone", "https://i.dummyjson.com/data/products/2/thumbnail.jpg"))
            listaProductos.add(Producto(3, "Samsung Universa 9","1249","Samsung's new variant which goes beyond Galaxy to the Universe", 15.94,4.44, 34, "Samsung","smartphone", "https://i.dummyjson.com/data/products/3/thumbnail.jpg"))
            listaProductos.add(Producto(4, "OPPOF19","280","OPPO F19 is officially announced on April 2021.", 17.91,4.3, 123, "OPPO","smartphone", "https://i.dummyjson.com/data/products/4/thumbnail.jpg"))
            listaProductos.add(Producto(5, "Huawei P30","499","Huawei’s re-badged P30 Pro New Edition was officially unveiled yesterday in Germany and now the device has made its way to the UK.", 10.58,4.09, 32, "Huawei","smartphone", "https://i.dummyjson.com/data/products/5/thumbnail.jpg"))
            listaProductos.add(Producto(6, "MacBook Pro","1749","MacBook Pro 2021 with mini-LED display may launch between September, November", 11.02,4.57, 83, "Apple","laptops", "https://i.dummyjson.com/data/products/6/thumbnail.png"))
            listaProductos.add(Producto(7, "Samsung Galaxy Book","1499","Samsung Galaxy Book S (2020) Laptop With Intel Lakefield Chip, 8GB of RAM Launched", 4.15,4.25, 50, "Samsung","laptops", "https://i.dummyjson.com/data/products/7/thumbnail.jpg"))
            listaProductos.add(Producto(8, "Microsoft Surface Laptop 4","1499","Style and speed. Stand out on HD video calls backed by Studio Mics. Capture ideas on the vibrant touchscreen.", 10.23,4.43, 68, "Microsoft Surface","laptops", "https://i.dummyjson.com/data/products/8/thumbnail.jpg"))
            listaProductos.add(Producto(9, "Infinix INBOOK","1099","Infinix Inbook X1 Ci3 10th 8GB 256GB 14 Win10 Grey – 1 Year Warranty", 11.83,4.54, 96, "Infinix","laptops", "https://i.dummyjson.com/data/products/9/thumbnail.jpg"))
            listaProductos.add(Producto(10, "HP Pavilion 15-DK1056WM","1099","HP Pavilion 15-DK1056WM Gaming Laptop 10th Gen Core i5, 8GB, 256GB SSD, GTX 1650 4GB, Windows 10", 6.18,4.43, 89, "HP Pavilion","laptops", "https://i.dummyjson.com/data/products/10/thumbnail.jpeg"))
            listaProductos.add(Producto(11, "perfume Oil","13","Mega Discount, Impression of Acqua Di Gio by GiorgioArmani concentrated attar perfume Oil", 8.4,4.26, 65, "Impression of Acqua Di Gio","fragrances", "https://i.dummyjson.com/data/products/11/thumbnail.jpg"))
            listaProductos.add(Producto(12, "Brown Perfume","13","Royal_Mirage Sport Brown Perfume for Men & Women - 120ml", 8.4,4.26, 65, "Royal_Mirage","fragrances", "https://i.dummyjson.com/data/products/12/thumbnail.jpg"))



    }
}