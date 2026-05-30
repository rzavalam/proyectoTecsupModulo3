# Base de Datos

## Creación de Tablas

```sql
CREATE TABLE sala (
    sala_id VARCHAR(50) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    capacidad INT NOT NULL
);

CREATE TABLE pelicula (
    pelicula_id VARCHAR(50) PRIMARY KEY,
    titulo_pelicula VARCHAR(100) NOT NULL UNIQUE,
    duracion DECIMAL(5,2) NOT NULL DEFAULT 0.00,
    clasificacion VARCHAR(20) NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE funcion_cine (
    funcion_cine_id VARCHAR(50) PRIMARY KEY,
    sala_id VARCHAR(50) NOT NULL,
    pelicula_id VARCHAR(50) NOT NULL,
    horario_inicio TIMESTAMP NOT NULL,
    precio DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    tipo_funcion VARCHAR(20) NOT NULL DEFAULT 'STANDARD',
    estado VARCHAR(20) NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_funcion_sala
        FOREIGN KEY (sala_id)
        REFERENCES sala(sala_id),

    CONSTRAINT fk_funcion_pelicula
        FOREIGN KEY (pelicula_id)
        REFERENCES pelicula(pelicula_id)
);
```

---

## Datos Iniciales

### Sala

```sql
INSERT INTO sala (
    sala_id,
    nombre,
    capacidad
) VALUES (
    'SALA-01',
    'Sala Premier',
    120
);
```

### Película

```sql
INSERT INTO pelicula (
    pelicula_id,
    titulo_pelicula,
    duracion,
    clasificacion
) VALUES (
    'PELI-01',
    'Avengers Endgame',
    181.00,
    'PG-13'
);
```

### Función de Cine

```sql
INSERT INTO funcion_cine (
    funcion_cine_id,
    sala_id,
    pelicula_id,
    horario_inicio,
    precio,
    tipo_funcion,
    estado,
    fecha_creacion,
    fecha_actualizacion
) VALUES (
    'FUNCION-01',
    'SALA-01',
    'PELI-01',
    '2026-05-30 20:00:00',
    25.50,
    'STANDARD',
    'ACTIVA',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);
```

---