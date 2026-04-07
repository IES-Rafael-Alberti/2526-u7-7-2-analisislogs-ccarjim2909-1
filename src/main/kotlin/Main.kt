package org.iesra


fun main() {

    val lector = LectorArchivo()
    // val mostrador = MostrarArchivo()

    val lineas = lector.leer("archivo.txt")

    // mostrador.mostrar(lineas)
}