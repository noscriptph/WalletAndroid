package com.noscript.walletandroid.vista.fragments

import com.noscript.walletandroid.vistaModelo.TransaccionesAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.noscript.walletandroid.R
import com.noscript.walletandroid.databinding.FragmentHomePageBinding
import com.noscript.walletandroid.vistaModelo.UsuarioViewModel
import com.noscript.walletandroid.vistaModelo.transacciones as listaTransacciones

/**
 * Fragmento para la página de inicio.
 */
class HomePage : Fragment() {

    private var _binding: FragmentHomePageBinding? = null
    private val binding get() = _binding!!
    private lateinit var usuarioViewModel: UsuarioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomePageBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar el ViewModel del usuario
        usuarioViewModel = ViewModelProvider(this).get(UsuarioViewModel::class.java)

        // Configurar el RecyclerView con las transacciones de ejemplo
        configurarRecyclerView()

        // Observar cambios en los datos del usuario
        usuarioViewModel.usuario.observe(viewLifecycleOwner) { usuario ->
            binding.textView21.text = usuario.nombre
            binding.textView23.text = String.format("$%.2f", usuario.montoDinero)
        }
        // Configurar OnClickListener para imageView6 para abrir fragment de perfil
        binding.imageView6.setOnClickListener {
            findNavController().navigate(R.id.action_homePage_to_profilePage)
        }
        // Configurar OnClickListener para imageView8 para abrir fragment de envio de dinero
        binding.imageView8.setOnClickListener {
            findNavController().navigate(R.id.action_homePage_to_sendMoney)
        }
        // Configurar OnClickListener para imageView9 para abrir fragment de peticion de dinero
        binding.imageView9.setOnClickListener {
            findNavController().navigate(R.id.action_homePage_to_requestMoney)
        }
    }

    /**
     * Configurar el RecyclerView con las transacciones de la lista de modelo.
     */
    private fun configurarRecyclerView() {
        // Obtener las transacciones de la lista de modelo
        val transacciones = listaTransacciones

        // Crear un adaptador para el RecyclerView con las transacciones
        val adaptador = TransaccionesAdapter(transacciones)

        // Asignar el adaptador al RecyclerView
        binding.recyclerViewTransacciones.apply {
            adapter = adaptador
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        // Comprobar si hay transacciones y cambiar la visibilidad en consecuencia
        if (transacciones.isEmpty()) {
            binding.textView42.visibility = View.VISIBLE
            binding.imageView19.visibility = View.VISIBLE
        } else {
            binding.textView42.visibility = View.INVISIBLE
            binding.imageView19.visibility = View.INVISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}