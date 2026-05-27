package application.userinterface;

import application.domain.DetalleLista;
import application.domain.ListaCompra;
import application.domain.Usuario;
import application.service.DetalleListaProductoServicio;
import application.service.outputs.DesalleListaServicio;
import application.service.outputs.ListaCompraServicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SesionUsuarioMenu {

    private final ListaCompraServicio listaCompraServicio;
    private final DesalleListaServicio detalleListaServicio;
    private final DetalleListaProductoServicio detalleListaProductoServicio;
    private final Scanner sc = new Scanner(System.in);

    public SesionUsuarioMenu(
            ListaCompraServicio listaCompraServicio,
            DesalleListaServicio detalleListaServicio,
            DetalleListaProductoServicio detalleListaProductoServicio
    ) {
        this.listaCompraServicio = listaCompraServicio;
        this.detalleListaServicio = detalleListaServicio;
        this.detalleListaProductoServicio = detalleListaProductoServicio;
    }

    public void showMenuForUser(Usuario usuarioLogueado) {
        int opcion;

        do {
            System.out.println("\n=== MENU USUARIO ===");
            System.out.println("Usuario: " + usuarioLogueado.getNombre() + " " + usuarioLogueado.getApellido());
            System.out.println("1. Ver mis listas de compras con detalles");
            System.out.println("2. Editar una lista");
            System.out.println("3. Editar un detalle de una lista");
            System.out.println("0. Cerrar sesion");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    mostrarListasConDetalles(usuarioLogueado.getId());
                    break;
                case 2:
                    editarLista(usuarioLogueado.getId());
                    break;
                case 3:
                    editarDetalle(usuarioLogueado.getId());
                    break;
                case 0:
                    System.out.println("Sesion cerrada.");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        } while (opcion != 0);
    }

    private void mostrarListasConDetalles(int usuarioId) {
        List<ListaCompra> listasUsuario = obtenerListasPorUsuario(usuarioId);
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

    private void editarLista(int usuarioId) {
        List<ListaCompra> listasUsuario = obtenerListasPorUsuario(usuarioId);
        if (listasUsuario.isEmpty()) {
            System.out.println("No tienes listas para editar.");
            return;
        }

        System.out.print("Ingresa el ID de la lista a editar: ");
        int listaId = sc.nextInt();
        sc.nextLine();

        ListaCompra lista = listaCompraServicio.leerPorId(listaId);
        if (lista == null || lista.getUsuarioId() != usuarioId) {
            System.out.println("No puedes editar esa lista.");
            return;
        }

        System.out.print("Nuevo nombre: ");
        String nuevoNombre = sc.nextLine();
        System.out.print("Nueva fecha (yyyy-mm-dd): ");
        String nuevaFecha = sc.nextLine();

        ListaCompra actualizada = new ListaCompra(lista.getId(), nuevoNombre, nuevaFecha, usuarioId, lista.getEstado());
        listaCompraServicio.actualizar(actualizada);
        System.out.println("Lista actualizada con exito.");
    }

    private void editarDetalle(int usuarioId) {
        List<ListaCompra> listasUsuario = obtenerListasPorUsuario(usuarioId);
        if (listasUsuario.isEmpty()) {
            System.out.println("No tienes listas asociadas.");
            return;
        }

        System.out.print("Ingresa el ID de la lista del detalle: ");
        int listaId = sc.nextInt();
        sc.nextLine();

        ListaCompra lista = listaCompraServicio.leerPorId(listaId);
        if (lista == null || lista.getUsuarioId() != usuarioId) {
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

        DetalleLista detalle = detalleListaServicio.leerPorId(detalleId);
        if (detalle == null || detalle.getListaId() != listaId) {
            System.out.println("Ese detalle no pertenece a la lista.");
            return;
        }

        System.out.print("Nuevo productoId: ");
        int nuevoProductoId = sc.nextInt();
        System.out.print("Nueva cantidad: ");
        int nuevaCantidad = sc.nextInt();
        sc.nextLine();

        DetalleLista actualizado = new DetalleLista(detalle.getId(), nuevoProductoId, nuevaCantidad, listaId, detalle.isComprado());
        detalleListaServicio.actualizar(actualizado);
        System.out.println("Detalle actualizado con exito.");
    }

    private List<ListaCompra> obtenerListasPorUsuario(int usuarioId) {
        List<ListaCompra> resultado = new ArrayList<>();
        for (ListaCompra lista : listaCompraServicio.obtenerTodos()) {
            if (lista.getUsuarioId() == usuarioId) {
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
