package com.example.listatareas.model

data class Tarea(
    val id: Long,
    val descripcion: String,
    var completada: Boolean = false,
    val fechaCreacion: Long = System.currentTimeMillis()
)