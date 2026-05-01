import dominio.Rol;
import dominio.Usuario;
import dominio.ListaCompra;
import dominio.Producto;
import dominio.CategoriaProducto;
import dominio.DetalleLista;

import application.service.*;
import infraestructura.repositorio.*;
import vista.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // ===== DATOS INICIALES =====
        Rol admin = new Rol();
        admin.crearRol(1, "Administrador", "Rol con todos los permisos");

        Usuario usuario = new Usuario();
        usuario.crearUsuario(1, "Yetty", "Sanz", "yetty@email.com", "300123456", "1234", admin.getId());

        CategoriaProducto categoria = new CategoriaProducto();
        categoria.crearCategoria(1, "Lacteos", "Productos derivados de la leche");

        Producto producto = new Producto();
        producto = producto.crearProducto(1, "Leche", "Litros", categoria.getId());

        ListaCompra lista = new ListaCompra();
        lista.crearListaDeCompra(1, "Mercado semanal", "2026-03-11", usuario.getId());

        DetalleLista detalle = new DetalleLista();
        detalle.crearDetallelista(1, producto.getId(), 2, lista.getId());

        // ===== INYECCIÓN =====
        ProductoVista productoVista = new ProductoVista(
                new ProductoServicioImpl(new ProductoRepositorio()));

        CategoriaVista categoriaVista = new CategoriaVista(
                new CategoriaServicioImpl(new CategoriaRepositorio()));

        RolVista rolVista = new RolVista(
                new RolServicioImpl(new RolRepositorio()));

        // ===== MENÚ GENERAL =====
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== SISTEMA =====");
            System.out.println("1. Productos");
            System.out.println("2. Categorias");
            System.out.println("3. Roles");
            System.out.println("0. Salir");

            int op = sc.nextInt();

            if (op == 1) {
                productoVista.menu();
            }

            if (op == 2) {
                categoriaVista.menu();
            }

            if (op == 3) {
                rolVista.menu();
            }

            if (op == 0) {
                System.out.println("Saliendo...");
                break;
            }
        }
    }
}