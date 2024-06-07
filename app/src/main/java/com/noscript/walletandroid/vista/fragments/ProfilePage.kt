package com.noscript.walletandroid.vista.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.noscript.walletandroid.databinding.FragmentProfilePageBinding

/**
 * Fragmento que representa la página de perfil.
 */
class ProfilePage : Fragment() {

    // Variable privada para el enlace de vista (view binding)
    private var _binding: FragmentProfilePageBinding? = null

    // Propiedad para acceder al enlace de vista de manera segura
    private val binding get() = _binding!!

    /**
     * Método llamado cuando se crea el fragmento.
     * @param savedInstanceState Estado previamente guardado del fragmento.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /**
     * Método llamado para crear y devolver la jerarquía de vistas asociada con el fragmento.
     * @param inflater Objeto LayoutInflater para inflar cualquier vista en el fragmento.
     * @param container Vista padre a la que se adjuntará la interfaz de usuario del fragmento.
     * @param savedInstanceState Estado previamente guardado del fragmento.
     * @return La vista para la interfaz de usuario del fragmento, o null.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño para este fragmento utilizando el enlace de vista
        _binding = FragmentProfilePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Método llamado cuando la vista del fragmento se destruye.
     * Aquí se limpia el enlace de vista para evitar fugas de memoria.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}