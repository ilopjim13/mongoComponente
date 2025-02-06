package com.es.mongoComponente.repository

import com.es.mongoComponente.model.Usuario

interface UsuarioCustomRepository {
    fun findByCiudad(ciudad: String): List<Usuario>
    fun findNoActivos(): List<String>
    fun findNoTlfn() :List<Usuario>
}