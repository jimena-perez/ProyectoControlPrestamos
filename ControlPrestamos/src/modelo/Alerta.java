package modelo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Alerta {

    private int tiempo;
    private boolean recurrente;
    private String mensaje;
    private LocalDate fechaInicio;
    private Prestamo prestamo;

    public Alerta(int tiempo, boolean recurrente, String mensaje) {
        this.tiempo = tiempo;
        this.recurrente = recurrente;
        this.mensaje = mensaje;
        this.fechaInicio = LocalDate.now();
        this.prestamo = null;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public boolean isRecurrente() {
        return recurrente;
    }

    public void setRecurrente(boolean recurrente) {
        this.recurrente = recurrente;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public boolean debeMostrarse() {
        if (fechaInicio == null) {
            return false;
        }

        long dias = ChronoUnit.DAYS.between(fechaInicio, LocalDate.now());

        if (dias < tiempo) {
            return false;
        }

        if (recurrente) {
            return dias % tiempo == 0;
        }

        return dias == tiempo;
    }

    @Override
    public String toString() {
        return mensaje + " - Fecha inicio: " + fechaInicio;
    }
}