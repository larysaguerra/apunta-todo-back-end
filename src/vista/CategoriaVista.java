package vista;

import dominio.CategoriaProducto;
import application.service.CategoriaServicio;

import java.util.Scanner;

public class CategoriaVista {

    private CategoriaServicio servicio;
    private Scanner sc = new Scanner(System.in);

    public CategoriaVista(CategoriaServicio servicio) {
        this.servicio = servicio;
    }

    public void menu() {

        int op;

        do {
            System.out.println("\n=== CATEGORIAS ===");
            System.out.println("1. Crear categoria");
            System.out.println("2. Listar categorias");
            System.out.println("3. Eliminar categoria");
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

                    CategoriaProducto c = new CategoriaProducto().crearCategoria(id, nombre, "General");
                    servicio.crear(c);

                    System.out.println("✅ Categoria guardada");
                    break;

                case 2:
                    System.out.println("\n📋 Lista:");

                    if (servicio.listar().isEmpty()) {
                        System.out.println("⚠ No hay categorias");
                    } else {
                        servicio.listar().forEach(cat ->
                                System.out.println(cat.getId() + " - " + cat.getNombre()));
                    }
                    break;

                case 3:
                    System.out.print("ID: ");
                    int idEliminar = sc.nextInt();
                    servicio.eliminar(idEliminar);
                    System.out.println("🗑 Eliminada");
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