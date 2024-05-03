package com.example.proyectofinal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.proyectofinal.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class FragmentLogin : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private lateinit var auth: FirebaseAuth

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.botonLogin.setOnClickListener {
            if (binding.editUsuario.text.isEmpty() || binding.editPassword.text.isEmpty()){
                Snackbar.make(it, "Debe rellenar todos los campos", Snackbar.LENGTH_SHORT).show()
            } else {
                auth.signInWithEmailAndPassword(
                    binding.editUsuario.text.toString(),
                    binding.editPassword.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val bundle = Bundle();
                        bundle.putString("uid", auth.uid)
                        val intent: Intent = Intent(context, SecondActivity::class.java)
                        intent.putExtras(bundle)
                        startActivity(intent)

                    } else {
                        Snackbar.make(binding.root, "Fallo al Logear", Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}