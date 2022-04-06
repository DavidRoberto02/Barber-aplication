package com.example.barberapp.ui.recyclerView

import java.io.Serializable


data class User(
    var nombre: String,
    var url: String,
    var descripcion: String,
    var Sexo: String
) : Serializable
