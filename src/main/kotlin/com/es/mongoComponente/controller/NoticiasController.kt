package com.es.mongoComponente.controller

import com.es.mongoComponente.domain.Log
import com.es.mongoComponente.dto.NoticiaBetweenDatesDTO
import com.es.mongoComponente.exception.exceptions.NotFoundException
import com.es.mongoComponente.model.Noticia
import com.es.mongoComponente.model.Usuario
import com.es.mongoComponente.repository.NoticiaRepository
import com.es.mongoComponente.utils.FileManager
import com.es.mongoComponente.utils.LogUtils
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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
        FileManager.escribir("GET /noticias/obtener $titulo")
        return ResponseEntity(noticia.get(), HttpStatus.OK)
    }

    @DeleteMapping("/{titulo}")
    fun borrarNoticia() {

    }


    @PutMapping("/{titulo}")
    fun updateOne(
        @PathVariable id: String,
        @RequestBody noticiaToUpdate: Noticia,
        httpRequest: HttpServletRequest
    ) : ResponseEntity<Noticia>? {

        val noticiaExistente = noticiaRepository.findById(id).orElseThrow{
            NotFoundException("No se ha encontrado la noticia")
        }

        val nuevaNoticia = noticiaExistente.copy(
            titulo = noticiaToUpdate.titulo ?: noticiaExistente.titulo,
            cuerpo = noticiaToUpdate.cuerpo ?: noticiaExistente.cuerpo,
            fechaPub = noticiaToUpdate.fechaPub ?: noticiaExistente.fechaPub,
            tag = noticiaToUpdate.tag ?: noticiaExistente.tag,
            user = noticiaToUpdate.user ?: noticiaExistente.user,
        )

        val noticiaActualizada = noticiaRepository.save(nuevaNoticia)
        LogUtils.writeLog(Log(httpRequest.method, httpRequest.requestURI, true, HttpStatus.OK))

        return ResponseEntity(noticiaActualizada, HttpStatus.OK)
    }

    @GetMapping("/getBetweenDates")
    fun getNoticiasBetweenDate(
        httpRequest: HttpServletRequest,
        @RequestBody betweenDates: NoticiaBetweenDatesDTO
    ) :ResponseEntity<List<Noticia>> {
        val startDate = betweenDates.startDate
        val endDate = betweenDates.endDate

        val noticias = noticiaRepository.getNoticiasBetweenDates(startDate, endDate)

        return ResponseEntity(noticias,  HttpStatus.OK)
    }

    @GetMapping("/obtenerUltimos")
    fun obtenerUltimos(
        httpRequest: HttpServletRequest
    ) :ResponseEntity<List<Noticia>>  {
        val noticias = noticiaRepository.obtenerUltimos()
        return ResponseEntity(noticias, HttpStatus.OK)
    }

    @GetMapping("/noticiasUsuario")
    fun usuariosNoticia(
        httpRequest: HttpServletRequest,
        @RequestBody usuario: Usuario
    ) {
        val id = usuario._id
        val noticias = noticiaRepository.getNoticiasUsuario(id)
        val noticiasUsuario = listOf()
    }
}