package org.iesra
import java.io.File

class MostrarArchivo {

    fun mostrar(procesador: ProcesadorLog) {
        val informe = generarInforme(procesador)
        println("===== INFORME =====")
        println(informe)
    }

    fun guardar(procesador: ProcesadorLog, ruta: String) {
        val informe = generarInforme(procesador)
        File(ruta).writeText("===== INFORME =====\n$informe")
    }

    private fun generarInforme(procesador: ProcesadorLog): String {
        return "Total líneas: ${procesador.totalLineas}\n" +
                "Líneas válidas: ${procesador.lineasValidas}\n" +
                "Líneas inválidas: ${procesador.lineasInvalidas}\n" +
                "INFO: ${procesador.totalInfo}\n" +
                "WARNING: ${procesador.totalWarning}\n" +
                "ERROR: ${procesador.totalError}\n" +
                "Primera fecha: ${procesador.primeraFecha}\n" +
                "Última fecha: ${procesador.ultimaFecha}"
    }
}