package com.es.mongoComponente.repository

import com.es.mongoComponente.model.Noticia
import java.util.Date

interface NoticiaCustomRepository {

    fun getNoticiasBetweenDates(startDate:Date, endDate:Date):List<Noticia>

    fun obtenerUltimos():List<Noticia>

    fun getNoticiasUsuario(nick:String):List<Noticia>


}