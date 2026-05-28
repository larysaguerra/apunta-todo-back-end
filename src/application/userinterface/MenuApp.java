package application.userinterface;

import application.domain.Usuario;
import application.service.outputs.UsuarioServicio;
import application.vista.*;

import java.util.Scanner;

public class MenuApp {

    private final RolVista rolVista;
    private final UsuarioVista usuarioVista;
    private final CategoriaVista categoriaVista;
    private final ProductoVista productoVista;
    private final ListaCompraVista listaCompraVista;
    private final DetalleListaVista detalleListaVista;
    private final UsuarioServicio usuarioServicio;
    private final SesionUsuarioMenu sesionUsuarioMenu;

    public MenuApp(RolVista rolVista, UsuarioVista usuarioVista, CategoriaVista categoriaVista,
                   ProductoVista productoVista, ListaCompraVista listaCompraVista, DetalleListaVista detalleListaVista,
                   UsuarioServicio usuarioServicio, SesionUsuarioMenu sesionUsuarioMenu) {
        this.rolVista = rolVista;
        this.usuarioVista = usuarioVista;
        this.categoriaVista = categoriaVista;
        this.productoVista = productoVista;
        this.listaCompraVista = listaCompraVista;
        this.detalleListaVista = detalleListaVista;
        this.usuarioServicio = usuarioServicio;
        this.sesionUsuarioMenu = sesionUsuarioMenu;
    }

    public void showMainMenu() {
        System.out.println("Bienvenido al Apunta Todo");

        // ===== MENÚ GENERAL =====
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== SISTEMA =====");
            System.out.println("1. Roles");
            System.out.println("2. Usuarios");
            System.out.println("3. Categorias");
            System.out.println("4. Productos");
            System.out.println("5. Listas de compra");
            System.out.println("6. Detalle lista");
            System.out.println("7. Iniciar sesion como usuario");
            System.out.println("0. Salir");

            int opicion = sc.nextInt();

            if (opicion == 1) {
                rolVista.menu();
            }
            if (opicion == 2) {
                usuarioVista.menu();
            }
            if (opicion == 3) {
                categoriaVista.menu();
            }
            if (opicion == 4) {
                productoVista.menu();
            }
            if (opicion == 5) {
                listaCompraVista.menu();
            }
            if (opicion == 6) {
                detalleListaVista.menu();
            }
            if (opicion == 7) {
                iniciarSesionUsuario(sc);
            }
            if (opicion == 0) {
                System.out.println("Saliendo...");
                break;
            }

        }

    }

    private void iniciarSesionUsuario(Scanner sc) {
        sc.nextLine();

        System.out.print("Correo: ");
        String correo = sc.nextLine();

        System.out.print("Contrasena: ");
        String contrasena = sc.nextLine();

        Usuario usuario = usuarioServicio.iniciarSesion(correo, contrasena);

        if (usuario == null) {
            System.out.println("Error: correo o contrasena incorrectos.");
            return;
        }

        System.out.println("Inicio de sesion exitoso. Bienvenido, " + usuario.getNombre() + ".");
        sesionUsuarioMenu.showMenuForUser(usuario);
    }

}