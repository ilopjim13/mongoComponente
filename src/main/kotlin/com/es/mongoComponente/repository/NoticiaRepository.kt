package com.es.mongoComponente.repository

import com.es.mongoComponente.model.Noticia
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface NoticiaRepository:MongoRepository<Noticia, String> {
}