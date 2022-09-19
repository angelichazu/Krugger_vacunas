# Kruger Vacunas

## Requisitos del proyecto.

- Docker
- JAVA (JDK 11)
- Puertos disponibles: 8090 (Springboot) y 5432 (Postgres)

## Generacion del Proyecto

- El proyecto está basado en Maven, se procede a crear la base de datos en postgresql mediante un script y cuando dicha
  base de datos se encuentre funcionando se procede a levantarse el BACK END. Todo este proceso se ejecuta gracias a la
  utilización de dockers y docker compose.

## Desplegar la aplicación

**Pasos**

1. Se debe clonar el repositorio: https://github.com/angelichazu/Krugger_vacunas.git
2. Con la utilización de la terminal del sistema operativo nos dirigimos a la raíz del proyecto clonado anteriormente y
   ejecutamos el comando del docker compose para ejecutar las imagenes docker

       docker-compose up -d

3. Por último podemos proceder a probar los webs services con Swagger, para ello ingresamos al siguiente enlace:
   http://localhost:8090/api/vacuna/swagger-ui/index.html?configUrl=/api/vacuna/v3/api-docs/swagger-config#/


**NOTA**

Usuario administrador por defecto
        
    Usuario: ADMIN
    Contraseña: Patito.123@

WS Disponibles:
- /empleado/crearEmpleado  (Autenticación: ADMIN)
- /empleado/listarEmpleados  (Autenticación: ADMIN)
- /empleado/actualizarEmpleado/{id}  (Autenticación: USER)
- /empleado/filtrarEmpleados  (Autenticación: ADMIN)
- /empleado/infoEmpleado/{id}  (Autenticación: USER)