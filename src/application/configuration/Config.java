package application.configuration;

import application.service.*;
import application.service.outputs.UsuarioServicio;
import application.userinterface.MenuApp;
import application.userinterface.SesionUsuarioMenu;
import application.repositorio.*;
import application.vista.*;

public class Config {

    public static MenuApp createMenuApp() {

        RolRepositorio rolRepositorio = new RolRepositorio();
        RolServicioImpl rolServicioImpl = new RolServicioImpl(rolRepositorio);
        RolVista rolVista = new RolVista(rolServicioImpl);

        UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();
        UsuarioServicioImpl usuarioServicioImpl = new UsuarioServicioImpl(usuarioRepositorio);
        UsuarioVista usuarioVista = new UsuarioVista(usuarioServicioImpl);

        CategoriaRepositorio categoriaRepositorio = new CategoriaRepositorio();
        CategoriaServicioImpl categoriaServicioImpl = new CategoriaServicioImpl(categoriaRepositorio);
        CategoriaVista categoriaVista = new CategoriaVista(categoriaServicioImpl);

        ProductoRepositorio productoRepositorio = new ProductoRepositorio();
        ProductoServicioImpl productoServicioImpl = new ProductoServicioImpl(productoRepositorio);
        ProductoVista productoVista = new ProductoVista(productoServicioImpl);
        DetalleListaProductoServicio detalleListaProductoServicio = new DetalleListaProductoServicio(productoServicioImpl);

        ListaCompraRepositorio listaCompraRepositorio = new ListaCompraRepositorio();
        ListaCompraServicioImpl listaCompraServicioImpl = new ListaCompraServicioImpl(listaCompraRepositorio);
        ListaCompraVista listaCompraVista = new ListaCompraVista(listaCompraServicioImpl);

        DetalleListaRepositorio detalleListaRepositorio = new DetalleListaRepositorio();
        DetalleListaServicioImpl detalleListaServicioImpl = new DetalleListaServicioImpl(detalleListaRepositorio);
        DetalleListaVista detalleListaVista = new DetalleListaVista(detalleListaServicioImpl, detalleListaProductoServicio);

        UsuarioServicio usuarioServicio = usuarioServicioImpl;
        SesionUsuarioMenu sesionUsuarioMenu = new SesionUsuarioMenu(
                listaCompraServicioImpl,
                detalleListaServicioImpl,
                detalleListaProductoServicio
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