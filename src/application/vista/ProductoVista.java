package application.vista;

import application.domain.Producto;
import application.service.outputs.ProductoServicio;

import java.util.Scanner;

public class ProductoVista {

    private final ProductoServicio servicio;
    private final Scanner sc = new Scanner(System.in);

    public ProductoVista(ProductoServicio productoServicio) {
        this.servicio = productoServicio;
    }

    public void menu() {

        int op;

        do {
            System.out.println("\n=== PRODUCTOS ===");
            System.out.println("1. Crear producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Eliminar producto");
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

                    Producto producto = new Producto(id, nombre, "Unidad", 1);
                    servicio.crear(producto);

                    System.out.println("✅ Producto guardado");
                    break;

                case 2:
                    System.out.println("\n📋 Lista:");

                    if (servicio.obtenerTodos().isEmpty()) {
                        System.out.println("⚠ No hay productos");
                    } else {
                        servicio.obtenerTodos().forEach(p ->
                                System.out.println(p.getId() + " - " + p.getNombre()));
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