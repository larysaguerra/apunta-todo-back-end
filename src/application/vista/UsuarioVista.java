package application.vista;

import application.domain.Rol;
import application.domain.Usuario;
import application.domain.validaciones.ValidationRules;
import application.service.outputs.RolServicio;
import application.service.outputs.UsuarioServicio;

import java.util.Optional;
import java.util.Scanner;

public class UsuarioVista {

    private final UsuarioServicio servicio;
    private final RolServicio rolServicio;
    private final Scanner sc = new Scanner(System.in);

    public UsuarioVista(UsuarioServicio usuarioServicio, RolServicio rolServicio) {
        this.servicio = usuarioServicio;
        this.rolServicio = rolServicio;
    }

    public void menu() {
        int op;

        do {
            System.out.println("\n=== USUARIOS ===");
            System.out.println("1. Crear usuario");
            System.out.println("2. Leer usuario por ID");
            System.out.println("3. Listar usuarios");
            System.out.println("4. Actualizar usuario");
            System.out.println("5. Eliminar usuario");
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
                    System.out.print("Apellido: ");
                    String apellido = sc.nextLine();
                    if (!ValidationRules.NOMBRE_VALIDO.test(apellido)) {
                        System.out.println("Apellido invalido: no puede estar vacio ni contener numeros.");
                        break;
                    }
                    System.out.print("Correo: ");
                    String correo = sc.nextLine();
                    if (!ValidationRules.EMAIL_VALIDO.test(correo)) {
                        System.out.println("Correo invalido: debe tener formato usuario@dominio.com");
                        break;
                    }
                    System.out.print("Contrasena: ");
                    String contrasena = sc.nextLine();
                    if (!ValidationRules.CONTRASENA_VALIDA.test(contrasena)) {
                        System.out.println("Contrasena invalida: debe tener al menos 8 caracteres.");
                        break;
                    }
                    System.out.print("Telefono: ");
                    String telefono = sc.nextLine();
                    System.out.print("ID rol: ");
                    int rolId = sc.nextInt();
                    sc.nextLine();

                    Optional<Rol> rolOpt = rolServicio.buscarPorId(rolId);
                    if (rolOpt.isEmpty()) {
                        System.out.println("No existe un rol con ese ID.");
                        break;
                    }
                    Rol rol = rolOpt.get();

                    // El ID lo asigna MySQL automáticamente (AUTO_INCREMENT)
                    Usuario usuario = new Usuario();
                    usuario.setNombre(nombre);
                    usuario.setApellido(apellido);
                    usuario.setCorreo(correo);
                    usuario.setContrasena(contrasena);
                    usuario.setTelefono(telefono);
                    usuario.setRol(rol);

                    servicio.crear(usuario);
                    System.out.println("Usuario creado.");
                    break;

                case 2:
                    System.out.print("ID: ");
                    int idBuscar = sc.nextInt();
                    sc.nextLine();
                    Optional<Usuario> usuarioOpt = servicio.leerPorId(idBuscar);
                    if (usuarioOpt.isEmpty()) {
                        System.out.println("No existe usuario con ese ID.");
                    } else {
                        System.out.println(usuarioOpt.get());
                    }
                    break;

                case 3:
                    if (servicio.obtenerTodos().isEmpty()) {
                        System.out.println("No hay usuarios registrados.");
                    } else {
                        servicio.obtenerTodos().forEach(u -> System.out.println(u));
                    }
                    break;

                case 4:
                    System.out.print("ID a actualizar: ");
                    int idActualizar = sc.nextInt();
                    sc.nextLine();
                    if (servicio.leerPorId(idActualizar).isEmpty()) {
                        System.out.println("No existe usuario con ese ID.");
                        break;
                    }
                    System.out.print("Nuevo nombre: ");
                    String nuevoNombre = sc.nextLine();
                    if (!ValidationRules.NOMBRE_VALIDO.test(nuevoNombre)) {
                        System.out.println("Nombre invalido: no puede estar vacio ni contener numeros.");
                        break;
                    }
                    System.out.print("Nuevo apellido: ");
                    String nuevoApellido = sc.nextLine();
                    if (!ValidationRules.NOMBRE_VALIDO.test(nuevoApellido)) {
                        System.out.println("Apellido invalido: no puede estar vacio ni contener numeros.");
                        break;
                    }
                    System.out.print("Nuevo correo: ");
                    String nuevoCorreo = sc.nextLine();
                    if (!ValidationRules.EMAIL_VALIDO.test(nuevoCorreo)) {
                        System.out.println("Correo invalido: debe tener formato usuario@dominio.com");
                        break;
                    }
                    System.out.print("Nueva contrasena: ");
                    String nuevaContrasena = sc.nextLine();
                    if (!ValidationRules.CONTRASENA_VALIDA.test(nuevaContrasena)) {
                        System.out.println("Contrasena invalida: debe tener al menos 8 caracteres.");
                        break;
                    }
                    System.out.print("Nuevo telefono: ");
                    String nuevoTelefono = sc.nextLine();
                    System.out.print("Nuevo ID rol: ");
                    int nuevoRolId = sc.nextInt();
                    sc.nextLine();

                    Optional<Rol> nuevoRolOpt = rolServicio.buscarPorId(nuevoRolId);
                    if (nuevoRolOpt.isEmpty()) {
                        System.out.println("No existe un rol con ese ID.");
                        break;
                    }
                    Rol nuevoRol = nuevoRolOpt.get();

                    Usuario usuarioActualizado = new Usuario();
                    usuarioActualizado.setId(idActualizar);
                    usuarioActualizado.setNombre(nuevoNombre);
                    usuarioActualizado.setApellido(nuevoApellido);
                    usuarioActualizado.setCorreo(nuevoCorreo);
                    usuarioActualizado.setContrasena(nuevaContrasena);
                    usuarioActualizado.setTelefono(nuevoTelefono);
                    usuarioActualizado.setRol(nuevoRol);

                    servicio.actualizar(usuarioActualizado);
                    System.out.println("Usuario actualizado.");
                    break;

                case 5:
                    System.out.print("ID a eliminar: ");
                    int idEliminar = sc.nextInt();
                    sc.nextLine();
                    servicio.eliminar(idEliminar);
                    System.out.println("Usuario eliminado.");
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
