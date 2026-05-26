-- =========================
-- TABLA SALA
-- =========================
CREATE TABLE sala (
    sala_id VARCHAR(50) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    capacidad INT NOT NULL
);

-- =========================
-- TABLA PELICULA
-- =========================
CREATE TABLE pelicula (
    pelicula_id VARCHAR(50) PRIMARY KEY,
    titulo_pelicula VARCHAR(100) NOT NULL UNIQUE,
    duracion DECIMAL(5,2) NOT NULL DEFAULT 0.00,
    clasificacion VARCHAR(20) NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =========================
-- TABLA FUNCION_CINE
-- =========================
CREATE TABLE funcion_cine (
    funcion_cine_id VARCHAR(50) PRIMARY KEY,
    sala_id VARCHAR(50) NOT NULL,
    pelicula_id VARCHAR(50) NOT NULL,
    precio DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    tipo_funcion VARCHAR(20) NOT NULL DEFAULT 'STANDAR',
    estado VARCHAR(20) NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_funcion_sala
        FOREIGN KEY (sala_id) REFERENCES sala(sala_id),

    CONSTRAINT fk_funcion_pelicula
        FOREIGN KEY (pelicula_id) REFERENCES pelicula(pelicula_id)
);
