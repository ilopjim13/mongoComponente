package com.es.mongoComponente.controller

import com.es.mongoComponente.model.Noticia
import com.es.mongoComponente.repository.NoticiaRepository
import com.es.mongoComponente.utils.FileManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/noticias")
class NoticiasController {

    @Autowired
    private lateinit var noticiaRepository: NoticiaRepository

    @GetMapping("/getNoticias")
    fun getNoticias():List<Noticia> {
        return noticiaRepository.findAll()
    }

    @PostMapping("/alta")
    fun altaNoticas() {

    }

    @GetMapping("/{titulo}")
    fun verNoticia(
        @PathVariable titulo:String
    ): ResponseEntity<Noticia> {
        val noticia = noticiaRepository.findByTitulo(titulo)
        println(titulo)
        if (noticia.isEmpty) {
            throw Exception()
        }
        val fileManager = FileManager()
        fileManager.escribir("GET /noticias/obtener $titulo")
        return ResponseEntity(noticia.get(), HttpStatus.OK)
    }

    @DeleteMapping("/{titulo}")
    fun borrarNoticia() {

    }

    @PostMapping("/{titulo}")
    fun  updateNoticia(
        @RequestBody noticiaUpdate:Noticia
    ) {

    }

}