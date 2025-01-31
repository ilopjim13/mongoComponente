package com.es.mongoComponente.controller

import com.es.mongoComponente.model.Noticia
import com.es.mongoComponente.repository.NoticiaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Date

@RestController
class HolaMundoController {

    @Autowired
    private lateinit var noticiaRepository: NoticiaRepository

    @GetMapping
    fun getHolaMundo():String {
        val noticia = Noticia(null, "Noticia 1", "Se ha insertado", Date(), listOf("prueba"), "idCliente")
        noticiaRepository.insert(noticia)
        return "<h1>HOLA MUNDO</h1>"
    }

}