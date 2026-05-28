# Apunta ToDo

**Apunta ToDo** es una aplicación de consola en Java para gestionar listas de compras del hogar. Permite crear listas, agregar productos organizados por categorías, marcar ítems como comprados y administrar usuarios con roles diferenciados.

Desarrollada como **Proyecto Integrador del 2do semestre** del programa *Técnico Laboral como Asistente en Desarrollo de Software – CESDE*.

---

## Requisitos previos

| Herramienta | Versión mínima | Verificar con |
|---|---|---|
| Java JDK | 21 | `java -version` |
| Apache Maven | 3.8+ | `mvn -version` |
| MySQL Server | 8.x | MySQL Workbench o `mysql --version` |
| Git | cualquiera | `git --version` |

---

## Instalación y ejecución

### 1. Clonar el repositorio

```bash
git clone [<URL-del-repositorio>](https://github.com/larysaguerra/apunta-todo-back-end.git)
cd apunta-todo-back-end
```

### 2. Crear la base de datos

1. Abre **MySQL Workbench** y conéctate a tu servidor local
2. Ve a **File → Open SQL Script** y selecciona el archivo `db/schema.sql`
3. Ejecuta todo el script con **Ctrl+Shift+Enter**
4. Verifica que aparezca la base de datos `bd_apunta_todo` en el panel izquierdo

El script crea las 6 tablas, las llaves foráneas y carga datos de prueba automáticamente.

### 3. Configurar la contraseña de MySQL

Abre `src/application/persistence/database/DataBaseConnectionMySql.java` y reemplaza la contraseña con la de tu usuario `root` local:

```java
private static final String PASSWORD = "tu_contraseña_aqui";
```

### 4. Compilar

```bash
mvn compile
```

Debe terminar con `BUILD SUCCESS`.

### 5. Ejecutar

```bash
mvn exec:java -Dexec.mainClass="Main"
```

O desde un IDE (IntelliJ IDEA, Eclipse): clic derecho en `src/Main.java` → **Run**.

### Usuarios de prueba

El script SQL incluye dos usuarios listos para iniciar sesión:

| Email | Contraseña | Rol |
|---|---|---|
| `admin@email.com` | `1234` | Admin — acceso completo |
| `larysa@email.com` | `1234` | Usuario — menú personal de listas |

---

## Estructura del proyecto

```
src/
└── application/
    ├── configuration/       Config.java — Factory, ensambla todas las dependencias
    ├── domain/              Entidades puras del negocio
    │   ├── enums/           EstadoLista (FAVORITA, ABIERTA, CERRADA, ARCHIVADA)
    │   └── validaciones/    ValidationRules — reglas con Predicate<T>
    ├── persistence/
    │   ├── database/        DataBaseConnectionMySql — Singleton JDBC
    │   ├── mapper/          Interfaz RowMapper<T> + 6 implementaciones
    │   └── repositorio/     6 AdapterMySql — acceso real a MySQL
    ├── service/
    │   ├── outputs/         Interfaces de servicio (puertos de entrada)
    │   ├── ports/           Interfaces de repositorio (puertos de salida)
    │   └── *ServicioImpl    Lógica de negocio
    ├── userinterface/       MenuApp, SesionUsuarioMenu
    ├── util/                FormValidationUtil
    └── vista/               6 vistas de consola (una por entidad)
db/
└── schema.sql               Script MySQL — tablas, FK y datos de prueba
```

---

## Conceptos del checklist implementados

### POO — Pilares fundamentales

**Herencia**
`Persona` es la clase abstracta base. `Usuario extends Persona` y hereda `nombre`, `apellido`, `telefono` y `correo`. Esto evita repetir atributos comunes si en el futuro se agrega otro tipo de persona al sistema.

**Encapsulamiento**
Todos los atributos de las entidades son `private`. El acceso se hace únicamente a través de getters y setters. Ninguna clase modifica el estado interno de otra directamente.

**Abstracción**
Las interfaces `RolServicio`, `ProductoServicio`, `ListaCompraServicio`, etc., definen contratos de lo que se puede hacer sin exponer cómo. Las vistas solo conocen la interfaz, no la implementación.

**Polimorfismo**
Los servicios trabajan con las interfaces de repositorio (`RolRepositorioPort`, `ProductoRepositorioPort`, etc.). Si mañana se cambia MySQL por otro motor, solo se cambia el adaptador; el servicio y la vista no se tocan.

---

### Principios SOLID

| Principio | Aplicación |
|---|---|
| **S** — Responsabilidad única | Vista solo muestra, Servicio solo lógica, Adaptador solo BD |
| **O** — Abierto/Cerrado | Para agregar un nuevo motor de BD se crea un nuevo adaptador sin modificar los servicios |
| **L** — Sustitución de Liskov | `Usuario` puede usarse en cualquier lugar donde se espera una `Persona` |
| **I** — Segregación de interfaces | Hay una interfaz de servicio por entidad; ninguna tiene métodos que no le correspondan |
| **D** — Inversión de dependencias | Los servicios dependen de interfaces (`*RepositorioPort`), nunca de `*AdapterMySql` directamente |

---

### Arquitectura Hexagonal (Ports & Adapters)

El dominio del negocio no sabe nada de MySQL, ni de la consola. Las capas se comunican solo a través de interfaces:

```
Vista  →  [outputs/]ServicioInterface  →  ServicioImpl  →  [ports/]RepositorioPort  →  AdapterMySql  →  MySQL
```

- `service/outputs/` — interfaces que definen qué puede pedirle la vista al servicio
- `service/ports/` — interfaces que definen qué le pide el servicio al repositorio
- `persistence/repositorio/` — implementaciones concretas para MySQL
- Ninguna vista llama directamente a un repositorio

---

### Patrón Factory

`Config.java` es el único lugar de todo el proyecto donde se usa `new` para crear dependencias:

```java
RolAdapterMySql rolRepositorio = new RolAdapterMySql();
RolServicioImpl rolServicio = new RolServicioImpl(rolRepositorio);
RolVista rolVista = new RolVista(rolServicio);
```

Todos los servicios y vistas reciben sus dependencias por constructor. Si se cambia una implementación, solo se edita `Config.java`.

---

### Patrón Singleton

`DataBaseConnectionMySql` garantiza una única conexión a MySQL en toda la aplicación:

```java
public static DataBaseConnectionMySql getInstance() throws SQLException {
    if (instancia == null || instancia.conexion.isClosed()) {
        instancia = new DataBaseConnectionMySql();
    }
    return instancia;
}
```

- Constructor `private` — nadie puede crear instancias directamente
- `getInstance()` verifica si la conexión existe y está abierta antes de reutilizarla

---

### Patrón RowMapper

Interfaz genérica que convierte una fila de `ResultSet` en un objeto de dominio:

```java
public interface RowMapper<T> {
    T mapRow(ResultSet rs) throws SQLException;
}
```

Hay una implementación por entidad: `RolRowMapper`, `CategoriaRowMapper`, `UsuarioRowMapper`, `ProductoRowMapper`, `ListaCompraRowMapper`, `DetalleListaRowMapper`. Cada adaptador delega en su mapper para separar la lectura del SQL de la construcción del objeto.

---

### Inyección de dependencias (manual)

Ningún servicio ni vista crea sus propias dependencias. Todo llega por constructor:

```java
public class ProductoServicioImpl implements ProductoServicio {
    private final ProductoRepositorioPort repositorio;

    public ProductoServicioImpl(ProductoRepositorioPort repositorio) {
        this.repositorio = repositorio;
    }
}
```

Esto hace que cada clase sea fácil de probar y reemplazar.

---

### JDBC con PreparedStatement

Todos los adaptadores usan `PreparedStatement` para prevenir SQL injection y `try-with-resources` para cerrar recursos automáticamente:

```java
try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
    ps.setInt(1, id);
    try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
            return Optional.of(mapper.mapRow(rs));
        }
    }
}
```

Las consultas de entidades relacionadas usan `INNER JOIN` para traer el objeto completo en una sola query.

---

### Relaciones reales entre objetos

Las entidades se asocian con objetos completos, no solo con IDs:

| Entidad | Relación |
|---|---|
| `Producto` | contiene un objeto `CategoriaProducto` completo |
| `DetalleLista` | contiene un objeto `Producto` completo |
| `ListaCompra` | contiene un objeto `Usuario` completo |
| `Usuario` | contiene un objeto `Rol` completo |

Esto permite acceder a `lista.getUsuario().getNombre()` sin hacer una segunda consulta.

---

### `Optional<T>`

Todos los métodos `buscarPorId()` y `leerPorId()` retornan `Optional<T>` en lugar de `null`. Esto obliga a que quien llama verifique si el resultado existe antes de usarlo:

```java
Optional<Producto> productoOpt = productoServicio.buscarPorId(id);
if (productoOpt.isEmpty()) {
    System.out.println("No existe producto con ese ID.");
    break;
}
Producto producto = productoOpt.get();
```

Aplica en los 6 ports, 6 service interfaces, 6 impls y 6 adaptadores.

---

### `Predicate<T>` — Validaciones de negocio

La clase `ValidationRules` en `domain/validaciones/` define reglas de validación como constantes funcionales reutilizables:

| Constante | Criterio |
|---|---|
| `NOMBRE_VALIDO` | No vacío, solo letras y espacios |
| `EMAIL_VALIDO` | Formato `usuario@dominio.com` |
| `CONTRASENA_VALIDA` | Mínimo 8 caracteres |
| `CANTIDAD_VALIDA` | Entero mayor que 0 |
| `FECHA_VALIDA` | Formato `yyyy-MM-dd` |

Uso en las vistas:

```java
if (!ValidationRules.EMAIL_VALIDO.test(correo)) {
    System.out.println("Correo invalido: debe tener formato usuario@dominio.com");
    break;
}
```

---

### Enumeraciones (`enum`)

`EstadoLista` controla el ciclo de vida de cada lista de compra y permite ordenarlas por prioridad:

```
FAVORITA → aparece primero en el listado
ABIERTA  → lista activa
CERRADA  → compra realizada
ARCHIVADA → inactiva
```

El valor se guarda en MySQL como `VARCHAR(20)` y se convierte con `EstadoLista.valueOf(rs.getString("estado"))`.

---

### Colecciones, streams y lambdas

- `List<T>` como tipo de retorno en todos los métodos `obtenerTodos()`
- `ArrayList<T>` como implementación concreta en los adaptadores
- Ordenamiento funcional en `ListaCompraServicioImpl`:

```java
return repositorio.obtenerTodos().stream()
    .sorted(Comparator.comparing(ListaCompra::getEstado))
    .collect(Collectors.toList());
```

- `forEach` con lambdas en todas las vistas para mostrar listas

---

## Base de datos

| Tabla | Descripción |
|---|---|
| `tbl_roles` | Roles del sistema (Admin, Usuario) |
| `tbl_usuarios` | Cuentas de usuario con contraseña y rol |
| `tbl_categorias` | Categorías de productos |
| `tbl_productos` | Productos con unidad de medida y categoría |
| `tbl_listas` | Listas de compra con estado y fecha |
| `tbl_items` | Ítems de cada lista con cantidad y estado comprado |

Todos los IDs son `AUTO_INCREMENT`. Las llaves foráneas garantizan integridad referencial.

---

## Diagrama de clases

El diagrama completo en formato PlantUML está en [`docs/diagrama-clases.puml`](docs/diagrama-clases.puml).

Para visualizarlo:
- **IntelliJ IDEA**: instala el plugin [PlantUML Integration](https://plugins.jetbrains.com/plugin/7017-plantuml-integration) y abre el archivo `.puml`
- **VS Code**: instala la extensión [PlantUML](https://marketplace.visualstudio.com/items?itemName=jebbs.plantuml)
- **Online**: pega el contenido en [plantuml.com/plantuml](https://www.plantuml.com/plantuml/uml/)

El diagrama incluye los 8 paquetes del proyecto con sus clases, interfaces, relaciones de herencia, implementación y dependencia:

| Paquete (color) | Contenido |
|---|---|
| `domain` (verde) | Entidades, enum `EstadoLista`, `ValidationRules` |
| `service.outputs` (azul) | Interfaces de servicio — contratos de entrada |
| `service.impl` (azul claro) | Implementaciones de lógica de negocio |
| `service.ports` (violeta) | Interfaces de repositorio — contratos de salida |
| `persistence.mapper` (naranja) | `RowMapper<T>` y 6 implementaciones |
| `persistence.repositorio` (rojo) | 6 `*AdapterMySql` |
| `persistence.database` (lila) | `DataBaseConnectionMySql` Singleton |
| `vista` / `userinterface` (verde agua) | Vistas de consola y menús |

---

## Tecnologías

- **Java 21**
- **Apache Maven** — gestión de dependencias y compilación
- **MySQL Connector/J 8.3.0** — driver JDBC
- **MySQL 8.x** — base de datos relacional

---

Proyecto académico — *Técnico Laboral como Asistente en Desarrollo de Software, CESDE*.
