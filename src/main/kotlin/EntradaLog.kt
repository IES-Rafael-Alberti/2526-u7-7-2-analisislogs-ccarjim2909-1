package org.iesra
import java.time.LocalDateTime

data class EntradaLog(
    val timestamp: LocalDateTime,
    val nivel: String,
    val mensaje: String
)