package com.noscript.walletandroid.vistaModelo

data class Transacciones(
    val id: Int,
    val nombre: String,
    val monto: Double,
    val tipo: String, // "ingreso" o "gasto"
    val fecha: String
)

// Generación de algunas transacciones de ejemplo
val transacciones = listOf(
    Transacciones(1, "Usuario1", 50.0, "ingreso", "2024-05-25"),
    Transacciones(2, "Usuario2", 30.0, "gasto", "2024-05-24"),
    Transacciones(3, "Usuario1", 70.0, "ingreso", "2024-05-23"),
    Transacciones(4, "Usuario3", 20.0, "gasto", "2024-05-22"),
    Transacciones(5, "Usuario4", 60.0, "ingreso", "2024-05-21"),
    Transacciones(6, "Usuario2", 40.0, "gasto", "2024-05-20"),
    Transacciones(7, "Usuario5", 80.0, "ingreso", "2024-05-19"),
    Transacciones(8, "Usuario3", 50.0, "gasto", "2024-05-18"),
    Transacciones(9, "Usuario6", 90.0, "ingreso", "2024-05-17"),
    Transacciones(10, "Usuario4", 70.0, "gasto", "2024-05-16"),
    Transacciones(11, "Usuario7", 100.0, "ingreso", "2024-05-15"),
    Transacciones(12, "Usuario5", 80.0, "gasto", "2024-05-14"),
    Transacciones(13, "Usuario8", 110.0, "ingreso", "2024-05-13"),
    Transacciones(14, "Usuario6", 90.0, "gasto", "2024-05-12"),
    Transacciones(15, "Usuario9", 120.0, "ingreso", "2024-05-11"),
    Transacciones(16, "Usuario7", 100.0, "gasto", "2024-05-10"),
    Transacciones(17, "Usuario10", 130.0, "ingreso", "2024-05-09"),
    Transacciones(18, "Usuario8", 110.0, "gasto", "2024-05-08"),
    Transacciones(19, "Usuario11", 140.0, "ingreso", "2024-05-07"),
    Transacciones(20, "Usuario9", 120.0, "gasto", "2024-05-06"),
    Transacciones(21, "Usuario12", 150.0, "ingreso", "2024-05-05"),
    Transacciones(22, "Usuario10", 130.0, "gasto", "2024-05-04"),
    Transacciones(23, "Usuario13", 160.0, "ingreso", "2024-05-03"),
    Transacciones(24, "Usuario11", 140.0, "gasto", "2024-05-02"),
    Transacciones(25, "Usuario14", 170.0, "ingreso", "2024-05-01"),
    Transacciones(26, "Usuario12", 150.0, "gasto", "2024-04-30"),
    Transacciones(27, "Usuario15", 180.0, "ingreso", "2024-04-29"),
    Transacciones(28, "Usuario13", 160.0, "gasto", "2024-04-28"),
    Transacciones(29, "Usuario16", 190.0, "ingreso", "2024-04-27"),
    Transacciones(30, "Usuario14", 170.0, "gasto", "2024-04-26")
)


