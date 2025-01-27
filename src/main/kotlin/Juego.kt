package es.iesra.prog2425_ahorcado

class Juego(private val palabra: Palabra, private val jugador: Jugador) {

    fun iniciar() {
        println("────────────────────────────────────")
        println("¡Bienvenido al juego del Ahorcado!")
        println("────────────────────────────────────")

        println("La palabra tiene ${palabra.obtenerProgreso().filter { it != ' ' }.length} letras.")

        while (!palabra.esCompleta() && jugador.obtenerIntentosRestantes() > 0) {
            println("\nPalabra: ${palabra.obtenerProgreso()}")
            println("Intentos restantes: ${jugador.obtenerIntentosRestantes()}")
            println("Letras usadas: ${jugador.obtenerLetrasUsadas()}")

            print("Introduce una letra: ")
            val input = readln().trim().lowercase().firstOrNull()?.quitarAcentos()

            if (input == null || !jugador.intentarLetra(input)) {
                println("Letra no válida o ya utilizada. Intenta otra vez.")
            } else {
                if (palabra.revelarLetra(input)) {
                    println("¡Bien hecho! La letra '$input' está en la palabra.")
                } else {
                    println("La letra '$input' no está en la palabra.")
                    jugador.fallarIntento()
                }
            }
        }

        if (palabra.esCompleta()) {
            println("\n¡Felicidades! Has adivinado la palabra: ${palabra.obtenerProgreso()}")
        } else {
            println("\nLo siento, te has quedado sin intentos. La palabra era: ${palabra.obtenerPalabraOculta()}")
        }
    }

    fun preguntar(msj: String): Boolean {
        do {
            print("$msj (s/n): ")
            val respuesta = readln().trim().lowercase()
            when (respuesta) {
                "s" -> return true
                "n" -> return false
                else -> println("Respuesta no válida! Inténtelo de nuevo...")
            }
        } while (true)
    }
}
