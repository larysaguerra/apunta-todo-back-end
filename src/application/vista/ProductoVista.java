package application.vista;

import application.domain.CategoriaProducto;
import application.domain.Producto;
import application.service.outputs.CategoriaServicio;
import application.service.outputs.ProductoServicio;

import java.util.Scanner;

public class ProductoVista {

    private final ProductoServicio servicio;
    private final CategoriaServicio categoriaServicio;
    private final Scanner sc = new Scanner(System.in);

    public ProductoVista(ProductoServicio productoServicio, CategoriaServicio categoriaServicio) {
        this.servicio = productoServicio;
        this.categoriaServicio = categoriaServicio;
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

                    System.out.print("Unidad de medida (ej: Kilos, Litros, Unidad): ");
                    String unidad = sc.nextLine();

                    // Mostrar categorías disponibles para que el usuario elija
                    System.out.println("Categorias disponibles:");
                    categoriaServicio.obtenerTodos().forEach(c ->
                            System.out.println("  " + c.getId() + " - " + c.getNombre()));
                    System.out.print("ID categoria: ");
                    int categoriaId = sc.nextInt();
                    sc.nextLine();

                    CategoriaProducto categoria = categoriaServicio.buscarPorId(categoriaId);
                    if (categoria == null) {
                        System.out.println("No existe categoria con ese ID.");
                        break;
                    }

                    servicio.crear(new Producto(id, nombre, unidad, categoria));
                    System.out.println("Producto guardado.");
                    break;

                case 2:
                    if (servicio.obtenerTodos().isEmpty()) {
                        System.out.println("No hay productos registrados.");
                    } else {
                        servicio.obtenerTodos().forEach(p -> System.out.println(p));
                    }
                    break;

                case 3:
                    System.out.print("ID: ");
                    int idEliminar = sc.nextInt();
                    sc.nextLine();
                    servicio.eliminar(idEliminar);
                    System.out.println("Producto eliminado.");
                    break;

                case 0:
                    System.out.println("Volviendo...");
                    break;

                default:
                    System.out.println("Opcion invalida.");
            }

        } while (op != 0);
    }
}
