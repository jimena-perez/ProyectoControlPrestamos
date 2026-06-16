package modelo;

import java.util.ArrayList;

public class Persona {

    private String nombre;
    private String telefono;
    private String correo;
    private ArrayList<Prestamo> prestamos;

    public Persona(String nombre, String telefono, String correo) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.prestamos = new ArrayList<Prestamo>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public ArrayList<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void agregarPrestamo(Prestamo prestamo) {
        if (prestamo != null && !prestamos.contains(prestamo)) {
            prestamos.add(prestamo);
        }
    }

    public void eliminarPrestamo(Prestamo prestamo) {
        prestamos.remove(prestamo);
    }

    public boolean tienePrestamosActivos() {
        for (Prestamo prestamo : prestamos) {
            if (!prestamo.isFinalizado()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return nombre + " - " + telefono + " - " + correo;
    }
}