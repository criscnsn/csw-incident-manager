# Estándar de Codificación

## Lenguaje elegido

Kotlin

## Objetivo

Definir reglas de programación que permitan mantener un código uniforme, legible, organizado y fácil de mantener durante el desarrollo del proyecto.

---

# 1. Nomenclatura

## Regla 1. Convenciones estándar de capitalización

Se debe utilizar:

- `PascalCase` para nombres de clases e interfaces.
- `camelCase` para variables, atributos y métodos.
- `UPPER_SNAKE_CASE` para constantes y valores de enumeraciones (`enum`).
- `snake_case` para identificadores en esquemas de base de datos.

## Regla 2. Nombres descriptivos para parámetros de funciones

Todo parámetro de una función debe tener un nombre descriptivo que indique claramente su propósito.

Se deben evitar abreviaciones ambiguas y nombres genéricos de una sola letra.

---

# 2. Formato y diseño visual

## Regla 3. Uso consistente de espacios en blanco

Se debe colocar un espacio antes y después de operadores binarios, incluyendo:

- Operadores aritméticos.
- Operadores lógicos.
- Operadores de asignación.

También se debe colocar un espacio después de las comas en listas y parámetros.

## Regla 4. Posición de llaves de apertura

Las llaves de apertura `{` deben colocarse al final de la misma línea donde se declara una clase, función o estructura de control, dejando un espacio antes de la llave.

## Regla 5. Formato multilínea para constructores extensos

Cuando un constructor o una función tenga más de tres parámetros, cada parámetro debe colocarse en una línea separada con su respectiva indentación.

---

# 3. Organización y estructura del código

## Regla 6. Organización de carpetas modular por capas

La estructura de directorios del proyecto debe organizarse por módulos o capas, separando claramente las responsabilidades técnicas.

Ejemplo de organización:

```text
src/
├── models/
├── repository/
├── services/
├── ui/
└── Main.java
```

## Regla 7. Correspondencia entre archivo y clase principal

Cada archivo fuente debe tener exactamente el mismo nombre que la clase, interfaz o componente principal que contiene.

Ejemplo:

```text
Incidencia.java
```

debe contener como clase principal:

```java
public class Incidencia {
}
```

## Regla 8. Responsabilidad única a nivel de archivo

Cada archivo debe contener una única unidad lógica.

Se debe evitar agrupar varias clases o utilidades no relacionadas dentro de un mismo archivo.

---

# 4. Funciones, rutinas y clases

## Regla 9. Funciones pequeñas con responsabilidad única

Cada función o método debe realizar una sola tarea bien definida.

Cuando un método contenga demasiadas operaciones, se deben extraer partes del código a métodos auxiliares privados.

## Regla 10. Preferencia de parámetros por defecto sobre sobrecarga

Se deben preferir parámetros con valores predeterminados en lugar de crear múltiples sobrecargas innecesarias del mismo método.

## Regla 11. Uso de `switch` o `when` para múltiples condiciones

Cuando una estructura condicional evalúe tres o más opciones sobre un mismo valor o estado, se debe utilizar:

- `when` en Kotlin.
- `switch` en Java.

Se debe evitar una cadena larga de `if - else if - else`.

## Regla 12. Bajo acoplamiento mediante inyección de dependencias

Las clases no deben crear directamente dependencias complejas como:

- Repositorios.
- Conexiones a bases de datos.
- Servicios externos.

Estas dependencias deben recibirse mediante el constructor o mediante interfaces.

---

# 5. Excepciones

## Regla 13. Prohibición de captura genérica de excepciones

Se debe evitar capturar excepciones de forma genérica, por ejemplo:

```java
catch (Exception e)
```

Este tipo de captura puede ocultar errores inesperados y dificultar la depuración.

Se deben capturar excepciones específicas cuando sea necesario.

---

# 6. Declaraciones, inicializaciones e instancias

## Regla 14. Inmutabilidad por defecto

Las variables y propiedades deben declararse como inmutables siempre que sea posible.

En Kotlin se debe preferir:

```kotlin
val
```

sobre:

```kotlin
var
```

En Java se debe preferir:

```java
final
```

cuando el valor no necesite ser reasignado.

## Regla 15. Ámbito local y encapsulación de variables

Las variables utilizadas solamente dentro de una función deben declararse dentro de esa misma función.

Se debe evitar el uso innecesario de:

- Variables globales.
- Atributos compartidos.
- Estado mutable fuera del ámbito donde realmente se necesita.

---

# 7. Seguridad

## Regla 16. Prohibición de credenciales y secretos en el código fuente

Está prohibido escribir directamente en el código fuente información sensible como:

- Contraseñas.
- Tokens de autenticación.
- Claves de API.
- Cadenas de conexión.
- Credenciales.
- Otros datos sensibles.

---

# 8. Documentación y comentarios

## Regla 17. Comentarios de intención y propósito

Los comentarios deben explicar el motivo o la intención detrás de una decisión, algoritmo o regla de negocio compleja.

No deben limitarse a repetir lo que el código ya expresa claramente.

## Regla 18. Prohibición de comentarios obvios o redundantes

No se deben agregar comentarios que describan operaciones triviales o evidentes.

Ejemplo que debe evitarse:

```java
// Incrementar contador
contador++;
```

El código debe ser lo suficientemente claro para que este tipo de comentarios no sean necesarios.

---

# Resumen de reglas

1. Usar convenciones correctas de capitalización.
2. Utilizar nombres descriptivos.
3. Mantener espacios en blanco consistentes.
4. Colocar llaves de apertura en la misma línea.
5. Dividir firmas largas en varias líneas.
6. Organizar el proyecto por capas.
7. Hacer coincidir el nombre del archivo con su clase principal.
8. Mantener una sola responsabilidad por archivo.
9. Crear métodos pequeños con una sola responsabilidad.
10. Preferir parámetros por defecto sobre sobrecargas innecesarias.
11. Usar `switch` o `when` para múltiples condiciones.
12. Reducir el acoplamiento mediante inyección de dependencias.
13. Evitar capturas genéricas de excepciones.
14. Preferir variables inmutables.
15. Mantener las variables en el ámbito más pequeño posible.
16. No guardar credenciales o secretos en el código fuente.
17. Escribir comentarios que expliquen intención.
18. Evitar comentarios obvios o redundantes.
