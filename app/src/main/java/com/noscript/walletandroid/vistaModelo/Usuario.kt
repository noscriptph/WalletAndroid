package com.noscript.walletandroid.vistaModelo

import android.content.Context
import android.content.SharedPreferences

/**
 * Clase que representa a un usuario de la aplicación.
 *
 * @property nombre El nombre del usuario.
 * @property apellido El apellido del usuario.
 * @property email El correo electrónico del usuario.
 * @property contrasena La contraseña del usuario.
 * @property montoDinero El monto de dinero asociado al usuario.
 * @property sesionIniciada Booleano que indica si la sesión del usuario está iniciada o no.
 */
data class Usuario(
    var nombre: String,
    var apellido: String,
    var email: String,
    var contrasena: String,
    var montoDinero: Double,
    var sesionIniciada: Boolean
) {
    companion object {
        private const val PREFS_NAME = "UsuarioPrefs"
        private const val KEY_NOMBRE = "nombre"
        private const val KEY_APELLIDO = "apellido"
        private const val KEY_EMAIL = "email"
        private const val KEY_CONTRASENA = "contrasena"
        private const val KEY_MONTO_DINERO = "monto_dinero"
        private const val KEY_SESION_INICIADA = "sesion_iniciada"

        private val usuarioPorDefecto = Usuario(
            nombre = "user",
            apellido = "admin",
            email = "user",
            contrasena = "admin",
            montoDinero = 100000.0,
            sesionIniciada = false
        )

        /**
         * Método estático para guardar los datos de un usuario en SharedPreferences.
         *
         * @param context El contexto de la aplicación.
         * @param usuario El objeto com.noscript.walletandroid.vistaModelo.Usuario que se va a guardar.
         */
        fun guardarUsuario(context: Context, usuario: Usuario) {
            val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = prefs.edit()
            editor.putString(KEY_NOMBRE, usuario.nombre)
            editor.putString(KEY_APELLIDO, usuario.apellido)
            editor.putString(KEY_EMAIL, usuario.email)
            editor.putString(KEY_CONTRASENA, usuario.contrasena)
            editor.putFloat(KEY_MONTO_DINERO, usuario.montoDinero.toFloat())
            editor.putBoolean(KEY_SESION_INICIADA, usuario.sesionIniciada)
            editor.apply()
        }

        /**
         * Método estático para obtener los datos de un usuario desde SharedPreferences.
         *
         * @param context El contexto de la aplicación.
         * @return El objeto com.noscript.walletandroid.vistaModelo.Usuario recuperado desde SharedPreferences.
         */
        fun obtenerUsuario(context: Context): Usuario {
            val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val nombre = prefs.getString(KEY_NOMBRE, "") ?: ""
            val apellido = prefs.getString(KEY_APELLIDO, "") ?: ""
            val email = prefs.getString(KEY_EMAIL, "") ?: ""
            val contrasena = prefs.getString(KEY_CONTRASENA, "") ?: ""
            val montoDinero = prefs.getFloat(KEY_MONTO_DINERO, 0f).toDouble()
            val sesionIniciada = prefs.getBoolean(KEY_SESION_INICIADA, false)

            return if (nombre.isEmpty() && email.isEmpty() && contrasena.isEmpty()) {
                usuarioPorDefecto
            } else {
                Usuario(nombre, apellido, email, contrasena, montoDinero, sesionIniciada)
            }
        }

        /**
         * Método estático para inicializar un usuario por defecto si no existe ninguno.
         *
         * @param context El contexto de la aplicación.
         */
        fun inicializarUsuarioPorDefecto(context: Context) {
            val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            if (!prefs.contains(KEY_EMAIL)) {
                guardarUsuario(context, usuarioPorDefecto)
            }
        }
    }
}
