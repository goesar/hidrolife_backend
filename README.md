# HidroLife 🌱

HidroLife es un sistema de monitoreo para cultivos hidropónicos desarrollado como proyecto académico en el SENA dentro del programa de Análisis y Desarrollo de Software.

El sistema permite monitorear variables importantes del cultivo como pH, TDS, temperatura y humedad mediante sensores conectados a un microcontrolador ESP32. Los datos son enviados a una aplicación web desarrollada con Java y Spring Boot para su almacenamiento, visualización y gestión.

---

## Características principales

- Monitoreo de sensores en tiempo real.
- Registro de lecturas de pH, TDS, temperatura y humedad.
- Gestión de cultivos y actividades realizadas en el sistema.
- Visualización de datos para facilitar el control del cultivo.
- Sistema de alertas cuando los valores están fuera de los rangos establecidos.

---

## Tecnologías utilizadas

### Backend
- Java
- Spring Boot
- Spring Data JPA
- Spring Security

### Base de datos
- MySQL

### Frontend
- HTML
- CSS
- Bootstrap
- Thymeleaf

### Hardware / IoT
- ESP32
- Sensores de pH
- Sensor TDS
- Sensor de temperatura y humedad

---

## Arquitectura del proyecto

El sistema sigue una arquitectura en capas para separar responsabilidades dentro de la aplicación:

Controller
Service
Repository
DTO
Entity
Config

Esto permite mantener el código organizado y facilita su mantenimiento y escalabilidad.

## Funcionamiento general

1. Los sensores conectados al ESP32 miden variables del cultivo.
2. El microcontrolador envía los datos al sistema web.
3. El backend desarrollado con Spring Boot procesa y almacena la información en la base de datos.
4. La aplicación web permite visualizar las lecturas, gestionar cultivos y registrar actividades.

---

## Objetivo del proyecto

El objetivo de HidroLife es facilitar el monitoreo y control de cultivos hidropónicos mediante el uso de tecnologías de software e IoT, permitiendo automatizar procesos y mejorar la toma de decisiones en el cultivo.

---

## Autor

Esteban Gómez Arango  
Estudiante de Análisis y Desarrollo de Software  
SENA
