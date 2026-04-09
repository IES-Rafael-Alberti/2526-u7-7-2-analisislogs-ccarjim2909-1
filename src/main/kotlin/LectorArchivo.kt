package org.iesra
import java.io.File

class LectorArchivo(ruta: String) : ILectorLog {
    private val rutaArchivo = ruta

    override fun leerLineas(): List<String> {
        return File(rutaArchivo).readLines()
    }
}

