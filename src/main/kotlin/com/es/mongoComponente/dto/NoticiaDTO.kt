package com.es.mongoComponente.dto


import java.util.Date

data class NoticiaDTO(
    val titulo: String,
    val cuerpo: String,
    val fechaPub: Date,
    val tags: List<String>?,
    val usuario: String
)