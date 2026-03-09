import dominio.Rol;

public class Main {

    public static void main(String[] args) {

        // Crear un nuevo rol
        Rol admin = new Rol(1, "Administrador", "Rol con todos los permisos");

        // Mostrar la información del rol
        admin.mostrarRol();

    }

}