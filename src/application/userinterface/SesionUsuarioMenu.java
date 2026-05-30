package application.userinterface;

import application.domain.CategoriaProducto;
import application.domain.DetalleLista;
import application.domain.ListaCompra;
import application.domain.Producto;
import application.domain.Usuario;
import application.domain.enums.EstadoLista;
import application.domain.validaciones.ValidationRules;
import application.service.DetalleListaProductoServicio;
import application.service.inputs.CategoriaServicio;
import application.service.inputs.DesalleListaServicio;
import application.service.inputs.ListaCompraServicio;
import application.service.inputs.ProductoServicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class SesionUsuarioMenu {

    private final ListaCompraServicio listaCompraServicio;
    private final DesalleListaServicio detalleListaServicio;
    private final DetalleListaProductoServicio detalleListaProductoServicio;
    private final ProductoServicio productoServicio;
    private final CategoriaServicio categoriaServicio;
    private final Scanner sc = new Scanner(System.in);

    public SesionUsuarioMenu(
            ListaCompraServicio listaCompraServicio,
            DesalleListaServicio detalleListaServicio,
            DetalleListaProductoServicio detalleListaProductoServicio,
            ProductoServicio productoServicio,
            CategoriaServicio categoriaServicio
    ) {
        this.listaCompraServicio = listaCompraServicio;
        this.detalleListaServicio = detalleListaServicio;
        this.detalleListaProductoServicio = detalleListaProductoServicio;
        this.productoServicio = productoServicio;
        this.categoriaServicio = categoriaServicio;
    }

    public void showMenuForUser(Usuario usuarioLogueado) {
        int opcion;

        do {
            System.out.println("\n=== MENU USUARIO ===");
            System.out.println("Usuario: " + usuarioLogueado.getNombre() + " " + usuarioLogueado.getApellido());
            System.out.println("1. Ver mis listas de compras con detalles");
            System.out.println("2. Crear una nueva lista de compra");
            System.out.println("3. Editar una lista");
            System.out.println("4. Agregar item a una lista");
            System.out.println("5. Editar un detalle de una lista");
            System.out.println("0. Cerrar sesion");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    mostrarListasConDetalles(usuarioLogueado);
                    break;
                case 2:
                    crearLista(usuarioLogueado);
                    break;
                case 3:
                    editarLista(usuarioLogueado);
                    break;
                case 4:
                    agregarItem(usuarioLogueado);
                    break;
                case 5:
                    editarDetalle(usuarioLogueado);
                    break;
                case 0:
                    System.out.println("Sesion cerrada.");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        } while (opcion != 0);
    }

    private void mostrarListasConDetalles(Usuario usuarioLogueado) {
        List<ListaCompra> listasUsuario = obtenerListasPorUsuario(usuarioLogueado.getId());
        if (listasUsuario.isEmpty()) {
            System.out.println("No tienes listas registradas.");
            return;
        }

        for (ListaCompra lista : listasUsuario) {
            System.out.println("\nLista #" + lista.getId() + " - " + lista.getNombre() + " - " + lista.getFecha());
            List<DetalleLista> detalles = obtenerDetallesPorLista(lista.getId());
            if (detalles.isEmpty()) {
                System.out.println("  Sin detalles.");
            } else {
                for (DetalleLista detalle : detalles) {
                    System.out.println("  " + detalleListaProductoServicio.describirDetalle(detalle));
                }
            }
        }
    }

    private void crearLista(Usuario usuarioLogueado) {
        System.out.print("Nombre de la lista: ");
        String nombre = sc.nextLine();
        if (!ValidationRules.NOMBRE_VALIDO.test(nombre)) {
            System.out.println("Nombre invalido: no puede estar vacio ni contener numeros.");
            return;
        }
        System.out.print("Fecha (yyyy-mm-dd): ");
        String fecha = sc.nextLine();
        if (!ValidationRules.FECHA_VALIDA.test(fecha)) {
            System.out.println("Fecha invalida: debe tener formato yyyy-MM-dd (ej: 2025-12-31).");
            return;
        }
        EstadoLista estado = seleccionarEstado();

        listaCompraServicio.crear(new ListaCompra(0, nombre, fecha, usuarioLogueado, estado));
        System.out.println("Lista creada con exito.");
    }

    private EstadoLista seleccionarEstado() {
        System.out.println("Estado de la lista:");
        System.out.println("  1. FAVORITA  (aparece primero)");
        System.out.println("  2. ABIERTA");
        System.out.println("  3. CERRADA");
        System.out.println("  4. ARCHIVADA");
        System.out.print("Seleccione: ");
        int opcion = sc.nextInt();
        sc.nextLine();
        switch (opcion) {
            case 1: return EstadoLista.FAVORITA;
            case 3: return EstadoLista.CERRADA;
            case 4: return EstadoLista.ARCHIVADA;
            default: return EstadoLista.ABIERTA;
        }
    }

    private void editarLista(Usuario usuarioLogueado) {
        List<ListaCompra> listasUsuario = obtenerListasPorUsuario(usuarioLogueado.getId());
        if (listasUsuario.isEmpty()) {
            System.out.println("No tienes listas para editar.");
            return;
        }

        System.out.println("Tus listas:");
        listasUsuario.forEach(l -> System.out.println("  " + l.getId() + " - " + l.getNombre()));
        System.out.print("Ingresa el ID de la lista a editar: ");
        int listaId = sc.nextInt();
        sc.nextLine();

        Optional<ListaCompra> listaOpt = listaCompraServicio.leerPorId(listaId);
        // Verificar que la lista existe y pertenece al usuario logueado
        if (listaOpt.isEmpty() || listaOpt.get().getUsuario().getId() != usuarioLogueado.getId()) {
            System.out.println("No puedes editar esa lista.");
            return;
        }
        ListaCompra lista = listaOpt.get();

        System.out.print("Nuevo nombre: ");
        String nuevoNombre = sc.nextLine();
        System.out.print("Nueva fecha (yyyy-mm-dd): ");
        String nuevaFecha = sc.nextLine();

        // Conserva el mismo usuario y estado; solo actualiza nombre y fecha
        ListaCompra actualizada = new ListaCompra(lista.getId(), nuevoNombre, nuevaFecha, usuarioLogueado, lista.getEstado());
        listaCompraServicio.actualizar(actualizada);
        System.out.println("Lista actualizada con exito.");
    }

    private void agregarItem(Usuario usuarioLogueado) {
        List<ListaCompra> listasUsuario = obtenerListasPorUsuario(usuarioLogueado.getId());
        if (listasUsuario.isEmpty()) {
            System.out.println("No tienes listas a las que agregar items.");
            return;
        }

        System.out.println("Tus listas:");
        listasUsuario.forEach(l -> System.out.println("  " + l.getId() + " - " + l.getNombre()));
        System.out.print("ID de la lista: ");
        int listaId = sc.nextInt();
        sc.nextLine();

        Optional<ListaCompra> listaOpt = listaCompraServicio.leerPorId(listaId);
        if (listaOpt.isEmpty() || listaOpt.get().getUsuario().getId() != usuarioLogueado.getId()) {
            System.out.println("No puedes agregar items a esa lista.");
            return;
        }

        System.out.println("Productos disponibles:");
        productoServicio.obtenerTodos().forEach(p ->
                System.out.println("  " + p.getId() + " - " + p.getNombre()));
        System.out.println("  0. Crear nuevo producto");
        System.out.print("ID producto: ");
        int productoId = sc.nextInt();
        sc.nextLine();

        Producto producto;
        if (productoId == 0) {
            producto = crearNuevoProducto();
            if (producto == null) return;
        } else {
            Optional<Producto> productoOpt = productoServicio.buscarPorId(productoId);
            if (productoOpt.isEmpty()) {
                System.out.println("No existe producto con ese ID.");
                return;
            }
            producto = productoOpt.get();
        }

        System.out.print("Cantidad: ");
        int cantidad = sc.nextInt();
        sc.nextLine();
        if (!ValidationRules.CANTIDAD_VALIDA.test(cantidad)) {
            System.out.println("Cantidad invalida: debe ser mayor que 0.");
            return;
        }

        detalleListaServicio.crear(new DetalleLista(0, producto, cantidad, listaId));
        System.out.println("Item agregado con exito.");
    }

    private void editarDetalle(Usuario usuarioLogueado) {
        List<ListaCompra> listasUsuario = obtenerListasPorUsuario(usuarioLogueado.getId());
        if (listasUsuario.isEmpty()) {
            System.out.println("No tienes listas asociadas.");
            return;
        }

        System.out.println("Tus listas:");
        listasUsuario.forEach(l -> System.out.println("  " + l.getId() + " - " + l.getNombre()));
        System.out.print("Ingresa el ID de la lista del detalle: ");
        int listaId = sc.nextInt();
        sc.nextLine();

        Optional<ListaCompra> listaEditarOpt = listaCompraServicio.leerPorId(listaId);
        if (listaEditarOpt.isEmpty() || listaEditarOpt.get().getUsuario().getId() != usuarioLogueado.getId()) {
            System.out.println("No puedes editar detalles de esa lista.");
            return;
        }

        List<DetalleLista> detalles = obtenerDetallesPorLista(listaId);
        if (detalles.isEmpty()) {
            System.out.println("La lista no tiene detalles para editar.");
            return;
        }

        for (DetalleLista detalle : detalles) {
            System.out.println(detalleListaProductoServicio.describirDetalle(detalle));
        }

        System.out.print("Ingresa el ID del detalle a editar: ");
        int detalleId = sc.nextInt();
        sc.nextLine();

        Optional<DetalleLista> detalleOpt = detalleListaServicio.leerPorId(detalleId);
        if (detalleOpt.isEmpty() || detalleOpt.get().getListaId() != listaId) {
            System.out.println("Ese detalle no pertenece a la lista.");
            return;
        }
        DetalleLista detalle = detalleOpt.get();

        // Mostrar productos disponibles para elegir
        System.out.println("Productos disponibles:");
        productoServicio.obtenerTodos().forEach(p ->
                System.out.println("  " + p.getId() + " - " + p.getNombre()));
        System.out.println("  0. Crear nuevo producto");
        System.out.print("Nuevo ID producto: ");
        int nuevoProductoId = sc.nextInt();
        sc.nextLine();

        Producto nuevoProducto;
        if (nuevoProductoId == 0) {
            nuevoProducto = crearNuevoProducto();
            if (nuevoProducto == null) return;
        } else {
            Optional<Producto> nuevoProductoOpt = productoServicio.buscarPorId(nuevoProductoId);
            if (nuevoProductoOpt.isEmpty()) {
                System.out.println("No existe producto con ese ID.");
                return;
            }
            nuevoProducto = nuevoProductoOpt.get();
        }

        System.out.print("Nueva cantidad: ");
        int nuevaCantidad = sc.nextInt();
        sc.nextLine();

        System.out.println("Marcar como comprado? (estado actual: " + (detalle.isComprado() ? "Comprado" : "Pendiente") + ")");
        System.out.println("  1. Comprado");
        System.out.println("  2. Pendiente");
        System.out.print("Seleccione (Enter para dejar como Pendiente): ");
        int opcionComprado = sc.nextInt();
        sc.nextLine();
        boolean comprado = opcionComprado == 1;

        DetalleLista actualizado = new DetalleLista(detalle.getId(), nuevoProducto, nuevaCantidad, listaId, comprado);
        detalleListaServicio.actualizar(actualizado);
        System.out.println("Detalle actualizado con exito.");
    }

    private Producto crearNuevoProducto() {
        System.out.print("Nombre del producto: ");
        String nombre = sc.nextLine();
        if (!ValidationRules.NOMBRE_VALIDO.test(nombre)) {
            System.out.println("Nombre invalido: no puede estar vacio ni contener numeros.");
            return null;
        }
        System.out.print("Unidad de medida (ej: Kilos, Litros, Unidad): ");
        String unidad = sc.nextLine();

        System.out.println("Categorias disponibles:");
        categoriaServicio.obtenerTodos().forEach(c ->
                System.out.println("  " + c.getId() + " - " + c.getNombre()));
        System.out.print("ID categoria: ");
        int categoriaId = sc.nextInt();
        sc.nextLine();

        Optional<CategoriaProducto> categoriaOpt = categoriaServicio.buscarPorId(categoriaId);
        if (categoriaOpt.isEmpty()) {
            System.out.println("No existe categoria con ese ID.");
            return null;
        }

        Producto nuevo = new Producto(0, nombre, unidad, categoriaOpt.get());
        productoServicio.crear(nuevo);

        return productoServicio.obtenerTodos().stream()
                .filter(p -> p.getNombre().equals(nombre))
                .reduce((first, second) -> second)
                .orElse(null);
    }

    private List<ListaCompra> obtenerListasPorUsuario(int usuarioId) {
        List<ListaCompra> resultado = new ArrayList<>();
        for (ListaCompra lista : listaCompraServicio.obtenerTodos()) {
            if (lista.getUsuario().getId() == usuarioId) {
                resultado.add(lista);
            }
        }
        return resultado;
    }

    private List<DetalleLista> obtenerDetallesPorLista(int listaId) {
        List<DetalleLista> resultado = new ArrayList<>();
        for (DetalleLista detalle : detalleListaServicio.obtenerTodos()) {
            if (detalle.getListaId() == listaId) {
                resultado.add(detalle);
            }
        }
        return resultado;
    }
}
