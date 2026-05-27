package application.vista;

import application.domain.DetalleLista;
import application.domain.Producto;
import application.service.DetalleListaProductoServicio;
import application.service.outputs.ProductoServicio;
import application.service.outputs.DesalleListaServicio;

import java.util.Scanner;

public class DetalleListaVista {

    private final DesalleListaServicio servicio;
    private final DetalleListaProductoServicio detalleListaProductoServicio;
    private final ProductoServicio productoServicio;
    private final Scanner sc = new Scanner(System.in);

    public DetalleListaVista(
            DesalleListaServicio desalleListaServicio,
            DetalleListaProductoServicio detalleListaProductoServicio,
            ProductoServicio productoServicio
    ) {
        this.servicio = desalleListaServicio;
        this.detalleListaProductoServicio = detalleListaProductoServicio;
        this.productoServicio = productoServicio;
    }

    public void menu() {
        int op;

        do {
            System.out.println("\n=== DETALLE LISTA ===");
            System.out.println("1. Agregar ítem");
            System.out.println("2. Ver ítem por ID");
            System.out.println("3. Listar ítems");
            System.out.println("4. Actualizar ítem");
            System.out.println("5. Eliminar ítem");
            System.out.println("0. Volver");

            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    // Mostrar productos disponibles para elegir
                    System.out.println("Productos disponibles:");
                    productoServicio.obtenerTodos().forEach(p ->
                            System.out.println("  " + p.getId() + " - " + p.getNombre()));
                    System.out.print("ID producto: ");
                    int productoId = sc.nextInt();
                    sc.nextLine();

                    Producto producto = productoServicio.buscarPorId(productoId);
                    if (producto == null) {
                        System.out.println("No existe producto con ese ID.");
                        break;
                    }

                    System.out.print("Cantidad: ");
                    int cantidad = sc.nextInt();
                    System.out.print("ID lista: ");
                    int listaId = sc.nextInt();
                    sc.nextLine();

                    // Todo ítem nuevo empieza sin comprar
                    servicio.crear(new DetalleLista(id, producto, cantidad, listaId));
                    System.out.println("Ítem agregado.");
                    break;

                case 2:
                    System.out.print("ID: ");
                    int idBuscar = sc.nextInt();
                    sc.nextLine();
                    DetalleLista detalle = servicio.leerPorId(idBuscar);
                    if (detalle == null) {
                        System.out.println("No existe ítem con ese ID.");
                    } else {
                        System.out.println(detalleListaProductoServicio.describirDetalleConLista(detalle));
                    }
                    break;

                case 3:
                    if (servicio.obtenerTodos().isEmpty()) {
                        System.out.println("No hay ítems registrados.");
                    } else {
                        servicio.obtenerTodos().forEach(d ->
                                System.out.println(detalleListaProductoServicio.describirDetalleConLista(d)));
                    }
                    break;

                case 4:
                    System.out.print("ID a actualizar: ");
                    int idActualizar = sc.nextInt();
                    sc.nextLine();
                    DetalleLista existente = servicio.leerPorId(idActualizar);
                    if (existente == null) {
                        System.out.println("No existe ítem con ese ID.");
                        break;
                    }

                    // Mostrar productos disponibles para elegir
                    System.out.println("Productos disponibles:");
                    productoServicio.obtenerTodos().forEach(p ->
                            System.out.println("  " + p.getId() + " - " + p.getNombre()));
                    System.out.print("Nuevo ID producto: ");
                    int nuevoProductoId = sc.nextInt();
                    sc.nextLine();

                    Producto nuevoProducto = productoServicio.buscarPorId(nuevoProductoId);
                    if (nuevoProducto == null) {
                        System.out.println("No existe producto con ese ID.");
                        break;
                    }

                    System.out.print("Nueva cantidad: ");
                    int nuevaCantidad = sc.nextInt();
                    System.out.print("Nuevo ID lista: ");
                    int nuevaListaId = sc.nextInt();
                    sc.nextLine();
                    boolean nuevoComprado = preguntarComprado(existente.isComprado());

                    servicio.actualizar(new DetalleLista(idActualizar, nuevoProducto, nuevaCantidad, nuevaListaId, nuevoComprado));
                    System.out.println("Ítem actualizado.");
                    break;

                case 5:
                    System.out.print("ID a eliminar: ");
                    int idEliminar = sc.nextInt();
                    sc.nextLine();
                    servicio.eliminar(idEliminar);
                    System.out.println("Ítem eliminado.");
                    break;

                case 0:
                    System.out.println("Volviendo...");
                    break;

                default:
                    System.out.println("Opcion invalida.");
            }
        } while (op != 0);
    }

    private boolean preguntarComprado(boolean estadoActual) {
        System.out.println("Estado actual: " + (estadoActual ? "[✓] Comprado" : "[ ] Pendiente"));
        System.out.println("  1. Marcar como comprado");
        System.out.println("  2. Marcar como pendiente");
        System.out.println("  0. Mantener estado actual");
        System.out.print("Seleccione: ");
        int opcion = sc.nextInt();
        sc.nextLine();

        return switch (opcion) {
            case 1 -> true;
            case 2 -> false;
            default -> estadoActual;
        };
    }
}
