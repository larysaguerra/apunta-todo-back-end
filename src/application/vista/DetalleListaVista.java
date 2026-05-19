package application.vista;

import application.service.outputs.DesalleListaServicio;
import application.domain.DetalleLista;
import application.service.DetalleListaProductoServicio;

import java.util.Scanner;

public class DetalleListaVista {

    private final DesalleListaServicio servicio;
    private final DetalleListaProductoServicio detalleListaProductoServicio;
    private final Scanner sc = new Scanner(System.in);

    public DetalleListaVista(
            DesalleListaServicio desalleListaServicio,
            DetalleListaProductoServicio detalleListaProductoServicio
    ) {
        this.servicio = desalleListaServicio;
        this.detalleListaProductoServicio = detalleListaProductoServicio;
    }

    public void menu() {
        int op;

        do {
            System.out.println("\n=== DETALLE LISTA ===");
            System.out.println("1. Crear detalle");
            System.out.println("2. Leer detalle por ID");
            System.out.println("3. Listar detalles");
            System.out.println("4. Actualizar detalle");
            System.out.println("5. Eliminar detalle");
            System.out.println("0. Volver");

            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    System.out.print("ID producto: ");
                    int productoId = sc.nextInt();
                    System.out.print("Cantidad: ");
                    int cantidad = sc.nextInt();
                    System.out.print("ID lista: ");
                    int listaId = sc.nextInt();

                    servicio.crear(new DetalleLista(id, productoId, cantidad, listaId));
                    System.out.println("✅ Detalle creado");
                    break;

                case 2:
                    System.out.print("ID: ");
                    int idBuscar = sc.nextInt();
                    DetalleLista detalle = servicio.leerPorId(idBuscar);
                    if (detalle == null) {
                        System.out.println("⚠ No existe detalle con ese ID");
                    } else {
                        System.out.println(detalleListaProductoServicio.describirDetalleConLista(detalle));
                    }
                    break;

                case 3:
                    if (servicio.obtenerTodos().isEmpty()) {
                        System.out.println("⚠ No hay detalles registrados");
                    } else {
                        servicio.obtenerTodos().forEach(d ->
                                System.out.println(detalleListaProductoServicio.describirDetalleConLista(d)));
                    }
                    break;

                case 4:
                    System.out.print("ID a actualizar: ");
                    int idActualizar = sc.nextInt();
                    if (servicio.leerPorId(idActualizar) == null) {
                        System.out.println("⚠ No existe detalle con ese ID");
                        break;
                    }
                    System.out.print("Nuevo ID producto: ");
                    int nuevoProductoId = sc.nextInt();
                    System.out.print("Nueva cantidad: ");
                    int nuevaCantidad = sc.nextInt();
                    System.out.print("Nuevo ID lista: ");
                    int nuevaListaId = sc.nextInt();

                    servicio.actualizar(new DetalleLista(idActualizar, nuevoProductoId, nuevaCantidad, nuevaListaId));
                    System.out.println("✅ Detalle actualizado");
                    break;

                case 5:
                    System.out.print("ID a eliminar: ");
                    int idEliminar = sc.nextInt();
                    servicio.eliminar(idEliminar);
                    System.out.println("🗑 Detalle eliminado");
                    break;

                case 0:
                    System.out.println("🔙 Volviendo...");
                    break;

                default:
                    System.out.println("❌ Opción inválida");
            }
        } while (op != 0);
    }

}
