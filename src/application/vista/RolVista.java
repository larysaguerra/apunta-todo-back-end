package application.vista;

import application.domain.Rol;
import application.service.outputs.RolServicio;

import java.util.Scanner;

public class RolVista {

    private final RolServicio servicio;
    private final Scanner sc = new Scanner(System.in);

    public RolVista(RolServicio rolServicio) {
        this.servicio = rolServicio;
    }

    public void menu() {

        int op;

        do {
            System.out.println("\n=== ROLES ===");
            System.out.println("1. Crear rol");
            System.out.println("2. Listar roles");
            System.out.println("3. Eliminar rol");
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

                    Rol rol = new Rol(id, nombre, "General");
                    servicio.crear(rol);

                    System.out.println("✅ Rol guardado");
                    break;

                case 2:
                    System.out.println("\n📋 Lista:");

                    if (servicio.obtenerTodos().isEmpty()) {
                        System.out.println("⚠ No hay roles");
                    } else {
                        servicio.obtenerTodos().forEach(r ->
                                System.out.println(r.getId() + " - " + r.getNombre()));
                    }
                    break;

                case 3:
                    System.out.print("ID: ");
                    int idEliminar = sc.nextInt();
                    servicio.eliminar(idEliminar);
                    System.out.println("🗑 Eliminado");
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