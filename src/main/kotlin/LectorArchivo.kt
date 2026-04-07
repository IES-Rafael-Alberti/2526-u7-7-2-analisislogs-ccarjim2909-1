package org.iesra

import java.io.File

class LectorArchivo {

    fun leer(ruta: String): List<String> {
        return File(ruta).readLines()
    }
}