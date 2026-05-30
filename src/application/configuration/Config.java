package application.configuration;

import application.persistence.repositorio.*;
import application.service.*;
import application.service.inputs.UsuarioServicio;
import application.userinterface.MenuApp;
import application.userinterface.SesionUsuarioMenu;
import application.vista.*;

public class Config {

    public static MenuApp createMenuApp() {

        // --- Repositorios MySQL (adaptadores reales) ---
        RolAdapterMySql rolRepositorio = new RolAdapterMySql();
        CategoriaAdapterMySql categoriaRepositorio = new CategoriaAdapterMySql();
        UsuarioAdapterMySql usuarioRepositorio = new UsuarioAdapterMySql();
        ProductoAdapterMySql productoRepositorio = new ProductoAdapterMySql();
        ListaCompraAdapterMySql listaCompraRepositorio = new ListaCompraAdapterMySql();
        DetalleListaAdapterMySql detalleListaRepositorio = new DetalleListaAdapterMySql();

        // --- Servicios ---
        RolServicioImpl rolServicioImpl = new RolServicioImpl(rolRepositorio);
        CategoriaServicioImpl categoriaServicioImpl = new CategoriaServicioImpl(categoriaRepositorio);
        UsuarioServicioImpl usuarioServicioImpl = new UsuarioServicioImpl(usuarioRepositorio);
        ProductoServicioImpl productoServicioImpl = new ProductoServicioImpl(productoRepositorio);
        ListaCompraServicioImpl listaCompraServicioImpl = new ListaCompraServicioImpl(listaCompraRepositorio);
        DetalleListaServicioImpl detalleListaServicioImpl = new DetalleListaServicioImpl(detalleListaRepositorio);
        DetalleListaProductoServicio detalleListaProductoServicio = new DetalleListaProductoServicio();

        // --- Vistas ---
        RolVista rolVista = new RolVista(rolServicioImpl);
        UsuarioVista usuarioVista = new UsuarioVista(usuarioServicioImpl, rolServicioImpl);
        CategoriaVista categoriaVista = new CategoriaVista(categoriaServicioImpl);
        ProductoVista productoVista = new ProductoVista(productoServicioImpl, categoriaServicioImpl);
        ListaCompraVista listaCompraVista = new ListaCompraVista(listaCompraServicioImpl, usuarioServicioImpl);
        DetalleListaVista detalleListaVista = new DetalleListaVista(
                detalleListaServicioImpl,
                detalleListaProductoServicio,
                productoServicioImpl
        );

        // --- Sesion de usuario ---
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
