package org.iesra

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ProcesadorLog(
    val lineas: List<String>,
    val fechaInicio: LocalDateTime? = null,
    val fechaFin: LocalDateTime? = null,
    val niveles: List<String>? = null,
    val ignorarInvalidas: Boolean = false
) : IProcesadorLog {

    private val formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    var totalLineas = 0
    var lineasValidas = 0
    var lineasInvalidas = 0
    var totalInfo = 0
    var totalWarning = 0
    var totalError = 0
    var primeraFecha = ""
    var ultimaFecha = ""

    override fun procesar() {
        for (linea in lineas) {
            totalLineas++

            try {
                val partes = linea.split("] ")
                if (partes.size != 2) throw Exception()

                val fechaStr = partes[0].removePrefix("[")
                val resto = partes[1]

                val fecha = LocalDateTime.parse(fechaStr, formato)

                // 🔹 Filtro fechas
                if (fechaInicio != null && fecha.isBefore(fechaInicio)) continue
                if (fechaFin != null && fecha.isAfter(fechaFin)) continue

                val nivelYMensaje = resto.split(" ", limit = 2)
                if (nivelYMensaje.size != 2) throw Exception()

                val nivel = nivelYMensaje[0]

                // 🔹 Filtro niveles
                if (niveles != null && nivel !in niveles) continue

                when (nivel) {
                    "INFO" -> totalInfo++
                    "WARNING" -> totalWarning++
                    "ERROR" -> totalError++
                    else -> throw Exception()
                }

                lineasValidas++

                val fechaTexto = fecha.toString()
                if (primeraFecha == "" || fechaTexto < primeraFecha) primeraFecha = fechaTexto
                if (ultimaFecha == "" || fechaTexto > ultimaFecha) ultimaFecha = fechaTexto

            } catch (e: Exception) {
                if (!ignorarInvalidas) {
                    lineasInvalidas++
                }
            }
        }
    }
}