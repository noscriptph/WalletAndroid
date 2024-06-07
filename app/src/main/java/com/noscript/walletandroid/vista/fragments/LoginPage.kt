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

/**
 * Fragmento para la página de inicio de sesión.
 */
class LoginPage : Fragment() {
    // Variable para el binding de la vista
    private var _binding: FragmentLoginPageBinding? = null
    private val binding get() = _binding!!

    // ViewModel para el usuario
    private lateinit var usuarioViewModel: UsuarioViewModel

    /**
     * Método llamado para crear la vista del fragmento.
     * @param inflater El inflador de la vista.
     * @param container El contenedor de la vista.
     * @param savedInstanceState El estado previamente guardado de la instancia.
     * @return La vista inflada.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflar el layout del fragmento y obtener el binding
        _binding = FragmentLoginPageBinding.inflate(inflater, container, false)
        val view = binding.root

        // Inicializar el ViewModel del usuario
        usuarioViewModel = ViewModelProvider(requireActivity()).get(UsuarioViewModel::class.java)

        // Configurar el OnClickListener para el botón de inicio de sesión
        binding.imageViewLogin.setOnClickListener {
            val email = binding.editTextEmailAddress.text.toString()
            val password = binding.editTextPassword.text.toString()

            // Validar que los campos no estén vacíos
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    requireContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            // Obtener el usuario guardado y validar las credenciales
            val usuarioGuardado = Usuario.obtenerUsuario(requireContext())
            if (email == usuarioGuardado.email && password == usuarioGuardado.contrasena) {
                usuarioGuardado.sesionIniciada = true
                Usuario.guardarUsuario(requireContext(), usuarioGuardado)
                usuarioViewModel.usuario.value = usuarioGuardado
                findNavController().navigate(R.id.action_loginPage_to_homePage)
                Toast.makeText(
                    requireContext(), "Sesión iniciada correctamente", Toast.LENGTH_SHORT
                ).show()
                // Cargar el fragmento de HomePage
                //cargarFragmento(HomePage())
            } else {
                Toast.makeText(
                    requireContext(),
                    "Los datos son incorrectos, inténtalo nuevamente",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // Configurar el OnClickListener para el texto de registro
        binding.textView10.setOnClickListener {
            findNavController().navigate(R.id.action_loginPage_to_singupPage)
        }

        // Configurar el OnEditorActionListener para el campo de contraseña
        binding.editTextPassword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.imageViewLogin.performClick()
                return@setOnEditorActionListener true
            }
            false
        }

        return view
    }

    /**
     * Método llamado cuando la vista del fragmento es destruida.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    /*Se comento este codigo ya que estaba demas y generaba que algunos fragment no se cargaran con
    el findnavcontroller, Javier Alcantara me ayudo en esto
     */
       /**
        * Método para cargar un nuevo fragmento.
        * @param fragmento El fragmento a cargar.
        */


           private fun cargarFragmento(fragmento: Fragment) {
               val fragmentManager = requireActivity().supportFragmentManager
               val fragmentTransaction = fragmentManager.beginTransaction()
               fragmentTransaction.replace(R.id.fragment_container_view, fragmento)
               fragmentTransaction.addToBackStack(null)
               fragmentTransaction.commit()
           } */
}
