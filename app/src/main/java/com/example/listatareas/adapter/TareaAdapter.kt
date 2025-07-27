package com.example.listatareas.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listatareas.R
import com.example.listatareas.model.Tarea

class TareaAdapter(
    private val listaTareas: MutableList<Tarea>,
    private val onTareaCompletada: (Tarea) -> Unit
) : RecyclerView.Adapter<TareaAdapter.TareaViewHolder>() {

    inner class TareaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkBoxTarea: CheckBox = itemView.findViewById(R.id.checkBoxTarea)
        val tvDescripcionTarea: TextView = itemView.findViewById(R.id.tvDescripcionTarea)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareaViewHolder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tarea, parent, false)
        return TareaViewHolder(vista)
    }

    override fun onBindViewHolder(holder: TareaViewHolder, position: Int) {
        val tarea = listaTareas[position]
        holder.tvDescripcionTarea.text = tarea.descripcion
        holder.checkBoxTarea.isChecked = tarea.completada

        // Estilo tachado si está completada
        holder.tvDescripcionTarea.apply {
            paint.isStrikeThruText = tarea.completada
        }

        holder.checkBoxTarea.setOnCheckedChangeListener(null) // Previene cambios no deseados al reciclar
        holder.checkBoxTarea.setOnCheckedChangeListener { _, isChecked ->
            tarea.completada = isChecked
            notifyItemChanged(position)
            onTareaCompletada(tarea)
        }
    }

    override fun getItemCount(): Int = listaTareas.size

    fun agregarTarea(tarea: Tarea) {
        listaTareas.add(0, tarea)
        notifyItemInserted(0)
    }

    fun eliminarTarea(posicion: Int) {
        listaTareas.removeAt(posicion)
        notifyItemRemoved(posicion)
    }

    fun getTareas(): List<Tarea> = listaTareas
}
