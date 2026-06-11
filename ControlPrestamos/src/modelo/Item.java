package modelo;

import java.util.ArrayList;

public class Item {

    private String nombre;
    private String descripcion;
    private boolean prestado;
    private Tipo tipo;
    private ArrayList<Categoria> categorias;
    private Prestamo prestamo;

    public Item(String nombre, String descripcion, Tipo tipo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.prestado = false;
        this.categorias = new ArrayList<Categoria>();
        this.tipo = tipo;

        if (tipo != null) {
            tipo.agregarItem(this);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isPrestado() {
        return prestado;
    }

    public void setPrestado(boolean prestado) {
        this.prestado = prestado;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public void agregarCategoria(Categoria categoria) {
        if (categoria != null && !categorias.contains(categoria)) {
            categorias.add(categoria);
            categoria.agregarItem(this);
        }
    }

    public void eliminarCategoria(Categoria categoria) {
        if (categoria != null) {
            categorias.remove(categoria);
            categoria.eliminarItem(this);
        }
    }

    @Override
    public String toString() {
        String estado = prestado ? "Prestado" : "Disponible";

        return nombre + " - " + descripcion + " - " + estado;
    }
}