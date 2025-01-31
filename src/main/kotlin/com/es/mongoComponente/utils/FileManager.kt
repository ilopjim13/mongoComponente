package com.es.mongoComponente.utils

import java.io.File
import java.util.*

class FileManager {

    private val file = File("src/main/resources/log/log.txt")

    fun escribir(ruta:String) {
        if(!file.isFile) {
            crearFile()
        } else {
            file.appendText("${Date()} $ruta\n")
        }


    }

    private fun crearFile() {
        file.createNewFile()
    }

}