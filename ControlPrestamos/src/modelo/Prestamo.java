package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Prestamo {

    private Persona persona;
    private ArrayList<Item> items;
    private Alerta alerta;
    private LocalDate fechaPrestamo;
    private boolean finalizado;

    public Prestamo(Persona persona) {
        this(persona, LocalDate.now());
    }

    public Prestamo(Persona persona, LocalDate fechaPrestamo) {
        this.persona = persona;
        this.items = new ArrayList<Item>();
        this.fechaPrestamo = fechaPrestamo;
        this.finalizado = false;
        this.alerta = null;

        if (persona != null) {
            persona.agregarPrestamo(this);
        }
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        if (this.persona != null) {
            this.persona.eliminarPrestamo(this);
        }

        this.persona = persona;

        if (persona != null) {
            persona.agregarPrestamo(this);
        }
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void agregarItem(Item item) {
        if (item != null && !item.isPrestado() && !finalizado && !items.contains(item)) {
            items.add(item);
            item.setPrestado(true);
            item.setPrestamo(this);
        }
    }

    public void eliminarItem(Item item) {
        if (item != null && items.contains(item) && !finalizado) {
            items.remove(item);
            item.setPrestado(false);
            item.setPrestamo(null);
        }
    }

    public void retornarItem(Item item) {
        if (item != null && items.contains(item)) {
            items.remove(item);
            item.setPrestado(false);
            item.setPrestamo(null);
        }
    }

    public void finalizarPrestamo() {
        for (int i = items.size() - 1; i >= 0; i--) {
            retornarItem(items.get(i));
        }

        finalizado = true;
    }

    public Alerta getAlerta() {
        return alerta;
    }

    public void setAlerta(Alerta alerta) {
        this.alerta = alerta;

        if (alerta != null) {
            alerta.setPrestamo(this);

            if (alerta.getFechaInicio() == null) {
                alerta.setFechaInicio(fechaPrestamo);
            }
        }
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    @Override
    public String toString() {
        String nombrePersona = "Sin persona";

        if (persona != null) {
            nombrePersona = persona.getNombre();
        }

        String estado = finalizado ? "Finalizado" : "Activo";

        return "Prestamo de " + nombrePersona + " - " + fechaPrestamo + " - " + estado;
    }
}