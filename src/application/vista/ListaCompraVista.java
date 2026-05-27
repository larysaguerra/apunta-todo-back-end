package application.vista;

import application.domain.ListaCompra;
import application.domain.Usuario;
import application.domain.enums.EstadoLista;
import application.service.outputs.ListaCompraServicio;
import application.service.outputs.UsuarioServicio;

import java.util.Scanner;

public class ListaCompraVista {

    private final ListaCompraServicio servicio;
    private final UsuarioServicio usuarioServicio;
    private final Scanner sc = new Scanner(System.in);

    public ListaCompraVista(ListaCompraServicio listaCompraServicio, UsuarioServicio usuarioServicio) {
        this.servicio = listaCompraServicio;
        this.usuarioServicio = usuarioServicio;
    }

    public void menu() {
        int op;

        do {
            System.out.println("\n=== LISTAS DE COMPRA ===");
            System.out.println("1. Crear lista");
            System.out.println("2. Leer lista por ID");
            System.out.println("3. Listar listas");
            System.out.println("4. Actualizar lista");
            System.out.println("5. Eliminar lista");
            System.out.println("0. Volver");

            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Fecha (yyyy-mm-dd): ");
                    String fecha = sc.nextLine();

                    // Mostrar usuarios disponibles para elegir
                    System.out.println("Usuarios disponibles:");
                    usuarioServicio.obtenerTodos().forEach(u ->
                            System.out.println("  " + u.getId() + " - " + u.getNombre() + " " + u.getApellido()));
                    System.out.print("ID usuario: ");
                    int usuarioId = sc.nextInt();
                    sc.nextLine();

                    Usuario usuario = usuarioServicio.leerPorId(usuarioId);
                    if (usuario == null) {
                        System.out.println("No existe usuario con ese ID.");
                        break;
                    }

                    EstadoLista estadoCrear = seleccionarEstado();
                    servicio.crear(new ListaCompra(id, nombre, fecha, usuario, estadoCrear));
                    System.out.println("Lista creada.");
                    break;

                case 2:
                    System.out.print("ID: ");
                    int idBuscar = sc.nextInt();
                    sc.nextLine();
                    ListaCompra lista = servicio.leerPorId(idBuscar);
                    if (lista == null) {
                        System.out.println("No existe lista con ese ID.");
                    } else {
                        System.out.println(lista);
                    }
                    break;

                case 3:
                    if (servicio.obtenerTodos().isEmpty()) {
                        System.out.println("No hay listas registradas.");
                    } else {
                        servicio.obtenerTodos().forEach(l -> System.out.println(l));
                    }
                    break;

                case 4:
                    System.out.print("ID a actualizar: ");
                    int idActualizar = sc.nextInt();
                    sc.nextLine();
                    ListaCompra existente = servicio.leerPorId(idActualizar);
                    if (existente == null) {
                        System.out.println("No existe lista con ese ID.");
                        break;
                    }
                    System.out.print("Nuevo nombre: ");
                    String nuevoNombre = sc.nextLine();
                    System.out.print("Nueva fecha (yyyy-mm-dd): ");
                    String nuevaFecha = sc.nextLine();
                    EstadoLista nuevoEstado = seleccionarEstado();

                    // Conserva el mismo usuario, solo actualiza los demás campos
                    servicio.actualizar(new ListaCompra(idActualizar, nuevoNombre, nuevaFecha, existente.getUsuario(), nuevoEstado));
                    System.out.println("Lista actualizada.");
                    break;

                case 5:
                    System.out.print("ID a eliminar: ");
                    int idEliminar = sc.nextInt();
                    sc.nextLine();
                    servicio.eliminar(idEliminar);
                    System.out.println("Lista eliminada.");
                    break;

                case 0:
                    System.out.println("Volviendo...");
                    break;

                default:
                    System.out.println("Opcion invalida.");
            }
        } while (op != 0);
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
            case 2: return EstadoLista.ABIERTA;
            case 3: return EstadoLista.CERRADA;
            case 4: return EstadoLista.ARCHIVADA;
            default:
                System.out.println("Opcion invalida. Se asigna ABIERTA por defecto.");
                return EstadoLista.ABIERTA;
        }
    }
}
