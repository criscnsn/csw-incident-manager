# Mini Gestor de Incidencias

## Descripción

Este proyecto consiste en una aplicación desarrollada en Java para registrar y dar seguimiento a reportes de fallas en aulas y laboratorios de una institución universitaria.

El sistema permite registrar incidencias relacionadas con equipos o elementos que presenten algún problema, almacenando información como el equipo, ubicación, descripción, prioridad y estado.

Los datos se almacenan en memoria, por lo que no es necesario utilizar una base de datos.

---

## Objetivo

Desarrollar una aplicación funcional aplicando un estándar de programación definido previamente por el equipo.

Además de lograr que el programa funcione correctamente, se busca mantener un código organizado, entendible y dividido por responsabilidades.

---

## Funcionalidades

El sistema permite realizar las siguientes operaciones:

- Registrar una incidencia.
- Generar un identificador único para cada incidencia.
- Listar todas las incidencias registradas.
- Buscar una incidencia mediante su identificador.
- Cambiar el estado de una incidencia.
- Validar que los datos obligatorios no estén vacíos.

Los estados de una incidencia siguen el siguiente orden:

Pendiente -> En proceso -> Resuelta

Las prioridades disponibles son:

- Baja
- Media
- Alta

---

## Ejemplo de incidencia

```text
ID: INC-001
Equipo: Computadora
Ubicación: Laboratorio de cómputo
Descripción: No inicia Windows
Prioridad: Alta
Estado: Pendiente

