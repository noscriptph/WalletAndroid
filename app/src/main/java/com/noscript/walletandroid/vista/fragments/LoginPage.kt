package com.noscript.walletandroid.vista.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.noscript.walletandroid.R
import com.noscript.walletandroid.vistaModelo.Usuario
import com.noscript.walletandroid.vistaModelo.UsuarioViewModel
import com.noscript.walletandroid.databinding.FragmentLoginPageBinding

class LoginPage : Fragment() {

    private var _binding: FragmentLoginPageBinding? = null
    private val binding get() = _binding!!

    private lateinit var usuarioViewModel: UsuarioViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginPageBinding.inflate(inflater, container, false)
        val view = binding.root

        usuarioViewModel = ViewModelProvider(requireActivity()).get(UsuarioViewModel::class.java)

        binding.imageViewLogin.setOnClickListener {
            val email = binding.editTextEmailAddress.text.toString()
            val password = binding.editTextPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val usuarioGuardado = Usuario.obtenerUsuario(requireContext())

            if (email == usuarioGuardado.email && password == usuarioGuardado.contrasena) {
                usuarioGuardado.sesionIniciada = true
                Usuario.guardarUsuario(requireContext(), usuarioGuardado)
                usuarioViewModel.usuario.value = usuarioGuardado
                Toast.makeText(requireContext(), "Sesión iniciada correctamente", Toast.LENGTH_SHORT).show()
                cargarFragmento(HomePage())
            } else {
                Toast.makeText(requireContext(), "Los datos son incorrectos, inténtalo nuevamente", Toast.LENGTH_SHORT).show()
            }
        }

        binding.textView10.setOnClickListener {
            findNavController().navigate(R.id.action_loginPage_to_singupPage)
        }

        binding.editTextPassword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.imageViewLogin.performClick()
                return@setOnEditorActionListener true
            }
            false
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun cargarFragmento(fragmento: Fragment) {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container_view, fragmento)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}
