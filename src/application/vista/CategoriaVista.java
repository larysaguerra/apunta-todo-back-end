package application.vista;

import application.domain.CategoriaProducto;
import application.service.outputs.CategoriaServicio;

import java.util.Scanner;

public class CategoriaVista {

    private final CategoriaServicio servicio;
    private final Scanner sc = new Scanner(System.in);

    public CategoriaVista(CategoriaServicio categoriaServicio) {
        this.servicio = categoriaServicio;
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

                    CategoriaProducto categoria = new CategoriaProducto(id, nombre, "General");
                    servicio.crear(categoria);

                    System.out.println("✅ Categoria guardada");
                    break;

                case 2:
                    System.out.println("\n📋 Lista:");

                    if (servicio.obtenerTodos().isEmpty()) {
                        System.out.println("⚠ No hay categorias");
                    } else {
                        servicio.obtenerTodos().forEach(cat ->
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