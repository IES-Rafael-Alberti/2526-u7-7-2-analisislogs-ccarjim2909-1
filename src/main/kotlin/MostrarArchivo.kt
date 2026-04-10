package org.iesra

import java.io.File

class MostrarArchivo {

    fun mostrar(procesador: ProcesadorLog, soloStats: Boolean) {
        val informe = generarInforme(procesador, soloStats)
        println("===== INFORME =====")
        println(informe)
    }

    fun guardar(procesador: ProcesadorLog, ruta: String, soloStats: Boolean) {
        val informe = generarInforme(procesador, soloStats)
        File(ruta).writeText("===== INFORME =====\n$informe")
    }

    private fun generarInforme(procesador: ProcesadorLog, soloStats: Boolean): String {

        val niveles = procesador.niveles

        val infoLinea = if (niveles == null || "INFO" in niveles) {
            "INFO: ${procesador.totalInfo}\n"
        } else ""

        val warningLinea = if (niveles == null || "WARNING" in niveles) {
            "WARNING: ${procesador.totalWarning}\n"
        } else ""

        val errorLinea = if (niveles == null || "ERROR" in niveles) {
            "ERROR: ${procesador.totalError}\n"
        } else ""

        return if (soloStats) {
            "Total lineas: ${procesador.totalLineas}\n" +
                    infoLinea +
                    warningLinea +
                    errorLinea
        } else {
            "Total lineas: ${procesador.totalLineas}\n" +
                    "Lineas validas: ${procesador.lineasValidas}\n" +
                    "Lineas invalidas: ${procesador.lineasInvalidas}\n" +
                    infoLinea +
                    warningLinea +
                    errorLinea +
                    "Primera fecha: ${procesador.primeraFecha}\n" +
                    "Ultima fecha: ${procesador.ultimaFecha}"
        }
    }
}