package application.configuration;

import application.service.*;
import application.service.outputs.UsuarioServicio;
import application.userinterface.MenuApp;
import application.userinterface.SesionUsuarioMenu;
import application.repositorio.*;
import application.vista.*;

public class Config {

    public static MenuApp createMenuApp() {

        // --- Roles ---
        RolRepositorio rolRepositorio = new RolRepositorio();
        RolServicioImpl rolServicioImpl = new RolServicioImpl(rolRepositorio);
        RolVista rolVista = new RolVista(rolServicioImpl);

        // --- Usuarios ---
        UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();
        UsuarioServicioImpl usuarioServicioImpl = new UsuarioServicioImpl(usuarioRepositorio);
        UsuarioVista usuarioVista = new UsuarioVista(usuarioServicioImpl, rolServicioImpl);

        // --- Categorias ---
        CategoriaRepositorio categoriaRepositorio = new CategoriaRepositorio();
        CategoriaServicioImpl categoriaServicioImpl = new CategoriaServicioImpl(categoriaRepositorio);
        CategoriaVista categoriaVista = new CategoriaVista(categoriaServicioImpl);

        // --- Productos (necesita CategoriaServicio para la vista) ---
        ProductoRepositorio productoRepositorio = new ProductoRepositorio();
        ProductoServicioImpl productoServicioImpl = new ProductoServicioImpl(productoRepositorio);
        ProductoVista productoVista = new ProductoVista(productoServicioImpl, categoriaServicioImpl);
        DetalleListaProductoServicio detalleListaProductoServicio = new DetalleListaProductoServicio();

        // --- Listas de compra (necesita UsuarioServicio para la vista) ---
        ListaCompraRepositorio listaCompraRepositorio = new ListaCompraRepositorio();
        ListaCompraServicioImpl listaCompraServicioImpl = new ListaCompraServicioImpl(listaCompraRepositorio);
        ListaCompraVista listaCompraVista = new ListaCompraVista(listaCompraServicioImpl, usuarioServicioImpl);

        // --- Detalle lista (necesita ProductoServicio para la vista) ---
        DetalleListaRepositorio detalleListaRepositorio = new DetalleListaRepositorio();
        DetalleListaServicioImpl detalleListaServicioImpl = new DetalleListaServicioImpl(detalleListaRepositorio);
        DetalleListaVista detalleListaVista = new DetalleListaVista(
                detalleListaServicioImpl,
                detalleListaProductoServicio,
                productoServicioImpl
        );

        // --- Sesion de usuario (necesita ProductoServicio para buscar productos al editar) ---
        UsuarioServicio usuarioServicio = usuarioServicioImpl;
        SesionUsuarioMenu sesionUsuarioMenu = new SesionUsuarioMenu(
                listaCompraServicioImpl,
                detalleListaServicioImpl,
                detalleListaProductoServicio,
                productoServicioImpl
        );

        return new MenuApp(
                rolVista,
                usuarioVista,
                categoriaVista,
                productoVista,
                listaCompraVista,
                detalleListaVista,
                usuarioServicio,
                sesionUsuarioMenu
        );
    }
}
