package com.noscript.walletandroid.vista.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.noscript.walletandroid.R
import com.noscript.walletandroid.databinding.FragmentSplashScreenBinding

/**
 * Fragmento que muestra una pantalla de bienvenida (splash screen).
 * Esta pantalla se muestra durante 3 segundos antes de navegar a la página de inicio de sesión.
 */
class SplashScreen : Fragment() {

    // Binding para la vista del fragmento
    private var _binding: FragmentSplashScreenBinding? = null

    // Propiedad solo de lectura para acceder al binding de manera segura
    private val binding get() = _binding!!

    /**
     * Inflar el layout del fragmento y configurar el binding.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout usando ViewBinding
        _binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Configurar acciones después de que la vista ha sido creada.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Crear un delay de 3 segundos antes de navegar a la siguiente pantalla
        Handler(Looper.getMainLooper()).postDelayed({
            // Navegar al fragmento de inicio de sesión y registro
            findNavController().navigate(R.id.action_splashScreen_to_loginSingupPage)
        }, 3000)
    }

    /**
     * Limpiar el binding cuando la vista se destruye.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        // Evitar pérdidas de memoria liberando el binding
        _binding = null
    }
}