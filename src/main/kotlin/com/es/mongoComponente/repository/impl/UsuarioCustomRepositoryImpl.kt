package com.es.mongoComponente.repository.impl

import com.es.mongoComponente.model.Usuario
import com.es.mongoComponente.repository.UsuarioCustomRepository
import com.mongodb.client.model.Filters
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Repository


@Repository
class UsuarioCustomRepositoryImpl : UsuarioCustomRepository {

    @Autowired
    private lateinit var mongoTemplate: MongoTemplate
    override fun findByCiudad(ciudad: String): List<Usuario> {
        val database = mongoTemplate.db
        val collection = database.getCollection("coll_usuarios", Usuario::class.java)

        val filtroCiudad = Filters.eq("direccion.ciudad", ciudad)
        val usuarios = collection.find(filtroCiudad).toList()

        return usuarios
    }

    override fun findNoActivos(): List<String> {
        val database = mongoTemplate.db
        val collection = database.getCollection("coll_usuarios", Usuario::class.java)

        val filtroActivo = Filters.eq("estado", false)
        val usuarios = collection.find(filtroActivo).toList()
        return usuarios.map { it._id }
    }

    fun finNoTlfn():List<Usuario> {
        val database = mongoTemplate.db
        val collection = database.getCollection("coll_usuarios", Usuario::class.java)

        val filtroNoTlfn = Filters.eq("telefonos", "")
        return collection.find(filtroNoTlfn).toList()
    }


}