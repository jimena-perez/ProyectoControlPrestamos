package prueba;

import control.Controladora;

public class Principal {

    public static void main(String[] args) {

        Controladora control = Controladora.getInstancia();

        control.agregarTipo("Libro");
        control.agregarTipo("Juego");

        control.agregarCategoria("Estudio");
        control.agregarCategoria("Entretenimiento");

        control.agregarPersona("Jimena", "8888-8888", "jimena@correo.com");
        control.agregarPersona("April", "7777-7777", "april@correo.com");

        control.agregarItem("Libro de POO", "Libro para estudiar programacion", "Libro");
        control.agregarItem("UNO", "Juego de cartas", "Juego");

        control.agregarCategoriaAItem("Libro de POO", "Estudio");
        control.agregarCategoriaAItem("UNO", "Entretenimiento");

        control.agregarPrestamo("Jimena");
        control.agregarItemAPrestamo("Jimena", "Libro de POO");
        control.agregarItemAPrestamo("Jimena", "UNO");

        control.agregarAlertaAPrestamo("Jimena", 7, false, "Recordar devolver los items");

        System.out.println(control.generarListadoElementos());
        System.out.println(control.generarReportePorUsuario());
        System.out.println(control.generarReportePorItem());
        System.out.println(control.generarReportePorCategoria());
        System.out.println(control.generarReportePorTipo());
    }
}