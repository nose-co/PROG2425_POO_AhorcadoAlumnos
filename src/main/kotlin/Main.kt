package es.iesra.prog2425_ahorcado

fun main() {
    val palabras = Palabra.generarPalabras(cantidad = 10, tamanioMin = 7, tamanioMax = 7, idioma = Idioma.ES)
    var seguirJugando: Boolean
    do {
        val palabraOculta = palabras.pop()
        if (palabraOculta != null) {
            val jugador = Jugador(intentosMaximos = 6)
            val juego = Juego(palabraOculta, jugador)
            juego.iniciar()
            seguirJugando = juego.preguntar("¿Quieres jugar otra partida?")
        } else {
            println("No existen más palabras ocultas...")
            seguirJugando = false
        }
    } while (seguirJugando)
}

//TODO: Crear una función de extensión quitarAcentos para la clase Char
fun Char.quitarAcentos(): Char {
    val acentos = mapOf('á' to 'a', 'é' to 'e', 'í' to 'i', 'ó' to 'o', 'ú' to 'u',
        'Á' to 'A', 'É' to 'E', 'Í' to 'I', 'Ó' to 'O', 'Ú' to 'U',
        'ü' to 'u', 'Ü' to 'U')
    return acentos[this] ?: this
}

/**
 * Elimina y retorna un elemento aleatorio de este [MutableSet].
 * Si el conjunto está vacío, retorna `null`.
 *
 * @receiver MutableSet<T> El conjunto mutable del cual se eliminará el elemento.
 * @return [T]? El elemento eliminado del conjunto o `null` si el conjunto está vacío.
 * @param T El tipo de elementos que contiene el conjunto.
 */
fun <T> MutableSet<T>.pop(): T? {
    val elemento = this.randomOrNull()
    if (elemento != null) {
        this.remove(elemento)
    }
    return elemento
}
