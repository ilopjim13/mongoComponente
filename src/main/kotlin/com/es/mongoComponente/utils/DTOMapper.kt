package com.es.mongoComponente.utils

import com.es.mongoComponente.dto.UsuarioDTO
import com.es.mongoComponente.model.Direccion
import com.es.mongoComponente.model.Usuario
import org.springframework.stereotype.Component

object DTOMapper {

    fun userDTOToEntity(usuarioInsertDTO: UsuarioDTO) : Usuario {
        return Usuario(
            usuarioInsertDTO.email,
            usuarioInsertDTO.nombre,
            usuarioInsertDTO.nick,
            true,
            listOf(usuarioInsertDTO.telefono1, usuarioInsertDTO.telefono2),
            Direccion(
                usuarioInsertDTO.calle,
                usuarioInsertDTO.numero,
                usuarioInsertDTO.cp,
                usuarioInsertDTO.ciudad,
                usuarioInsertDTO.municipio
            )
        )
    }

    fun userEntityToDTO(usuario: Usuario) : UsuarioDTO {

        return UsuarioDTO(
            usuario._id,
            usuario.nombre,
            usuario.nick,
            usuario.telefonos[0] ?: "",
            usuario.telefonos[1] ?: "",
            usuario.direccion.calle,
            usuario.direccion.num,
            usuario.direccion.cp,
            usuario.direccion.ciudad,
            usuario.direccion.municipio
        )

    }

    fun listOfUserEntitiesToDTO(usuarios: List<Usuario>) : List<UsuarioDTO> {
        return usuarios.map {
            UsuarioDTO(
                it._id,
                it.nombre,
                it.nick,
                it.telefonos[0] ?: "",
                it.telefonos[1] ?: "",
                it.direccion.calle,
                it.direccion.num,
                it.direccion.cp,
                it.direccion.ciudad,
                it.direccion.municipio
            )
        }

    }
}