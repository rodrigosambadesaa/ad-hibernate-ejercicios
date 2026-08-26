# Enunciado original — Ejercicio 6 de Hibernate/JPA

Fuente: `Ejercicio6.pdf` de **Tema 4 — Hibernate** en Google Drive.

Importar la base de datos **Hibernate 1 corregida** y crear un programa usando Hibernate con anotaciones JPA y un menú que permita:

1. **Mostrar un ciclo formativo** a partir de su `idCiclo`: datos del ciclo, módulos impartidos y profesores de cada módulo.
2. **Mostrar un profesor** a partir de su id: datos personales, dirección, módulos que imparte y correos electrónicos.
3. **Mostrar un municipio** a partir de su `idMunicipio`: datos del municipio y direcciones de ese municipio junto con el profesor asociado a cada dirección.
4. Mostrar todos los profesores.
5. Mostrar todos los módulos.
6. **Añadir un profesor**, incluyendo su dirección, correos electrónicos e ids de los módulos que imparte.
7. **Añadir un módulo**, incluyendo los ids de los profesores que lo imparten y el id del ciclo al que pertenece.
8. **Añadir un ciclo** solicitando sus datos y persistirlo.

## Base de datos original

El volcado original asociado al ejercicio se conserva íntegro, comprimido sin modificar su contenido, en [`database/mihibernate.sql.gz`](database/mihibernate.sql.gz).

Para obtener el SQL original:

```bash
gzip -dc database/mihibernate.sql.gz > database/mihibernate.sql
```

Después puede importarse con MySQL/MariaDB, por ejemplo:

```bash
mysql -u root -p < database/mihibernate.sql
```

El SQL crea y selecciona la base de datos `hibernate1`, además de definir sus tablas, relaciones y datos de ejemplo.
