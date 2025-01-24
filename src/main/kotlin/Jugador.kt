package es.iesra.prog2425_ahorcado

class Jugador(intentosMaximos: Int) {
    private var intentos = intentosMaximos
    private val letrasUsadas = mutableSetOf<Char>()

    fun intentarLetra(letra: Char): Boolean {
        return if (!letrasUsadas.contains(letra)) {
            letrasUsadas.add(letra)
            true
        } else {
            false
        }
    }

    fun fallarIntento() {
        if (intentos > 0) {
            intentos--
        }
    }

    fun obtenerLetrasUsadas(): String {
        return letrasUsadas.joinToString(" ")
    }

    fun obtenerIntentosRestantes(): Int {
        return intentos
    }
}
