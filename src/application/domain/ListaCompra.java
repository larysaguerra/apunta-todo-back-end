package application.domain;

import application.domain.enums.EstadoLista;

public class ListaCompra {

    private int id;
    private String nombre;
    private String fecha;
    private int usuarioId;
    private EstadoLista estado;

    public ListaCompra() {
    }

    public ListaCompra(int id, String nombre, String fecha, int usuarioId, EstadoLista estado) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.usuarioId = usuarioId;
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

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public EstadoLista getEstado() {
        return estado;
    }

    public void setEstado(EstadoLista estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return id + " - " + nombre + " - " + fecha + " - Estado: " + estado;
    }
}
