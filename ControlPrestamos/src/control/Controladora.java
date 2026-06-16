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

        agregarTipo("Generico");
    }

    public static Controladora getInstancia() {
        if (instancia == null) {
            instancia = new Controladora();
        }

        return instancia;
    }

    // ---------------- PERSONAS ----------------

    public void agregarPersona(String nombre, String telefono, String correo) {
        if (nombre != null && obtenerPersona(nombre) == null) {
            Persona persona = new Persona(nombre, telefono, correo);
            personas.add(persona);
        }
    }

    public void modificarPersona(String nombre, String nuevoTelefono, String nuevoCorreo) {
        Persona persona = obtenerPersona(nombre);

        if (persona != null) {
            persona.setTelefono(nuevoTelefono);
            persona.setCorreo(nuevoCorreo);
        }
    }

    public void eliminarPersona(String nombre) {
        Persona persona = obtenerPersona(nombre);

        if (persona != null && !persona.tienePrestamosActivos()) {
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

    // ---------------- ITEMS ----------------

    public void agregarItem(String nombre, String descripcion, String nombreTipo) {
        if (nombre == null || obtenerItem(nombre) != null) {
            return;
        }

        Tipo tipo = obtenerTipo(nombreTipo);

        if (tipo == null) {
            tipo = obtenerTipo("Generico");
        }

        Item item = new Item(nombre, descripcion, tipo);
        items.add(item);
    }

    public void modificarItem(String nombre, String nuevaDescripcion, String nuevoTipo) {
        Item item = obtenerItem(nombre);

        if (item != null) {
            item.setDescripcion(nuevaDescripcion);

            Tipo tipo = obtenerTipo(nuevoTipo);

            if (tipo != null) {
                item.setTipo(tipo);
            }
        }
    }

    public void eliminarItem(String nombre) {
        Item item = obtenerItem(nombre);

        if (item != null && !item.isPrestado()) {
            if (item.getTipo() != null) {
                item.getTipo().eliminarItem(item);
            }

            ArrayList<Categoria> copiaCategorias = new ArrayList<Categoria>(item.getCategorias());

            for (Categoria categoria : copiaCategorias) {
                item.eliminarCategoria(categoria);
            }

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

    public void agregarCategoriaAItem(String nombreItem, String nombreCategoria) {
        Item item = obtenerItem(nombreItem);
        Categoria categoria = obtenerCategoria(nombreCategoria);

        if (item != null && categoria != null) {
            item.agregarCategoria(categoria);
        }
    }

    public void eliminarCategoriaDeItem(String nombreItem, String nombreCategoria) {
        Item item = obtenerItem(nombreItem);
        Categoria categoria = obtenerCategoria(nombreCategoria);

        if (item != null && categoria != null) {
            item.eliminarCategoria(categoria);
        }
    }

    // ---------------- TIPOS ----------------

    public void agregarTipo(String nombre) {
        if (nombre != null && obtenerTipo(nombre) == null) {
            Tipo tipo = new Tipo(nombre);
            tipos.add(tipo);
        }
    }

    public void modificarTipo(String nombreActual, String nuevoNombre) {
        Tipo tipo = obtenerTipo(nombreActual);

        if (tipo != null) {
            tipo.setNombre(nuevoNombre);
        }
    }

    public void eliminarTipo(String nombre) {
        Tipo tipo = obtenerTipo(nombre);

        if (tipo == null) {
            return;
        }

        if (tipo.getNombre().equalsIgnoreCase("Generico")) {
            return;
        }

        Tipo generico = obtenerTipo("Generico");
        ArrayList<Item> copiaItems = new ArrayList<Item>(tipo.getItems());

        for (Item item : copiaItems) {
            item.setTipo(generico);
        }

        tipos.remove(tipo);
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

    // ---------------- CATEGORIAS ----------------

    public void agregarCategoria(String nombre) {
        if (nombre != null && obtenerCategoria(nombre) == null) {
            Categoria categoria = new Categoria(nombre);
            categorias.add(categoria);
        }
    }

    public void modificarCategoria(String nombreActual, String nuevoNombre) {
        Categoria categoria = obtenerCategoria(nombreActual);

        if (categoria != null) {
            categoria.setNombre(nuevoNombre);
        }
    }

    public void eliminarCategoria(String nombre) {
        Categoria categoria = obtenerCategoria(nombre);

        if (categoria != null) {
            ArrayList<Item> copiaItems = new ArrayList<Item>(categoria.getItems());

            for (Item item : copiaItems) {
                item.eliminarCategoria(categoria);
            }

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

    // ---------------- PRESTAMOS ----------------

    public void agregarPrestamo(String nombrePersona) {
        Persona persona = obtenerPersona(nombrePersona);

        if (persona != null) {
            Prestamo prestamo = new Prestamo(persona);
            prestamos.add(prestamo);
        }
    }

    public Prestamo obtenerPrestamoActivo(String nombrePersona) {
        Persona persona = obtenerPersona(nombrePersona);

        if (persona == null) {
            return null;
        }

        for (Prestamo prestamo : persona.getPrestamos()) {
            if (!prestamo.isFinalizado()) {
                return prestamo;
            }
        }

        return null;
    }

    public void agregarItemAPrestamo(String nombrePersona, String nombreItem) {
        Prestamo prestamo = obtenerPrestamoActivo(nombrePersona);
        Item item = obtenerItem(nombreItem);

        if (prestamo != null && item != null) {
            prestamo.agregarItem(item);
        }
    }

    public void eliminarItemDePrestamo(String nombrePersona, String nombreItem) {
        Prestamo prestamo = obtenerPrestamoActivo(nombrePersona);
        Item item = obtenerItem(nombreItem);

        if (prestamo != null && item != null) {
            prestamo.eliminarItem(item);
        }
    }

    public void retornarItemDePrestamo(String nombrePersona, String nombreItem) {
        Prestamo prestamo = obtenerPrestamoActivo(nombrePersona);
        Item item = obtenerItem(nombreItem);

        if (prestamo != null && item != null) {
            prestamo.retornarItem(item);
        }
    }

    public void finalizarPrestamo(String nombrePersona) {
        Prestamo prestamo = obtenerPrestamoActivo(nombrePersona);

        if (prestamo != null) {
            prestamo.finalizarPrestamo();
        }
    }

    public void eliminarPrestamo(String nombrePersona) {
        Prestamo prestamo = obtenerPrestamoActivo(nombrePersona);

        if (prestamo != null) {
            prestamo.finalizarPrestamo();

            if (prestamo.getPersona() != null) {
                prestamo.getPersona().eliminarPrestamo(prestamo);
            }

            prestamos.remove(prestamo);
        }
    }

    public void agregarAlertaAPrestamo(String nombrePersona, int tiempo, boolean recurrente, String mensaje) {
        Prestamo prestamo = obtenerPrestamoActivo(nombrePersona);

        if (prestamo != null) {
            Alerta alerta = new Alerta(tiempo, recurrente, mensaje);
            prestamo.setAlerta(alerta);
        }
    }

    public ArrayList<Prestamo> obtenerListadoPrestamos() {
        return prestamos;
    }

    // ---------------- REPORTES ----------------

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

                    if (prestamo.getItems().isEmpty()) {
                        texto += "Sin items en el prestamo.\n";
                    } else {
                        for (Item item : prestamo.getItems()) {
                            texto += "- " + item.getNombre() + "\n";
                        }
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