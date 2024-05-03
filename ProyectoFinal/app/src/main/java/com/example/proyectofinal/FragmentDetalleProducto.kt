package com.example.proyectofinal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.proyectofinal.databinding.FragmentDetalleBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class FragmentDetalleProducto : Fragment() {

    private var _binding: FragmentDetalleBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var nombre: String? = null
    var precio: String? = null
    var imagen: String? = null
    var descripcion: String? = null
    var id: Int? = null
    var categoria: String? = null
    var stock: Int? = null
    var descuento: Double? = null
    var rating: Double? = null
    var marca: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetalleBinding.inflate(inflater, container, false)

        nombre = arguments?.getString("nombre")
        precio = arguments?.getString("precio")
        imagen = arguments?.getString("imagen")
        descripcion = arguments?.getString("descripcion")
        id = arguments?.getInt("id")
        categoria = arguments?.getString("categoria")
        stock = arguments?.getInt("stock")
        descuento = arguments?.getDouble("descuento")
        rating = arguments?.getDouble("rating")
        marca = arguments?.getString("marca")

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nombreProducto.text = nombre
        binding.precioProducto.text = "Precio: " + precio + "€"
        Glide.with(this).load(imagen).into(binding.imagenProducto)
        binding.descripcionProducto.text = descripcion
        binding.categoriaProducto.text = categoria
        binding.stockProducto.text = "Stock: " + stock.toString()
        binding.descuentoProducto.text = "Descuento: " + descuento.toString()
        binding.ratingProducto.text = "Rating: " + rating.toString()
        binding.marcaProducto.text = marca


        binding.botonVolver.setOnClickListener {
            findNavController().navigate(R.id.action_Second2Fragment_to_First2Fragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}