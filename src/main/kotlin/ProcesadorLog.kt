package org.iesra

class ProcesadorLog(val lineas: List<String>) : IProcesadorLog {

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
            totalLineas += 1
            val partes = linea.split("] ")

            if (partes.size == 2) {
                val fechaConCorchete = partes[0]
                val resto = partes[1]
                var fecha = ""
                if (fechaConCorchete.length > 1 && fechaConCorchete[0] == '[') {
                    fecha = fechaConCorchete.substring(1)
                }

                if (fecha != "" && resto != "") {
                    val nivelYMensaje = resto.split(" ", limit = 2)
                    if (nivelYMensaje.size == 2) {
                        val nivel = nivelYMensaje[0]
                        var valido = false

                        if (nivel == "INFO") { totalInfo += 1; valido = true }
                        else if (nivel == "WARNING") { totalWarning += 1; valido = true }
                        else if (nivel == "ERROR") { totalError += 1; valido = true }

                        if (valido) {
                            lineasValidas += 1
                            if (primeraFecha == "" || fecha < primeraFecha) { primeraFecha = fecha }
                            if (ultimaFecha == "" || fecha > ultimaFecha) { ultimaFecha = fecha }
                        } else { lineasInvalidas += 1 }

                    } else { lineasInvalidas += 1 }
                } else { lineasInvalidas += 1 }

            } else { lineasInvalidas += 1 }
        }
    }
}