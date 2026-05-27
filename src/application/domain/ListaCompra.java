package application.domain;

import application.domain.enums.EstadoLista;

public class ListaCompra {

    private int id;
    private String nombre;
    private String fecha;
    private Usuario usuario;  // objeto real, no solo el ID
    private EstadoLista estado;

    public ListaCompra() {
    }

    public ListaCompra(int id, String nombre, String fecha, Usuario usuario, EstadoLista estado) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.usuario = usuario;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public EstadoLista getEstado() {
        return estado;
    }

    public void setEstado(EstadoLista estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        String nombreUsuario = (usuario != null) ? usuario.getNombre() + " " + usuario.getApellido() : "Sin usuario";
        return id + " - " + nombre + " - " + fecha + " - Estado: " + estado + " - Usuario: " + nombreUsuario;
    }
}
