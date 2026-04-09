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
        return if (soloStats) {
            "Total lineas: ${procesador.totalLineas}\n" +
                    "INFO: ${procesador.totalInfo}\n" +
                    "WARNING: ${procesador.totalWarning}\n" +
                    "ERROR: ${procesador.totalError}"
        } else {
            "Total lineas: ${procesador.totalLineas}\n" +
                    "Lineas validas: ${procesador.lineasValidas}\n" +
                    "Lineas invalidas: ${procesador.lineasInvalidas}\n" +
                    "INFO: ${procesador.totalInfo}\n" +
                    "WARNING: ${procesador.totalWarning}\n" +
                    "ERROR: ${procesador.totalError}\n" +
                    "Primera fecha: ${procesador.primeraFecha}\n" +
                    "Ultima fecha: ${procesador.ultimaFecha}"
        }
    }
}