package es.iesra.prog2425_ahorcado

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.gson.*
import kotlinx.coroutines.runBlocking

class Palabra(val palabraOculta: String) {
    private val progreso = CharArray(palabraOculta.length) { '_' }

    /*
    fun revelarLetraV1(letra: Char): Boolean {
        var encontrada = false
        for (i in palabraOculta.indices) {
            if (palabraOculta[i] == letra) {
                progreso[i] = letra
                encontrada = true
            }
        }
        return encontrada
    }
    */

    fun revelarLetra(letra: Char): Boolean {
        val letraSinAcento = letra.quitarAcentos()
        var encontrada = false

        for (i in palabraOculta.indices) {

            if (palabraOculta[i].quitarAcentos() == letraSinAcento) {
                encontrada = true
                progreso[i] = palabraOculta[i]
            }

        }

        return encontrada
    }

    fun obtenerProgreso(): String {
        return progreso.joinToString(" ")
    }

    fun esCompleta(): Boolean {
        return !progreso.contains('_')
    }

    fun obtenerPalabraOculta(): String {
        return palabraOculta
    }

    companion object {
        fun generarPalabras(
            cantidad: Int,
            tamanioMin: Int,
            tamanioMax: Int,
            idioma: Idioma = Idioma.ES,
            soloConTildes: Boolean = false
        ): MutableSet<Palabra> {
            val client = HttpClient {
                install(ContentNegotiation) {
                    gson()
                }
            }
            val palabras = mutableSetOf<Palabra>()
            val url = "https://random-word-api.herokuapp.com/word?number=${cantidad * 5}&lang=${idioma.codigo}"
            val patron = if (idioma == Idioma.ES) {
                "^[a-záéíóúüñ]+$"
            } else {
                "^[a-z]+$"
            }

            runBlocking {
                try {
                    while (palabras.size < cantidad) {
                        val respuesta: Array<String> = client.get(url).body()
                        val filtradas = respuesta
                            .map { it.trim().lowercase() }
                            .filter { it.length in tamanioMin..tamanioMax }
                            .filter { it.matches(Regex(patron)) }
                            .filter { !it.contains(" ") }
                            .filter { if (soloConTildes) it.matches(Regex(".*[áéíóúü].*")) else true }
                            .map { Palabra(it) }

                        palabras.addAll(filtradas)
                    }
                } catch (e: Exception) {
                    println("Error al obtener las palabras: ${e.message}")
                }
            }
            client.close()
            return palabras.take(cantidad).toMutableSet()
        }
    }
}
