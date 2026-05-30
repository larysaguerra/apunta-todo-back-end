package application.vista;

import application.domain.ListaCompra;
import application.domain.Usuario;
import application.domain.enums.EstadoLista;
import application.domain.validaciones.ValidationRules;
import application.service.inputs.ListaCompraServicio;
import application.service.inputs.UsuarioServicio;

import java.util.Optional;
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
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    if (!ValidationRules.NOMBRE_VALIDO.test(nombre)) {
                        System.out.println("Nombre invalido: no puede estar vacio ni contener numeros.");
                        break;
                    }
                    System.out.print("Fecha (yyyy-mm-dd): ");
                    String fecha = sc.nextLine();
                    if (!ValidationRules.FECHA_VALIDA.test(fecha)) {
                        System.out.println("Fecha invalida: debe tener formato yyyy-MM-dd (ej: 2025-12-31).");
                        break;
                    }

                    // Mostrar usuarios disponibles para elegir
                    System.out.println("Usuarios disponibles:");
                    usuarioServicio.obtenerTodos().forEach(u ->
                            System.out.println("  " + u.getId() + " - " + u.getNombre() + " " + u.getApellido()));
                    System.out.print("ID usuario: ");
                    int usuarioId = sc.nextInt();
                    sc.nextLine();

                    Optional<Usuario> usuarioOpt = usuarioServicio.leerPorId(usuarioId);
                    if (usuarioOpt.isEmpty()) {
                        System.out.println("No existe usuario con ese ID.");
                        break;
                    }
                    Usuario usuario = usuarioOpt.get();

                    EstadoLista estadoCrear = seleccionarEstado();
                    // El ID lo asigna MySQL automáticamente (AUTO_INCREMENT), pasamos 0
                    servicio.crear(new ListaCompra(0, nombre, fecha, usuario, estadoCrear));
                    System.out.println("Lista creada.");
                    break;

                case 2:
                    System.out.print("ID: ");
                    int idBuscar = sc.nextInt();
                    sc.nextLine();
                    Optional<ListaCompra> listaOpt = servicio.leerPorId(idBuscar);
                    if (listaOpt.isEmpty()) {
                        System.out.println("No existe lista con ese ID.");
                    } else {
                        System.out.println(listaOpt.get());
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
                    Optional<ListaCompra> existenteOpt = servicio.leerPorId(idActualizar);
                    if (existenteOpt.isEmpty()) {
                        System.out.println("No existe lista con ese ID.");
                        break;
                    }
                    ListaCompra existente = existenteOpt.get();
                    System.out.print("Nuevo nombre: ");
                    String nuevoNombre = sc.nextLine();
                    if (!ValidationRules.NOMBRE_VALIDO.test(nuevoNombre)) {
                        System.out.println("Nombre invalido: no puede estar vacio ni contener numeros.");
                        break;
                    }
                    System.out.print("Nueva fecha (yyyy-mm-dd): ");
                    String nuevaFecha = sc.nextLine();
                    if (!ValidationRules.FECHA_VALIDA.test(nuevaFecha)) {
                        System.out.println("Fecha invalida: debe tener formato yyyy-MM-dd (ej: 2025-12-31).");
                        break;
                    }
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
