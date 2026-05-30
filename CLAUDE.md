# CLAUDE.md — Apunta ToDo Backend

Proyecto integrador del 2do semestre — Técnico Laboral en Desarrollo de Software, CESDE.
Aplicación de consola en Java para gestión de listas de compras del hogar.

---

## Descripción del proyecto

**Apunta ToDo** permite a usuarios crear y gestionar listas de compra con productos organizados por categorías.
Tiene dos roles: Administrador (gestión completa) y Usuario (gestión de sus propias listas).

### Entidades del dominio

- `Persona` — clase base (a implementar): id, nombre, apellido, email, contraseña
- `Usuario extends Persona` — teléfono, rolId, estado
- `Rol` — id, nombre, descripción
- `Producto` — id, nombre, unidadMedida, categoriaId, estado
- `CategoriaProducto` — id, nombre, descripción, estado
- `ListaCompra` — id, nombre, fecha, usuarioId, estado
- `DetalleLista` — id, productoId, cantidad, listaId

### Base de datos

Motor: **MySQL 8.x** (migrado de SQL Server Express).
Nombre de la base de datos: `bd_apunta_todo`.
Conexión: `jdbc:mysql://localhost:3306/bd_apunta_todo?useSSL=false&serverTimezone=UTC`
Usuario: `root` — contraseña configurada en `DataBaseConnectionMySql.PASSWORD`.
Script: `db/schema.sql` — contiene tablas, llaves foráneas y datos de prueba.

### Mapeo dominio → tabla

| Clase Java       | Tabla MySQL       | Columnas clave añadidas vs SQL Server original |
|------------------|-------------------|------------------------------------------------|
| `Rol`            | `tbl_roles`       | —                                              |
| `Usuario`        | `tbl_usuarios`    | `contrasena` (sin tilde; era `contraseña`)     |
| `CategoriaProducto` | `tbl_categorias` | —                                           |
| `Producto`       | `tbl_productos`   | —                                              |
| `ListaCompra`    | `tbl_listas`      | `estado` VARCHAR(20) **nuevo**                 |
| `DetalleLista`   | `tbl_items`       | `comprado` TINYINT(1) **nuevo**                |

---

## Estructura de paquetes

```
src/
├── Main.java
└── application/
    ├── configuration/     — Config.java (Factory + ensamblado de dependencias)
    ├── domain/            — entidades puras, enums, validaciones
    │   ├── enums/         — enums de estado y tipo
    │   └── validaciones/  — ValidationRules con Predicate<T>
    ├── persistence/       — acceso a datos
    │   ├── database/      — DataBaseConnectionMySql (Singleton)
    │   ├── mapper/        — RowMapper<T> interface + implementaciones
    │   └── repositorio/   — adaptadores MySQL (*AdapterMySql)
    ├── service/           — lógica de negocio
    │   ├── inputs/        — interfaces de servicio (puertos de entrada)
    │   └── outputs/       — interfaces de repositorio (puertos de salida)
    ├── userinterface/     — MenuApp, SesionUsuarioMenu
    ├── vista/             — vistas por entidad
    └── util/              — FormValidationUtil, FormRuleValidator
```

---

## Checklist de requisitos del proyecto final

Cada ítem debe estar implementado y demostrable en el código.

### 1. POO — Pilares fundamentales
- [ ] **Herencia**: `Persona` (base) → `Usuario` la extiende con atributos adicionales
- [ ] **Encapsulamiento**: todos los atributos `private` con getters/setters
- [ ] **Abstracción**: interfaces de servicio y de repositorio como contratos
- [ ] **Polimorfismo**: implementaciones concretas sustituyen a sus interfaces

### 2. Principios SOLID
- [ ] **S** — cada clase tiene una única responsabilidad (Vista, Servicio, Repositorio separados)
- [ ] **O** — nuevas funcionalidades mediante nuevas implementaciones, no modificando interfaces
- [ ] **L** — `Usuario` puede sustituir a `Persona` sin romper comportamiento
- [ ] **I** — interfaces de servicio separadas por entidad (no una sola interfaz gigante)
- [ ] **D** — servicios y vistas dependen de interfaces, nunca de implementaciones concretas

### 3. Arquitectura por capas — Puertos y Adaptadores (Hexagonal)
- [ ] `domain/` — solo entidades, enums y reglas de validación, sin dependencias externas
- [ ] `service/inputs/` — interfaces de servicio (puertos de entrada)
- [ ] `service/outputs/` — interfaces de repositorio (puertos de salida)
- [ ] `persistence/repositorio/` — adaptadores MySQL que implementan los ports
- [ ] `vista/` y `userinterface/` — capa de presentación que solo llama a servicios
- [ ] Ninguna vista llama directamente a un repositorio

### 4. JDBC + Patrón Repository / DAO
- [ ] `DataBaseConnectionMySql` con `DriverManager` y `PreparedStatement`
- [ ] Un repositorio adapter por entidad (`*AdapterMySql`) que implementa su port
- [ ] CRUD completo (crear, leer todos, leer por ID, actualizar, eliminar) en al menos 3 entidades
- [ ] Queries con `JOIN` para relaciones (ej: `DetalleLista` trae `Producto` completo)
- [ ] Script SQL `schema.sql` en la raíz con creación de todas las tablas y llaves foráneas

### 5. Patrón Factory
- [ ] `Config.java` instancia todos los repositorios, servicios y vistas
- [ ] Método `crearMenuApp()` que retorna el `MenuApp` completamente ensamblado
- [ ] Ninguna otra clase usa `new` para crear sus dependencias

### 6. Patrón Singleton
- [ ] `DataBaseConnectionMySql` con método estático `getInstance()`
- [ ] Constructor privado
- [ ] Instancia única compartida por todos los repositorios

### 7. Inyección de dependencias (manual)
- [ ] Servicios reciben su repositorio port por constructor
- [ ] Vistas reciben su servicio por constructor
- [ ] `Config.java` es el único lugar donde se hace `new` de repositorios y servicios

### 8. Patrón Row Mapper
- [ ] Interfaz genérica `RowMapper<T>` con método `T mapRow(ResultSet rs)`
- [ ] Implementación por entidad: `UsuarioRowMapper`, `ProductoRowMapper`, `CategoriaRowMapper`, `ListaCompraRowMapper`, `DetalleListaRowMapper`
- [ ] Los repositorios usan el mapper para convertir `ResultSet` a objetos de dominio

### 9. Excepciones — try/catch/throws
- [ ] `try/catch (SQLException e)` en todos los métodos de los repositorios MySQL
- [ ] Al menos una excepción personalizada de negocio (ej: `UsuarioNoEncontradoException`, `ListaVaciaException`)
- [ ] `throws` declarado en métodos que propagan excepciones
- [ ] Las excepciones se manejan o reportan al usuario desde la vista, no se ignoran

### 10. Validaciones
- [ ] **De tipo**: `FormValidationUtil` valida que los inputs sean del tipo correcto (int, double, String no vacío)
- [ ] **De negocio**: `ValidationRules` con `Predicate<T>` para reglas como:
  - Email con formato válido (regex)
  - Contraseña con mínimo 8 caracteres
  - Nombre sin números
  - Cantidad mayor a cero
  - Nombre con mínimo 3 caracteres
- [ ] `FormRuleValidator` acepta `Predicate<T>` y mensaje de error para validar con regla específica

### 11. Relaciones entre clases
- [ ] **Herencia**: `Usuario extends Persona`
- [ ] **Composición**: `DetalleLista` contiene referencia a objeto `Producto` (no solo el ID)
- [ ] **Asociación**: `ListaCompra` referencia a objeto `Usuario`
- [ ] **Asociación**: `Producto` referencia a objeto `CategoriaProducto`
- [ ] Los mappers con JOIN deben hidratar los objetos relacionados al leer de DB

### 12. Colecciones
- [ ] `List<T>` como tipo de retorno en métodos `getAll*()`
- [ ] `Optional<T>` como tipo de retorno en métodos `get*ById()`
- [ ] Al menos un uso explícito de `ArrayList` en memoria o como resultado de queries

### 13. Optional y programación funcional
- [ ] Retornar `Optional<T>` en búsquedas por ID en servicios y repositorios
- [ ] Uso de `Optional.of()`, `Optional.empty()`, `isPresent()`, `orElseThrow()`
- [ ] `Predicate<T>` en `ValidationRules` para reglas de validación reutilizables

### 14. Enums
- [ ] `RolEnum` — ADMINISTRADOR, USUARIO
- [ ] `EstadoUsuario` — ACTIVO, INACTIVO
- [ ] `EstadoProducto` — ACTIVO, INACTIVO, DESCONTINUADO
- [ ] `EstadoCategoria` — ACTIVA, INACTIVA
- [ ] `EstadoLista` — ABIERTA, CERRADA, ARCHIVADA
- [ ] Los enums se usan en las entidades y en los selectores de menú

### 15. KISS / DRY
- [ ] `FormValidationUtil` centraliza toda lectura de inputs desde consola
- [ ] No hay bloques `try/catch` duplicados en múltiples repositorios — extraer método auxiliar si es necesario
- [ ] Los selectores de enum (`*Selector`) evitan repetir el switch en cada vista
- [ ] Métodos cortos y con una sola responsabilidad

---

## Convenciones de código

- Idioma: **español** para nombres de clases, métodos, variables y mensajes al usuario
- Excepción: palabras clave técnicas del patrón en inglés (`RowMapper`, `Port`, `Adapter`, `Impl`)
- Clases de dominio: sustantivos simples (`Usuario`, `Producto`, `ListaCompra`)
- Interfaces de servicio: sufijo `Servicio` (`UsuarioServicio`)
- Implementaciones de servicio: sufijo `ServicioImpl` (`UsuarioServicioImpl`)
- Interfaces de repositorio: sufijo `RepositorioPort` (`UsuarioRepositorioPort`)
- Adaptadores MySQL: sufijo `AdapterMySql` (`UsuarioAdapterMySql`)
- Mappers: sufijo `RowMapper` (`UsuarioRowMapper`)
- Enums: en `domain/enums/`, nombre en `PascalCase`, valores en `UPPER_SNAKE_CASE`

---

## Reglas de implementación

1. **Nunca** llamar a un repositorio directamente desde una vista — siempre pasar por el servicio.
2. **Nunca** instanciar dependencias con `new` dentro de servicios o vistas — inyectarlas por constructor.
3. **Nunca** dejar un `catch` vacío — siempre imprimir o relanzar la excepción.
4. **Nunca** usar `System.exit()` dentro de vistas — el flujo de salida se maneja en `MenuApp`.
5. Los repositorios en memoria (`*Repositorio.java`) se mantienen solo como respaldo o referencia, no como implementación activa.
6. Toda lectura de input del usuario pasa por `FormValidationUtil` o `FormRuleValidator`.
7. El script `schema.sql` debe reflejar exactamente la estructura de tablas que usan los adaptadores.

---

## Estado actual del proyecto

### Implementado
- Arquitectura hexagonal (capas, puertos de entrada y salida)
- Patrón Factory en `Config.java`
- Inyección de dependencias por constructor
- Repositorios en memoria con `ArrayList` para todas las entidades
- Vistas por entidad con menús de consola
- `FormValidationUtil` para lectura de inputs básicos
- Sesión de usuario con login por email y contraseña

### Pendiente (por orden de prioridad)
1. Base de datos MySQL — `DataBaseConnectionMySql` (Singleton) + script `schema.sql`
2. Adaptadores MySQL — uno por entidad con CRUD completo
3. Row Mappers — uno por entidad
4. Enums de estado y tipo en `domain/enums/`
5. Herencia — clase `Persona` base para `Usuario`
6. Excepciones personalizadas de negocio
7. Validaciones de negocio con `Predicate<T>` en `ValidationRules`
8. Relaciones reales entre objetos en el dominio (en lugar de solo IDs)
9. `Optional<T>` en retornos de búsqueda por ID
