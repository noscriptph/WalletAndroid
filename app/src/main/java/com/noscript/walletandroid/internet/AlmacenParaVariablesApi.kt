package com.noscript.walletandroid.internet

/**
 * Clase de datos que representa las variables de la API.
 *
 * @property createdAt Fecha y hora de creación del registro.
 * @property creationDate Fecha de creación del registro.
 * @property id Identificador único del registro.
 * @property isBlocked Indica si el registro está bloqueado.
 * @property money Cantidad de dinero asociada al registro.
 * @property updatedAt Fecha y hora de la última actualización del registro.
 * @property userId Identificador único del usuario asociado al registro.
 */
data class AlmacenParaVariablesApi(
    val createdAt: String,    // Fecha y hora de creación del registro
    val creationDate: String, // Fecha de creación del registro
    val id: Int,              // Identificador único del registro
    val isBlocked: Boolean,   // Indica si el registro está bloqueado
    val money: String,        // Cantidad de dinero asociada al registro
    val updatedAt: String,    // Fecha y hora de la última actualización del registro
    val userId: Int           // Identificador único del usuario asociado al registro
)