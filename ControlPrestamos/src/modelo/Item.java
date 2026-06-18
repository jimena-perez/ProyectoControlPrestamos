package modelo;

import java.util.ArrayList;

public class Item {

    private String codigo;
    private String nombre;
    private String descripcion;
    private boolean prestado;
    private Tipo tipo;
    private ArrayList<Categoria> categorias;
    private Prestamo prestamo;

    public Item(String codigo, String nombre, String descripcion, Tipo tipo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.prestado = false;
        this.categorias = new ArrayList<Categoria>();
        this.prestamo = null;
        setTipo(tipo);
    }

    public Item(String nombre, String descripcion, Tipo tipo) {
        this(nombre, nombre, descripcion, tipo);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
        if (this.tipo != null) {
            this.tipo.eliminarItem(this);
        }

        this.tipo = tipo;

        if (tipo != null) {
            tipo.agregarItem(this);
        }
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

    public String categoriasComoTexto() {
        if (categorias.isEmpty()) {
            return "Sin categorias";
        }

        String texto = "";

        for (int i = 0; i < categorias.size(); i++) {
            texto += categorias.get(i).getNombre();

            if (i < categorias.size() - 1) {
                texto += ", ";
            }
        }

        return texto;
    }

    @Override
    public String toString() {
        String estado = prestado ? "Prestado" : "Disponible";
        String nombreTipo = tipo != null ? tipo.getNombre() : "Sin tipo";

        return codigo + " - " + nombre + " - " + descripcion + " - " + nombreTipo + " - " + estado;
    }
}