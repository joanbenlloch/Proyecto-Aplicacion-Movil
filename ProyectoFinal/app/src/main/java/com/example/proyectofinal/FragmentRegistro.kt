package com.example.proyectofinal

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.proyectofinal.databinding.FragmentRegistroBinding
import com.example.proyectofinal.model.Usuario
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FragmentRegistro : Fragment() {

    private var _binding: FragmentRegistroBinding? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase


    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance("https://jbgutad2223-default-rtdb.firebaseio.com/")
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegistroBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonRegistrarse.setOnClickListener {
            if (binding.correo.text.isEmpty() || binding.password.text.isEmpty()
                || binding.nombre.text.isEmpty() || binding.edad.text.isEmpty() || binding.direccion.text.isEmpty()){

                Snackbar.make(it, "Debe rellenar todos los campos", Snackbar.LENGTH_SHORT).show()

            } else {
                auth.createUserWithEmailAndPassword(
                    binding.correo.text.toString(),
                    binding.password.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val user = auth.currentUser
                        val userId = user?.uid ?: "no uid"

                        val name = binding.nombre.text.toString()
                        val correo = binding.correo.text.toString()
                        val edad = binding.edad.text.toString().toInt()
                        val direccion = binding.direccion.text.toString()

                        val databaseRef = database.reference.child("usuarios")
                            .child(userId)

                        databaseRef.setValue(
                            Usuario(name, correo, edad, direccion)
                        ).addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Snackbar.make(
                                    binding.root,
                                    "Registro completo",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                            } else {
                                Snackbar.make(
                                    binding.root,
                                    "Fallo los datos del registro detectado",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }
                        }

                    } else {
                        Snackbar.make(
                            binding.root,
                            "Fallo en el registro detectado",
                            Snackbar.LENGTH_SHORT
                        ).show()

                    }
                }
            }

        }

        binding.buttonLogin.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}