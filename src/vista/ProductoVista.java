package vista;

import dominio.Producto;
import application.service.ProductoServicio;

import java.util.Scanner;

public class ProductoVista {

    private ProductoServicio servicio;
    private Scanner sc = new Scanner(System.in);

    public ProductoVista(ProductoServicio servicio) {
        this.servicio = servicio;
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

                    System.out.print("Unidad de medida: ");
                    String unidad = sc.nextLine();

                    System.out.println("Seleccione categoria:");
                    System.out.println("1. Lacteos");
                    System.out.println("2. Carnes");
                    System.out.println("3. Aseo");
                    System.out.println("4. Bebidas");

                    System.out.print("Categoria ID: ");
                    int categoriaId = sc.nextInt();
                    sc.nextLine();

                    Producto producto = new Producto().crearProducto(
                            id,
                            nombre,
                            unidad,
                            categoriaId
                    );

                    servicio.crear(producto);

                    System.out.println("✅ Producto guardado correctamente");
                    break;
                case 2:
                    System.out.println("\n📋 Lista:");

                    if (servicio.listar().isEmpty()) {
                        System.out.println("⚠ No hay productos");
                    } else {
                        servicio.listar().forEach(p ->
                                System.out.println(
                                        "ID: " + p.getId() +
                                                " | Nombre: " + p.getNombre() +
                                                " | Unidad: " + p.getUnidadMedida() +
                                                " | Categoria: " + p.getCategoriaId()
                                )
                        );
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