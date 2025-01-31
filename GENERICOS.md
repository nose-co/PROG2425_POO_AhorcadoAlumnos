# GENÉRICOS EN KOTLIN

![¿Qué son los genéricos?](https://i.ytimg.com/vi/iwA5SEusHdA/maxresdefault.jpg)

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

## **2. ¿Cómo se utilizan los genéricos en Kotlin y qué ventajas tienen?**  

### **¿Cómo se usan?**  
- **Paso 1**: Al definir una función, clase o interfaz, añades **`<T>`** (o cualquier letra como `<A>`, `<B>`).  
- **Paso 2**: Usas **`T`** en tu código para representar *cualquier tipo de dato futuro*.  
- **Ejemplo mental**:  
  - Es como decirle a Kotlin: *"Aquí usaré un tipo de dato, pero te diré cuál más tarde"*.  

### **Ventajas clave** 🌟  

#### **1. Flexibilidad total**  
- Decides el tipo de dato **cuando usas el código**, no cuando lo escribes.  
- *Ejemplo real*: Es como una caja vacía: tú eliges si guardar números, textos o incluso objetos personalizados.  

#### **2. Código que sirve para todo**  
- **Escribes una sola vez** y funciona con *cualquier tipo*.  
- *Ejemplo real*: Imagina una función para "imprimir algo". Con genéricos, imprime números, textos, perros, gatos… ¡lo que sea!  

#### **3. Menos errores**  
- Kotlin **verifica automáticamente** que no mezcles tipos incorrectos.  
- *Ejemplo real*: Si creas una lista de números, no podrás añadir un texto por error.  

#### **4. Código limpio y fácil de mantener**  
- Es más claro entender qué tipos se usan y cómo.  
- *Ejemplo real*: Si alguien más ve `List<Usuario>`, entiende al instante que es una lista de objetos de tipo `Usuario`.  

---

### **Partes clave (sin tecnicismos)**  
- **`<T>`**: Es un "marcador" que le dice a Kotlin: *"Aquí irá un tipo, pero aún no sé cuál"*.  
- **Funciones genéricas**: Son como herramientas universales (ej: una función para "obtener un elemento" de cualquier lista, sin importar su tipo).  
- **Interfaces genéricas**: Son contratos que definen operaciones para cualquier tipo (ej: una interfaz para "guardar" cualquier dato en una base de datos).  

---

## 3. Un ejemplo de cómo se utilizan los genéricos y explicación de este

### Ejemplo: Función genérica pop()

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
