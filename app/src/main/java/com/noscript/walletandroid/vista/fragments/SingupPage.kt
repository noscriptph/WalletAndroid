package com.noscript.walletandroid.vista.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.noscript.walletandroid.R
import com.noscript.walletandroid.databinding.FragmentSingupPageBinding
import com.noscript.walletandroid.vistaModelo.UsuarioViewModel

/**
 * Fragmento para la página de registro de usuario.
 */
class SingupPage : Fragment() {

    private var _binding: FragmentSingupPageBinding? = null
    private val binding get() = _binding!!

    private val usuarioViewModel: UsuarioViewModel by activityViewModels()

    /**
     * Crea y devuelve la vista jerárquica asociada con el fragmento.
     *
     * @param inflater Objeto LayoutInflater que puede usarse para inflar cualquier vista en el fragmento.
     * @param container Si no es nulo, es la vista primaria en la que se inflará el fragmento.
     * @param savedInstanceState Si no es nulo, este fragmento se está reconstituyendo de un estado anterior.
     * @return La vista para el fragmento, o nula.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSingupPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Llamado cuando la vista jerárquica del fragmento ha sido creada.
     *
     * @param view La vista devuelta por onCreateView(LayoutInflater, ViewGroup, Bundle).
     * @param savedInstanceState Si no es nulo, este fragmento se está reconstituyendo de un estado anterior.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configura el listener para el botón de registro, se debe usar el id de los cuadros de texto
        binding.imageView2.setOnClickListener {
            val nombre = binding.editTextNombre.text.toString()
            val apellido = binding.editTextApellido.text.toString()
            val email = binding.editTextEmailAddress.text.toString()
            val contrasena = binding.editTextPassword.text.toString()
            val reingresarContrasena = binding.editTextReingresarPassword.text.toString()

            if (contrasena.length < 10) {
                Toast.makeText(
                    requireContext(),
                    "La contraseña debe tener al menos 10 caracteres.",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (contrasena != reingresarContrasena) {
                Toast.makeText(
                    requireContext(),
                    "Las contraseñas no coinciden.",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (nombre.isBlank() || apellido.isBlank() || email.isBlank() || contrasena.isBlank()) {
                Toast.makeText(
                    requireContext(),
                    "Por favor, rellena todos los campos.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val usuarioActualizado = usuarioViewModel.usuario.value?.copy(
                    nombre = nombre,
                    apellido = apellido,
                    email = email,
                    contrasena = contrasena,
                    sesionIniciada = true
                )
                usuarioActualizado?.let {
                    usuarioViewModel.actualizarUsuario(it)
                }
                findNavController().navigate(R.id.action_singupPage_to_homePage)
            }
        }

        // Configura el listener para el texto de "Ya tienes una cuenta"
        binding.textViewYaTienesCuenta.setOnClickListener {
            findNavController().navigate(R.id.action_singupPage_to_loginPage)
        }
    }

    /**
     * Llamado cuando la vista del fragmento está a punto de ser destruida.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}