## 1. ¿Qué son los genéricos en Kotlin?

Los genéricos en Kotlin son como **"moldes inteligentes"** que te permiten crear código flexible y seguro para trabajar con **cualquier tipo de dato** (números, textos, objetos personalizados, etc.).  

### **En términos simples**:  
- Son una forma de escribir **una sola función, clase o interfaz** que funciona con múltiples tipos de datos.  
- **Por ejemplo**: Imagina una función que imprime un elemento. Con genéricos, la misma función sirve para imprimir un número, un texto o incluso una clase como `Palabra("hola")`.  

### **Detalle técnico (sencillo)**:  
- **Definición con `<T>`**: Al escribir `<T>`, le dices a Kotlin: *"Este código funcionará con un tipo de dato `T`, que se decidirá cuando se use"*.  
  ```kotlin
  fun <T> imprimirElemento(elemento: T) {
      println(elemento)
  }
  ```  
- **Seguridad de tipos**: Kotlin verifica en tiempo de compilación que no mezcles tipos incorrectos.  
  - **Ejemplo**: Si creas una lista de números (`List<Int>`), no podrás añadir un texto.  

---

## 2. ¿Cómo se utilizan los genéricos en Kotlin y qué ventajas tienen?

En Kotlin, los genéricos se utilizan al definir una función o clase con uno o más parámetros de tipo. Se usa la notación \<T> donde T es un nombre de tipo genérico.

### Ventajas

- **Parámetros de Tipo**: Puedes definir una lista o conjunto, por ejemplo, que funcione con cualquier clase o tipo al indicar un tipo genérico. La sintaxis `MutableList<Int>` especifica una lista de enteros, mientras que `MutableList<T>` indica que puedes utilizar cualquier tipo.

- **Reutilización del Código**: Los genéricos permiten escribir funciones y clases que funcionan con cualquier tipo de datos. Esto reduce la necesidad de crear versiones repetitivas del mismo código para diferentes tipos de datos.

- **Seguridad de Tipos**: Durante la compilación, Kotlin asegura automáticamente que los tipos sean seguros. Esto significa que errores comunes como el "casting" incorrecto de tipos se detectan en tiempo de compilación, no en tiempo de ejecución.

- **Mayor claridad y mantenimiento**: El uso de genéricos permite que el código sea más claro al expresar qué tipo de datos debe manejar y facilita el mantenimiento y la evolución del código base.

### Partes clave:

<T>: Letra que representa el tipo genérico (puede ser cualquier nombre, pero T es la convención).

Funciones genéricas: Funciones que aceptan cualquier tipo.

Interfaces genéricas: Operaciones que funcionan con cualquier tipo

---

## 3. Un ejemplo de cómo se utilizan los genéricos y explicación de este

### Ejemplo 1: Función genérica pop()

```kotlin
fun <T> MutableSet<T>.pop(): T? {
    val elemento = this.randomOrNull()
    if (elemento != null) {
        this.remove(elemento)
    }
    return elemento
}
```

**Explicación:**

- La función `pop()` está definida con un tipo genérico `T`. Esto permite que la función elimine y devuelva un elemento de tipo `T` del conjunto, sin conocer específicamente el tipo de los elementos cuando se define la función.

- **¿Como funciona el .pop?**: La función intenta acceder a un elemento aleatorio del `MutableSet` que recibe como argumento (`this`), utilizando `randomOrNull()`, que devuelve un elemento aleatorio o `null` si el conjunto está vacío. Si `randomOrNull()` devuelve un elemento, este se elimina del conjunto utilizando `remove(elemento)` y luego se devuelve.

- **Ventaja**: La funcionalidad de `pop()` puede aplicarse a cualquier `MutableSet` independientemente del tipo de objeto que contenga (p.ej., `MutableSet<Palabra>`, `MutableSet<Int>`, etc.), proporcionando una capacidad general y adaptable gracias al uso de genéricos.

### Uso en el proyecto
```kotlin
val miConjunto = mutableSetOf("hola", "adios", 3, Palabra("aeiou"))

println(miConjunto.pop() ?: "No hay elementos") // Elimina "hola" (String)
println(miConjunto.pop() ?: "No hay elementos") // Elimina 3 (Int)
```

1. **`miConjunto`**: Es un conjunto mutable con elementos de distintos tipos:  
   - `"hola"` (texto), `"adios"` (texto), `3` (número), `Palabra("aeiou")` (objeto).  

2. **`pop()`**:  
   - **Qué hace**: Elimina un elemento aleatorio del conjunto y lo devuelve.  
   - **Ejemplo**:  
     - Primer `pop()` → Elimina `"hola"` (o cualquier otro elemento al azar).  
     - Segundo `pop()` → Elimina `3` (u otro elemento restante).  
   - Si el conjunto está vacío → Retorna `null`.  

3. **`?:`**:  
   - Si `pop()` retorna `null` (conjunto vacío), imprime `"No hay elementos"`.  

**Clave**: La función `pop()` usa **genéricos** (`<T>`) para funcionar con cualquier tipo de dato en el conjunto.
