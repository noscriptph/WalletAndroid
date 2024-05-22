package com.noscript.walletandroid.vistaModelo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UsuarioViewModel : ViewModel() {
    val usuario = MutableLiveData<Usuario>()

    init {
        usuario.value = Usuario(
            nombre = "",
            apellido = "",
            email = "",
            contrasena = "",
            montoDinero = 0.0,
            sesionIniciada = false
        )
    }

    fun actualizarUsuario(usuarioActualizado: Usuario) {
        usuario.value = usuarioActualizado
    }
}
