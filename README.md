# ConvsersorMonedaApp

Este proyecto es una aplicación de consola en Java que permite realizar conversiones entre diferentes monedas utilizando tasas de cambio actualizadas desde una API externa. También guarda un historial de conversiones con marca de tiempo para mayor trazabilidad.

---

## Funcionalidades

- Conversión entre distintas monedas:
  - USD ⇄ COP
  - MXN ⇄ COP
  - EUR ⇄ COP
- Consulta de tasas de cambio en tiempo real mediante API.
- Historial de conversiones con fecha y hora.
- Validación de entrada para evitar errores de usuario.

---

## Estructura del Proyecto

- `ConversorApp.java` – Clase principal. Contiene el menú interactivo y control de flujo.
- `ConsultaDivisa.java` – Maneja la conexión con la API para obtener tasas de cambio.
- `Calclulo.java` – Lógica para calcular la conversión de divisas.

---

## Requisitos

- **Java 17 o superior** (por el uso de _Text Blocks_ y `java.time`)
- Conexión a Internet (para consultar la API de tasas de cambio)
- Dependencias:
  - Ninguna externa si la API se consume mediante clases estándar de Java (`HttpURLConnection`).

---
