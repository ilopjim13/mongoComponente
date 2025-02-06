package com.es.mongoComponente.service

import com.es.mongoComponente.dto.UsuarioDTO
import com.es.mongoComponente.dto.UsuarioNoTlfnDTO
import com.es.mongoComponente.exception.exceptions.NotFoundException
import com.es.mongoComponente.repository.UsuarioRepository
import com.es.mongoComponente.utils.DTOMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UsuarioService {

    @Autowired
    private lateinit var usuarioRepository: UsuarioRepository
    //@Autowired
//    private lateinit var apiService: ExternalApiService

    //fun insertUser(usuarioDTO: UsuarioDTO) : UsuarioDTO {
//
    //    // Mapeo DTO a Entity
    //    val usuario = DTOMapper.userDTOToEntity(usuarioDTO)
//
    //    // Realizo una llamada a una API externa para obtener todas las provincias de España
    //    val datosProvincias = apiService.obtenerDatosDesdeApi()
//
    //    // Si los datos vienen rellenos entonces busco la provincia dentro del resultado de la llamada
    //    if (datosProvincias != null) {
    //        if(datosProvincias.data != null) {
    //            datosProvincias.data.stream().filter {
    //                it.PRO == usuario.direccion.ciudad.uppercase()
    //            }.findFirst().orElseThrow {
    //                NotFoundException("Provincia ${usuario.direccion.ciudad.uppercase()} no válida")
    //            }
    //        }
    //    }
    //    // Si todo ha ido bien, inserto el usuario
    //    usuarioRepository.insert(usuario)
//
    //    // Devuelvo un DTO
    //    return DTOMapper.userEntityToDTO(usuario)
    //}

    fun getUsuarioByCiudad(ciudad: String): List<UsuarioDTO> {

        // Uso el método que he creado personalizado
        val usuarios = usuarioRepository.findByCiudad(ciudad)

        // Si no se encuentra, puedo lanzar una excepción
        if (usuarios.isEmpty()) throw NotFoundException("Ciudad $ciudad no encontrada")

        // Mapeo Entities a DTO
        val usuariosDto = DTOMapper.listOfUserEntitiesToDTO(usuarios)

        // Devuelvo List<DTO>
        return usuariosDto

    }

    fun findNoActivo() :List<String> {
        return usuarioRepository.findNoActivos()
    }

    fun findNoTlfn() :List<UsuarioNoTlfnDTO> {
        val usuarios = usuarioRepository.findNoTlfn()
        val usuariosNoTlfn = mutableListOf<UsuarioNoTlfnDTO>()
        usuarios.map {
            usuariosNoTlfn.add(UsuarioNoTlfnDTO(it._id, it.nick))
        }
        return usuariosNoTlfn
    }




}