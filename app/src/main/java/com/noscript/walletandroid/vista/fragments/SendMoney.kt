package com.noscript.walletandroid.vista.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.noscript.walletandroid.R

/**
 * Fragmento para enviar dinero.
 */
class SendMoney : Fragment() {

    /**
     * Método llamado al crear la vista del fragmento.
     *
     * @param inflater El inflador de la vista.
     * @param container El contenedor de la vista.
     * @param savedInstanceState El estado previamente guardado de la instancia.
     * @return La vista inflada.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla el diseño de la vista de este fragmento
        return inflater.inflate(R.layout.fragment_send_money, container, false)
    }

    /**
     * Método llamado cuando la vista del fragmento ha sido creada.
     *
     * @param view La vista del fragmento.
     * @param savedInstanceState El estado previamente guardado de la instancia.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configura la acción de navegación para enviar dinero
        view.findViewById<View>(R.id.imageView25).setOnClickListener {
            // Navega al destino deseado cuando se hace clic en el botón de enviar dinero
            findNavController().navigate(R.id.action_sendMoney_to_homePage)
        }
    }
}
