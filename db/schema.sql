-- =============================================================
-- schema.sql — bd_apunta_todo
-- Motor: MySQL 8.x
-- Proyecto: Apunta ToDo — CESDE Backend I
-- =============================================================

CREATE DATABASE IF NOT EXISTS bd_apunta_todo
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_unicode_ci;

USE bd_apunta_todo;

-- -------------------------------------------------------------
-- ROLES
-- -------------------------------------------------------------
CREATE TABLE IF NOT EXISTS tbl_roles (
    id_rol      INT          NOT NULL AUTO_INCREMENT,
    nombre      VARCHAR(50)  NOT NULL,
    descripcion VARCHAR(100) NULL,
    PRIMARY KEY (id_rol),
    UNIQUE KEY uk_rol_nombre (nombre)
);

-- -------------------------------------------------------------
-- USUARIOS
-- Hereda conceptualmente de Persona (en Java).
-- En la BD solo existe una tabla plana.
-- contrasena: sin tilde para evitar problemas de encoding en JDBC
-- -------------------------------------------------------------
CREATE TABLE IF NOT EXISTS tbl_usuarios (
    id_usuario INT          NOT NULL AUTO_INCREMENT,
    id_rol     INT          NOT NULL,
    nombre     VARCHAR(100) NOT NULL,
    apellido   VARCHAR(100) NOT NULL,
    telefono   VARCHAR(12)  NOT NULL,
    email      VARCHAR(100) NOT NULL,
    contrasena VARCHAR(100) NOT NULL,
    PRIMARY KEY (id_usuario),
    UNIQUE KEY uk_usuario_email (email),
    CONSTRAINT fk_usuarios_rol
        FOREIGN KEY (id_rol) REFERENCES tbl_roles (id_rol)
);

-- -------------------------------------------------------------
-- CATEGORIAS DE PRODUCTO
-- -------------------------------------------------------------
CREATE TABLE IF NOT EXISTS tbl_categorias (
    id_categoria INT          NOT NULL AUTO_INCREMENT,
    nombre       VARCHAR(100) NOT NULL,
    descripcion  VARCHAR(100) NULL,
    PRIMARY KEY (id_categoria)
);

-- -------------------------------------------------------------
-- PRODUCTOS
-- -------------------------------------------------------------
CREATE TABLE IF NOT EXISTS tbl_productos (
    id_producto      INT          NOT NULL AUTO_INCREMENT,
    nombre           VARCHAR(100) NOT NULL,
    unidad_de_medida VARCHAR(50)  NOT NULL,
    id_categoria     INT          NOT NULL,
    PRIMARY KEY (id_producto),
    CONSTRAINT fk_productos_categoria
        FOREIGN KEY (id_categoria) REFERENCES tbl_categorias (id_categoria)
);

-- -------------------------------------------------------------
-- LISTAS DE COMPRA
-- estado: FAVORITA | ABIERTA | CERRADA | ARCHIVADA
--   Se guarda como texto para que coincida exactamente con el enum Java.
--   FAVORITA aparece primero al ordenar por el ordinal del enum en Java.
-- fecha_creacion: corregido el typo del script original (fecha_creccion)
-- -------------------------------------------------------------
CREATE TABLE IF NOT EXISTS tbl_listas (
    id_lista       INT          NOT NULL AUTO_INCREMENT,
    id_usuario     INT          NOT NULL,
    nombre         VARCHAR(150) NOT NULL,
    fecha_creacion DATE         NOT NULL,
    estado         VARCHAR(20)  NOT NULL DEFAULT 'ABIERTA',
    PRIMARY KEY (id_lista),
    CONSTRAINT fk_listas_usuario
        FOREIGN KEY (id_usuario) REFERENCES tbl_usuarios (id_usuario)
);

-- -------------------------------------------------------------
-- ITEMS (DetalleLista en Java)
-- comprado: 0 = pendiente, 1 = ya comprado  (boolean en Java)
-- cantidad: DECIMAL para permitir fracciones (ej: 0.5 kg)
-- -------------------------------------------------------------
CREATE TABLE IF NOT EXISTS tbl_items (
    id_item     INT            NOT NULL AUTO_INCREMENT,
    id_lista    INT            NOT NULL,
    id_producto INT            NOT NULL,
    cantidad    DECIMAL(10, 2) NOT NULL,
    comprado    TINYINT(1)     NOT NULL DEFAULT 0,
    PRIMARY KEY (id_item),
    CONSTRAINT fk_items_lista
        FOREIGN KEY (id_lista)    REFERENCES tbl_listas   (id_lista),
    CONSTRAINT fk_items_producto
        FOREIGN KEY (id_producto) REFERENCES tbl_productos (id_producto)
);

-- =============================================================
-- DATOS DE PRUEBA
-- =============================================================

INSERT INTO tbl_roles (nombre, descripcion) VALUES
    ('Administrador', 'Rol con todos los permisos'),
    ('Usuario',       'Rol con permisos para listas');

INSERT INTO tbl_usuarios (id_rol, nombre, apellido, telefono, email, contrasena) VALUES
    (1, 'Admin',  'Sistema', '000000000',  'admin@email.com',  '1234'),
    (2, 'Larysa', 'Guerra',  '333123456',  'larysa@email.com', '1234');

INSERT INTO tbl_categorias (nombre, descripcion) VALUES
    ('Lacteos', 'Productos derivados de la leche'),
    ('Frutas',  'Comestibles obtenidos de plantas'),
    ('Aseo',    'Productos de limpieza del hogar');

INSERT INTO tbl_productos (nombre, unidad_de_medida, id_categoria) VALUES
    ('Leche',    'Litros', 1),
    ('Manzana',  'Kilos',  2),
    ('Jabon',    'Unidad', 3);

INSERT INTO tbl_listas (id_usuario, nombre, fecha_creacion, estado) VALUES
    (2, 'Mercado semanal', '2026-02-11', 'FAVORITA'),
    (2, 'Fruver',          '2026-03-02', 'CERRADA'),
    (2, 'Aseo',            '2026-04-15', 'ABIERTA');

INSERT INTO tbl_items (id_lista, id_producto, cantidad, comprado) VALUES
    (1, 1, 2.00, 0),
    (1, 2, 3.00, 1),
    (3, 3, 1.00, 0);
