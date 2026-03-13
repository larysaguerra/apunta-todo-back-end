import dominio.Rol;
import dominio.Usuario;
import dominio.ListaCompra;
import dominio.Producto;
import dominio.CategoriaProducto;
import dominio.DetalleLista;
import dominio.*;

public class Main {

    public static void main(String[] args) {

        // Crear un nuevo rol
        Rol admin = new Rol(1, "Administrador", "Rol con todos los permisos");

        // Mostrar la información del rol
        admin.mostrarRol();

        // Crear un nuevo usuario
        Usuario usuario = new Usuario(1, "Yetty", "Sanz", "yetty@email.com", "300123456", "1234", admin);

        // Mostrar la información del usuario
        usuario.mostrarUsuario();

        // Crear una nueva categoría
        CategoriaProducto categoria = new CategoriaProducto(1, "Lacteos", "Productos derivados de la leche");

        // mostrar la información de la categoría
        categoria.mostrarCategoria();

        // Crear un nuevo producto
        Producto producto = new Producto(1, "Leche", "Litros", categoria);

        // mostrar la información del producto
        producto.mostrarProducto();

        // crear una nueva lista de compra
        ListaCompra lista = new ListaCompra(1, "Mercado semanal", "2026-03-11", usuario);

        // Mostrar la información de la lista
        lista.mostrarListaCompra();

        // Crear un detalle de lista
        DetalleLista detalle = new DetalleLista(1, producto, 2, lista);

        // mostrar la información del detalle
        detalle.mostrarDetalleLista();










    }
}




