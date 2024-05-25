package com.noscript.walletandroid.vista.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.noscript.walletandroid.R
import com.noscript.walletandroid.vistaModelo.UsuarioViewModel
import com.noscript.walletandroid.databinding.FragmentLoginPageBinding
/**
 * Fragmento que maneja la vista de inicio de sesión.
 * Permite al usuario ingresar su nombre de usuario y contraseña para iniciar sesión.
 * También proporciona la opción de navegar a la página de registro.
 */
class LoginPage : Fragment() {

    private var _binding: FragmentLoginPageBinding? = null
    private val binding get() = _binding!!

    private lateinit var usuarioViewModel: UsuarioViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Infla y vincula el diseño de la página de inicio de sesión
        _binding = FragmentLoginPageBinding.inflate(inflater, container, false)
        val view = binding.root

        // Inicializa el ViewModel del usuario
        usuarioViewModel = ViewModelProvider(requireActivity()).get(UsuarioViewModel::class.java)

        // Configura el clic del botón de inicio de sesión
        binding.imageViewLogin.setOnClickListener {
            val username = binding.editTextEmailAddress.text.toString()
            val password = binding.editTextPassword.text.toString()

            // Verifica si alguno de los campos está vacío
            if (username.isEmpty() || password.isEmpty()) {
                // Muestra un mensaje de error si algún campo está vacío
                Toast.makeText(requireContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Obtiene el usuario desde el ViewModel
            val usuarioGuardado = usuarioViewModel.usuario.value

            // Verifica las credenciales ingresadas con el usuario guardado
            if (usuarioGuardado != null && username == usuarioGuardado.nombre && password == usuarioGuardado.contrasena) {
                // Si las credenciales son correctas, carga el fragmento de la página de inicio
                cargarFragmento(HomePage())
                Toast.makeText(requireContext(), "Sesión iniciada correctamente", Toast.LENGTH_SHORT).show()
            } else {
                // Si las credenciales son incorrectas, muestra un mensaje de error
                Toast.makeText(requireContext(), "Los datos son incorrectos, inténtalo nuevamente", Toast.LENGTH_SHORT).show()
            }
        }

        // Configura el clic del TextView para ir a la página de registro
        binding.textView10.setOnClickListener {
            findNavController().navigate(R.id.action_loginPage_to_singupPage)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Carga el fragmento especificado en el contenedor de fragmentos.
     *
     * @param fragmento El fragmento que se va a cargar.
     */
    private fun cargarFragmento(fragmento: Fragment) {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container_view, fragmento)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}