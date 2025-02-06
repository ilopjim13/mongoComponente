package com.es.mongoComponente.dto

import java.util.*

data class NoticiasUsuarioDTO(
    val _id:String,
    val nombre:String,
    val nick:String,
    val estado:Boolean,
    val telefonos:List<String?>,
    val titulo:String,
    val cuerpo:String,
    val fechaPub: Date,
    val tag:List<String>?
) {
}