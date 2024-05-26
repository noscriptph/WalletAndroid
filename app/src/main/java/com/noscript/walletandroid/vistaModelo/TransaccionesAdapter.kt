package com.noscript.walletandroid.vistaModelo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.noscript.walletandroid.R
import java.text.NumberFormat
import java.util.*


/**
 * Adaptador para mostrar una lista de transacciones en un RecyclerView.
 *
 * @param transacciones La lista de transacciones a mostrar.
 */
class TransaccionesAdapter(private val transacciones: List<Transacciones>) :
    RecyclerView.Adapter<TransaccionesAdapter.TransaccionViewHolder>() {

    /**
     * ViewHolder para cada elemento de la lista de transacciones.
     */
    class TransaccionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Elementos de la interfaz de usuario para cada elemento de la transacción
        val montoTextView: TextView = itemView.findViewById(R.id.textViewTransactionAmount)
        val tipoTextView: TextView = itemView.findViewById(R.id.textViewTransactionTime)
        val fechaTextView: TextView = itemView.findViewById(R.id.textViewUserName)
        val sendIcon: ImageView = itemView.findViewById(R.id.imageViewSendIcon)
        val requestIcon: ImageView = itemView.findViewById(R.id.imageViewRequestIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransaccionViewHolder {
        // Inflar el diseño de la vista de elemento de transacción
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.transaccion, parent, false)
        return TransaccionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TransaccionViewHolder, position: Int) {
        val transaccionActual = transacciones[position]

        // Formatear el monto con separadores de miles y decimales
        val formatoMoneda = NumberFormat.getCurrencyInstance(Locale.US)
        formatoMoneda.maximumFractionDigits = 2
        val montoFormateado = formatoMoneda.format(transaccionActual.monto)

        // Agregar símbolo según el tipo de transacción
        val montoConSimbolo = when (transaccionActual.tipo) {
            "ingreso" -> "+ $montoFormateado" //uespacio después del símbolo de más
            "egreso" -> "- $montoFormateado" //no funciona actualmente esta linea
            //TODO("se debe revisar por que el simbolo menos no se visualiza en el recicler view")
            else -> montoFormateado // Si el tipo no es ingreso o egreso, mostrar solo el monto
        }

        // Mostrar los datos de la transacción en los TextView correspondientes
        holder.montoTextView.text = montoConSimbolo
        holder.tipoTextView.text = transaccionActual.tipo
        holder.fechaTextView.text = transaccionActual.fecha

        // Ajustar la visibilidad de los íconos según el tipo de transacción
        if (transaccionActual.tipo == "ingreso") {
            holder.sendIcon.visibility = View.VISIBLE
            holder.requestIcon.visibility = View.GONE
        } else {
            holder.sendIcon.visibility = View.GONE
            holder.requestIcon.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return transacciones.size
    }
}