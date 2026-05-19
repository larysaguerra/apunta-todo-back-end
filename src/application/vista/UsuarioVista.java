package application.vista;

import application.service.outputs.UsuarioServicio;
import application.domain.Usuario;

import java.util.Scanner;

public class UsuarioVista {

    private final UsuarioServicio servicio;
    private final Scanner sc = new Scanner(System.in);

    public UsuarioVista(UsuarioServicio usuarioServicio) {
        this.servicio = usuarioServicio;
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
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Apellido: ");
                    String apellido = sc.nextLine();
                    System.out.print("Correo: ");
                    String correo = sc.nextLine();
                    System.out.print("Telefono: ");
                    String telefono = sc.nextLine();
                    System.out.print("Contrasena: ");
                    String contrasena = sc.nextLine();
                    System.out.print("ID rol: ");
                    int rolId = sc.nextInt();

                    Usuario usuario = new Usuario();
                    usuario.setId(id);
                    usuario.setNombre(nombre);
                    usuario.setApellido(apellido);
                    usuario.setCorreo(correo);
                    usuario.setTelefono(telefono);
                    usuario.setContrasena(contrasena);
                    usuario.setRol(rolId);

                    servicio.crear(usuario);
                    System.out.println("✅ Usuario creado");
                    break;

                case 2:
                    System.out.print("ID: ");
                    int idBuscar = sc.nextInt();
                    Usuario usuarioLeido = servicio.leerPorId(idBuscar);
                    if (usuarioLeido == null) {
                        System.out.println("⚠ No existe usuario con ese ID");
                    } else {
                        System.out.println(usuarioLeido.getId() + " - " + usuarioLeido.getNombre() + " "
                                + usuarioLeido.getApellido() + " - " + usuarioLeido.getCorreo());
                    }
                    break;

                case 3:
                    if (servicio.obtenerTodos().isEmpty()) {
                        System.out.println("⚠ No hay usuarios registrados");
                    } else {
                        servicio.obtenerTodos().forEach(u -> System.out.println(u.getId() + " - " + u.getNombre()
                                + " " + u.getApellido() + " - " + u.getCorreo()));
                    }
                    break;

                case 4:
                    System.out.print("ID a actualizar: ");
                    int idActualizar = sc.nextInt();
                    sc.nextLine();
                    if (servicio.leerPorId(idActualizar) == null) {
                        System.out.println("⚠ No existe usuario con ese ID");
                        break;
                    }
                    System.out.print("Nuevo nombre: ");
                    String nuevoNombre = sc.nextLine();
                    System.out.print("Nuevo apellido: ");
                    String nuevoApellido = sc.nextLine();
                    System.out.print("Nuevo correo: ");
                    String nuevoCorreo = sc.nextLine();
                    System.out.print("Nuevo telefono: ");
                    String nuevoTelefono = sc.nextLine();
                    System.out.print("Nueva contrasena: ");
                    String nuevaContrasena = sc.nextLine();
                    System.out.print("Nuevo ID rol: ");
                    int nuevoRolId = sc.nextInt();

                    Usuario usuarioActualizado = new Usuario();
                    usuarioActualizado.setId(idActualizar);
                    usuarioActualizado.setNombre(nuevoNombre);
                    usuarioActualizado.setApellido(nuevoApellido);
                    usuarioActualizado.setCorreo(nuevoCorreo);
                    usuarioActualizado.setTelefono(nuevoTelefono);
                    usuarioActualizado.setContrasena(nuevaContrasena);
                    usuarioActualizado.setRol(nuevoRolId);

                    servicio.actualizar(usuarioActualizado);
                    System.out.println("✅ Usuario actualizado");
                    break;

                case 5:
                    System.out.print("ID a eliminar: ");
                    int idEliminar = sc.nextInt();
                    servicio.eliminar(idEliminar);
                    System.out.println("🗑 Usuario eliminado");
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