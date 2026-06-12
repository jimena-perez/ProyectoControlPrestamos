package prueba;

import java.util.ArrayList;

import control.Controladora;
import modelo.Alerta;
import modelo.Categoria;
import modelo.Item;
import modelo.Persona;
import modelo.Tipo;

public class Principal {

    public static void main(String[] args) {

        Controladora control = Controladora.getInstancia();

        Tipo libro = new Tipo("Libro");
        Tipo juego = new Tipo("Juego");

        control.agregarTipo(libro);
        control.agregarTipo(juego);

        Categoria estudio = new Categoria("Estudio");
        Categoria entretenimiento = new Categoria("Entretenimiento");

        control.agregarCategoria(estudio);
        control.agregarCategoria(entretenimiento);

        Persona persona = new Persona("Jimena", "8888-8888", "jimena@correo.com");
        control.agregarPersona(persona);

        Item item1 = new Item("Libro de POO", "Libro para estudiar programacion", libro);
        item1.agregarCategoria(estudio);

        Item item2 = new Item("UNO", "Juego de cartas", juego);
        item2.agregarCategoria(entretenimiento);

        control.agregarItem(item1);
        control.agregarItem(item2);

        ArrayList<Item> itemsPrestamo = new ArrayList<Item>();
        itemsPrestamo.add(item1);
        itemsPrestamo.add(item2);

        Alerta alerta = new Alerta(7, false, "Recordar devolver los items");

        control.hacerPrestamo(persona, itemsPrestamo, alerta);

        System.out.println(control.generarListadoElementos());
        System.out.println(control.generarReportePorUsuario());
        System.out.println(control.generarReportePorItem());
        System.out.println(control.generarReportePorCategoria());
        System.out.println(control.generarReportePorTipo());
    }
}