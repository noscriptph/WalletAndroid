package com.noscript.walletandroid.vista

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import com.noscript.walletandroid.R
import com.noscript.walletandroid.databinding.ActivityMainBinding
import com.noscript.walletandroid.vistaModelo.Usuario

class MainActivity : AppCompatActivity() {

    // Variable para el binding de la actividad principal
    private lateinit var binding: ActivityMainBinding

    /**
     * Método llamado cuando la actividad es creada.
     *
     * @param savedInstanceState Si la actividad está siendo re-creada de un estado previamente guardado,
     * este es el estado. Si no, es nulo.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar usuario por defecto si es necesario
        Usuario.inicializarUsuarioPorDefecto(this)

        this.enableEdgeToEdge()

        // Inicializa el binding para inflar el layout de la actividad
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Establece la vista de contenido de la actividad con el root del binding
        setContentView(binding.root)

        // Ajusta los paddings de la vista principal para acomodar las barras del sistema (estatus, navegación)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v: View, insets: WindowInsetsCompat ->
            // Obtiene los insets de las barras del sistema
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Establece el padding de la vista principal
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Obtiene el fragmento de navegación
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFragment.navController

        // Si no hay un estado guardado, establece el gráfico de navegación inicial
        if (savedInstanceState == null) {
            navController.setGraph(R.navigation.nav_graph)
        }
    }
}
