package org.iesra

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.system.exitProcess

class AnalizadorCLI : CliktCommand() {

    private val formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    val input by option("-i", "--input").required()

    val from by option("-f", "--from")
    val to by option("-t", "--to")

    val level by option("-l", "--level")

    val stats by option("-s", "--stats").flag(default = false)
    val report by option("-r", "--report").flag(default = false)

    val output by option("-o", "--output")
    val stdout by option("-p", "--stdout").flag(default = false)

    val ignoreInvalid by option("--ignore-invalid").flag(default = false)

    override fun run() {
        val lineas = try {
            val lector = LectorArchivo(input)
            lector.leerLineas()
        } catch (e: Exception) {
            println("Error: no se pudo leer el archivo '${input}': ${e.message}")
            exitProcess(1)
        }

        val fechaInicio = from?.let { LocalDateTime.parse(it, formato) }
        val fechaFin = to?.let { LocalDateTime.parse(it, formato) }

        val niveles = level?.split(",")

        val procesador = ProcesadorLog(
            lineas,
            fechaInicio,
            fechaFin,
            niveles,
            ignoreInvalid
        )

        procesador.procesar()

        val mostrar = MostrarArchivo()
        val soloStats = stats && !report

        if (stdout) mostrar.mostrar(procesador, soloStats)
        if (output != null) mostrar.guardar(procesador, output!!, soloStats)
    }
}