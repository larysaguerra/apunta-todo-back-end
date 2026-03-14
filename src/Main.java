import dominio.Rol;
import dominio.Usuario;
import dominio.ListaCompra;
import dominio.Producto;
import dominio.CategoriaProducto;
import dominio.DetalleLista;

public class Main {

    public static void main(String[] args) {

        Rol admin = new Rol();
        // Crear un nuevo rol
        admin.crearRol(1, "Administrador", "Rol con todos los permisos");
        // Mostrar la información del rol
        admin.mostrarRol();

        Usuario usuario = new Usuario();
        // Crear un nuevo usuario
        usuario.crearUsuario(1, "Yetty", "Sanz", "yetty@email.com", "300123456", "1234", usuario.getRolId());
        // Mostrar la información del usuario
        usuario.mostrarUsuario();

        CategoriaProducto categoria = new CategoriaProducto();
        // Crear una nueva categoría
        categoria.crearCategoria(1, "Lacteos", "Productos derivados de la leche");
        // mostrar la información de la categoría
        categoria.mostrarCategoria();

        Producto producto = new Producto();
        // Crear un nuevo producto
        producto.crearProducto(1, "Leche", "Litros", categoria.getId());
        // mostrar la información del producto
        producto.mostrarProducto();

        ListaCompra lista = new ListaCompra();
        // crear una nueva lista de compra
        lista.crearListaDeCompra(1, "Mercado semanal", "2026-03-11", usuario.getId());
        // Mostrar la información de la lista
        lista.mostrarListaCompra();

        DetalleLista detalle = new DetalleLista();
        // Crear un detalle de lista
        detalle.crearDetallelista(1, producto.getId(), 2, lista.getId());
        // mostrar la información del detalle
        detalle.mostrarDetalleLista();
    }
}




