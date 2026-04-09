package org.iesra

fun main() {

    val lector: ILectorLog = LectorArchivo("archivo.txt")
    val lineas = lector.leerLineas()

    val procesador = ProcesadorLog(lineas)
    procesador.procesar()

    val mostrar = MostrarArchivo()
    mostrar.mostrar(procesador)
    mostrar.guardar(procesador, "informe.txt")
}