package com.es.mongoComponente.repository.impl

import com.es.mongoComponente.model.Noticia
import com.es.mongoComponente.repository.NoticiaCustomRepository
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Sorts
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class NoticiaCustomRepositoryImpl :NoticiaCustomRepository{

    @Autowired
    private lateinit var mongoTemplate: MongoTemplate

    override fun getNoticiasBetweenDates(startDate: Date, endDate: Date): List<Noticia> {

        // 1º Obtener la BD
        val db = mongoTemplate.db
        // 2º Obtener la collecion
        val coll = db.getCollection("coll_noticias", Noticia::class.java)

        // 3º Poner los Filters
        val filter = Filters.and(
            Filters.gte("fechaPub", startDate),
            Filters.lte("fechaPub", endDate)
        )

        // 4º Ejecutar la consulta
        val noticias = coll.find(filter).toList()

        return noticias

    }

    override fun obtenerUltimos(): List<Noticia> {
        // 1º Obtener la BD
        val db = mongoTemplate.db
        // 2º Obtener la collecion
        val coll = db.getCollection("coll_noticias", Noticia::class.java)

        val sortDescending = Sorts.descending("fechaPub")
        return coll.find()
            .sort(sortDescending)
            .limit(10)
            .toList()
    }

    override fun getNoticiasUsuario(id: String): List<Noticia> {
        // 1º Obtener la BD
        val db = mongoTemplate.db
        // 2º Obtener la collecion
        val coll = db.getCollection("coll_noticias", Noticia::class.java)

        val filtro = Filters.eq("user", id)
        return coll.find(filtro).toList()
    }

}