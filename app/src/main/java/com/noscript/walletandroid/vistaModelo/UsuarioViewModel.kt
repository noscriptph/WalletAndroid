package com.noscript.walletandroid.vistaModelo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * ViewModel para gestionar los datos del usuario.
 * Se encarga de mantener y actualizar los datos del usuario de manera que sobrevivan a cambios de configuración.
 */
class UsuarioViewModel : ViewModel() {

    // LiveData mutable que contiene los datos del usuario.
    val usuario = MutableLiveData<Usuario>()

    /**
     * Inicializa el ViewModel con un usuario vacío.
     * Se establece un usuario con valores por defecto cuando el ViewModel es creado.
     */
    init {
        usuario.value = Usuario(
            nombre = "", // Nombre del usuario, inicialmente vacío.
            apellido = "", // Apellido del usuario, inicialmente vacío.
            email = "", // Correo electrónico del usuario, inicialmente vacío.
            contrasena = "", // Contraseña del usuario, inicialmente vacía.
            montoDinero = 0.0, // Monto de dinero en la cuenta del usuario, inicialmente 0.0.
            sesionIniciada = false // Estado de la sesión del usuario, inicialmente no iniciada.
        )
    }

    /**
     * Actualiza los datos del usuario con los valores proporcionados.
     *
     * @param usuarioActualizado Los nuevos datos del usuario que se deben establecer.
     */
    fun actualizarUsuario(usuarioActualizado: Usuario) {
        // Asigna el nuevo valor al LiveData de usuario
        usuario.value = usuarioActualizado
    }
}

