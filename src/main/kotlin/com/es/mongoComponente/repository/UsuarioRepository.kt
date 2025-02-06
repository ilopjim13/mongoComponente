package com.es.mongoComponente.repository

import com.es.mongoComponente.model.Usuario
import org.springframework.data.mongodb.repository.MongoRepository

interface UsuarioRepository : MongoRepository<Usuario, String>, UsuarioCustomRepository{
}