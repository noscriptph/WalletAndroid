package com.noscript.walletandroid.vista.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.noscript.walletandroid.R
import com.noscript.walletandroid.databinding.FragmentLoginSingupPageBinding

/**
 * Fragmento para la página de inicio de sesión y registro.
 */
class LoginSingupPage : Fragment() {

    // Variable para el enlace de vista
    private var _enlaceVista: FragmentLoginSingupPageBinding? = null
    private val enlaceVista get() = _enlaceVista!!

    // Variable para controlar el doble toque del botón de atrás
    private var dobleToqueParaSalir = false

    /**
     * Inflar el diseño del fragmento y configurar el enlace de vista.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _enlaceVista = FragmentLoginSingupPageBinding.inflate(inflater, container, false)
        return enlaceVista.root
    }

    /**
     * Configurar los oyentes de clic y el comportamiento del botón de atrás.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Navegación al fragmento de registro (signup)
        enlaceVista.signupContainer.setOnClickListener {
            findNavController().navigate(R.id.action_loginSingupPage_to_singupPage)
        }

        // Navegación al fragmento de inicio de sesión (login)
        enlaceVista.textView2.setOnClickListener {
            findNavController().navigate(R.id.action_loginSingupPage_to_loginPage)
        }

        // Manejo del botón de atrás para evitar volver al splash screen
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (dobleToqueParaSalir) {
                    // Si el botón de atrás se presionó dos veces, cerrar la aplicación
                    requireActivity().finish()
                    return
                }

                // Indicar al usuario que presione nuevamente para salir
                dobleToqueParaSalir = true
                Toast.makeText(requireContext(), "Presiona nuevamente atrás para salir", Toast.LENGTH_SHORT).show()

                // Restablecer la variable después de la duración del Toast
                Handler(Looper.getMainLooper()).postDelayed({
                    dobleToqueParaSalir = false
                }, Toast.LENGTH_SHORT.toLong()) // Duración del Toast.LENGTH_SHORT es 2000ms
            }
        })
    }

    /**
     * Limpiar el enlace de vista cuando se destruye la vista del fragmento.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _enlaceVista = null
    }
}
