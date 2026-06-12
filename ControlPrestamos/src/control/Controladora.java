package control;

import java.util.ArrayList;

import modelo.Alerta;
import modelo.Categoria;
import modelo.Item;
import modelo.Persona;
import modelo.Prestamo;
import modelo.Tipo;

public class Controladora {

    private static Controladora instancia;

    private ArrayList<Persona> personas;
    private ArrayList<Prestamo> prestamos;
    private ArrayList<Item> items;
    private ArrayList<Tipo> tipos;
    private ArrayList<Categoria> categorias;

    private Controladora() {
        personas = new ArrayList<Persona>();
        prestamos = new ArrayList<Prestamo>();
        items = new ArrayList<Item>();
        tipos = new ArrayList<Tipo>();
        categorias = new ArrayList<Categoria>();
    }

    public static Controladora getInstancia() {
        if (instancia == null) {
            instancia = new Controladora();
        }

        return instancia;
    }


    public void agregarPersona(Persona persona) {
        if (persona != null && obtenerPersona(persona.getNombre()) == null) {
            personas.add(persona);
        }
    }

    public void modificarPersona(Persona persona) {
        Persona personaEncontrada = obtenerPersona(persona.getNombre());

        if (personaEncontrada != null) {
            personaEncontrada.setTelefono(persona.getTelefono());
            personaEncontrada.setCorreo(persona.getCorreo());
        }
    }

    public void eliminarPersona(Persona persona) {
        if (persona != null && !persona.tienePrestamoActivo()) {
            personas.remove(persona);
        }
    }

    public Persona obtenerPersona(String nombre) {
        for (Persona persona : personas) {
            if (persona.getNombre().equalsIgnoreCase(nombre)) {
                return persona;
            }
        }

        return null;
    }

    public ArrayList<Persona> obtenerListadoPersonas() {
        return personas;
    }


    public void agregarItem(Item item) {
        if (item != null && obtenerItem(item.getNombre()) == null) {
            items.add(item);
        }
    }

    public void modificarItem(Item item) {
        Item itemEncontrado = obtenerItem(item.getNombre());

        if (itemEncontrado != null) {
            itemEncontrado.setDescripcion(item.getDescripcion());
            itemEncontrado.setTipo(item.getTipo());
        }
    }

    public void eliminarItem(Item item) {
        if (item != null && !item.isPrestado()) {
            items.remove(item);
        }
    }

    public Item obtenerItem(String nombre) {
        for (Item item : items) {
            if (item.getNombre().equalsIgnoreCase(nombre)) {
                return item;
            }
        }

        return null;
    }

    public ArrayList<Item> obtenerListadoItems() {
        return items;
    }

    public void agregarTipo(Tipo tipo) {
        if (tipo != null && obtenerTipo(tipo.getNombre()) == null) {
            tipos.add(tipo);
        }
    }

    public void modificarTipo(Tipo tipo) {
        Tipo tipoEncontrado = obtenerTipo(tipo.getNombre());

        if (tipoEncontrado != null) {
            tipoEncontrado.setNombre(tipo.getNombre());
        }
    }

    public void eliminarTipo(Tipo tipo) {
        if (tipo != null) {
            tipos.remove(tipo);
        }
    }

    public Tipo obtenerTipo(String nombre) {
        for (Tipo tipo : tipos) {
            if (tipo.getNombre().equalsIgnoreCase(nombre)) {
                return tipo;
            }
        }

        return null;
    }

    public ArrayList<Tipo> obtenerListadoTipos() {
        return tipos;
    }

 
    public void agregarCategoria(Categoria categoria) {
        if (categoria != null && obtenerCategoria(categoria.getNombre()) == null) {
            categorias.add(categoria);
        }
    }

    public void modificarCategoria(Categoria categoria) {
        Categoria categoriaEncontrada = obtenerCategoria(categoria.getNombre());

        if (categoriaEncontrada != null) {
            categoriaEncontrada.setNombre(categoria.getNombre());
        }
    }

    public void eliminarCategoria(Categoria categoria) {
        if (categoria != null) {
            categorias.remove(categoria);
        }
    }

    public Categoria obtenerCategoria(String nombre) {
        for (Categoria categoria : categorias) {
            if (categoria.getNombre().equalsIgnoreCase(nombre)) {
                return categoria;
            }
        }

        return null;
    }

    public ArrayList<Categoria> obtenerListadoCategorias() {
        return categorias;
    }


    public void agregarPrestamo(Prestamo prestamo) {
        if (prestamo != null && !prestamos.contains(prestamo)) {
            prestamos.add(prestamo);
        }
    }

    public Prestamo hacerPrestamo(Persona persona, ArrayList<Item> itemsPrestamo, Alerta alerta) {
        if (persona == null || itemsPrestamo == null || itemsPrestamo.isEmpty()) {
            return null;
        }

        Prestamo prestamo = new Prestamo(persona);
        prestamo.setAlerta(alerta);

        for (Item item : itemsPrestamo) {
            prestamo.agregarItem(item);
        }

        if (!prestamo.getItems().isEmpty()) {
            agregarPrestamo(prestamo);
            return prestamo;
        }

        return null;
    }

    public void eliminarPrestamo(Prestamo prestamo) {
        if (prestamo != null) {
            prestamo.finalizarPrestamo();
            prestamos.remove(prestamo);
        }
    }

    public ArrayList<Prestamo> obtenerListadoPrestamos() {
        return prestamos;
    }


    public String generarListadoElementos() {
        String texto = "LISTADO GENERAL\n";
        texto += "-------------------------\n";

        texto += "\nPersonas:\n";
        for (Persona persona : personas) {
            texto += "- " + persona + "\n";
        }

        texto += "\nItems:\n";
        for (Item item : items) {
            texto += "- " + item + "\n";
        }

        texto += "\nTipos:\n";
        for (Tipo tipo : tipos) {
            texto += "- " + tipo + "\n";
        }

        texto += "\nCategorias:\n";
        for (Categoria categoria : categorias) {
            texto += "- " + categoria + "\n";
        }

        texto += "\nPrestamos:\n";
        for (Prestamo prestamo : prestamos) {
            texto += "- " + prestamo + "\n";
        }

        return texto;
    }

    public String generarReportePorUsuario() {
        String texto = "REPORTE POR USUARIO\n";
        texto += "-------------------------\n";

        for (Persona persona : personas) {
            texto += "\nUsuario: " + persona.getNombre() + "\n";

            if (persona.getPrestamos().isEmpty()) {
                texto += "No tiene prestamos registrados.\n";
            } else {
                for (Prestamo prestamo : persona.getPrestamos()) {
                    texto += "Prestamo: " + prestamo.getFechaPrestamo() + "\n";

                    for (Item item : prestamo.getItems()) {
                        texto += "- " + item.getNombre() + "\n";
                    }
                }
            }
        }

        return texto;
    }

    public String generarReportePorItem() {
        String texto = "REPORTE POR ITEM\n";
        texto += "-------------------------\n";

        for (Item item : items) {
            texto += "\nItem: " + item.getNombre() + "\n";
            texto += "Descripcion: " + item.getDescripcion() + "\n";
            texto += "Estado: ";

            if (item.isPrestado()) {
                texto += "Prestado\n";

                if (item.getPrestamo() != null && item.getPrestamo().getPersona() != null) {
                    texto += "Prestado a: " + item.getPrestamo().getPersona().getNombre() + "\n";
                }

            } else {
                texto += "Disponible\n";
            }
        }

        return texto;
    }

    public String generarReportePorCategoria() {
        String texto = "REPORTE POR CATEGORIA\n";
        texto += "-------------------------\n";

        for (Categoria categoria : categorias) {
            texto += "\nCategoria: " + categoria.getNombre() + "\n";

            if (categoria.getItems().isEmpty()) {
                texto += "Sin items asignados.\n";
            } else {
                for (Item item : categoria.getItems()) {
                    texto += "- " + item.getNombre() + "\n";
                }
            }
        }

        return texto;
    }

    public String generarReportePorTipo() {
        String texto = "REPORTE POR TIPO\n";
        texto += "-------------------------\n";

        for (Tipo tipo : tipos) {
            texto += "\nTipo: " + tipo.getNombre() + "\n";

            if (tipo.getItems().isEmpty()) {
                texto += "Sin items asignados.\n";
            } else {
                for (Item item : tipo.getItems()) {
                    texto += "- " + item.getNombre() + "\n";
                }
            }
        }

        return texto;
    }
}