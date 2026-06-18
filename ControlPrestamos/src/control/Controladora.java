package control;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Datos.ArchivoDatos;
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
    private ArchivoDatos archivoDatos;

    private Controladora() {
        personas = new ArrayList<Persona>();
        prestamos = new ArrayList<Prestamo>();
        items = new ArrayList<Item>();
        tipos = new ArrayList<Tipo>();
        categorias = new ArrayList<Categoria>();
        archivoDatos = new ArchivoDatos();

        agregarTipo("Generico");
    }

    public static Controladora getInstancia() {
        if (instancia == null) {
            instancia = new Controladora();
        }

        return instancia;
    }

    // ---------------- PERSONAS ----------------

    public boolean agregarPersona(String nombre, String telefono, String correo) {
        if (nombre != null && !nombre.isEmpty() && obtenerPersona(nombre) == null) {
            Persona persona = new Persona(nombre, telefono, correo);
            personas.add(persona);
            return true;
        }

        return false;
    }

    public boolean modificarPersona(String nombre, String nuevoTelefono, String nuevoCorreo) {
        Persona persona = obtenerPersona(nombre);

        if (persona != null) {
            persona.setTelefono(nuevoTelefono);
            persona.setCorreo(nuevoCorreo);
            return true;
        }

        return false;
    }

    public boolean eliminarPersona(String nombre) {
        Persona persona = obtenerPersona(nombre);

        if (persona != null && !persona.tienePrestamosActivos()) {
            personas.remove(persona);
            return true;
        }

        return false;
    }

    public Persona obtenerPersona(String nombre) {
        if (nombre == null) {
            return null;
        }

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

    public boolean agregarItem(String codigo, String nombre, String descripcion, String nombreTipo) {
        if (codigo == null || codigo.isEmpty() || nombre == null || nombre.isEmpty()) {
            return false;
        }

        if (obtenerItem(codigo) != null || obtenerItem(nombre) != null) {
            return false;
        }

        Tipo tipo = obtenerTipo(nombreTipo);

        if (tipo == null) {
            tipo = obtenerTipo("Generico");
        }

        Item item = new Item(codigo, nombre, descripcion, tipo);
        items.add(item);
        return true;
    }

    public boolean agregarItem(String nombre, String descripcion, String nombreTipo) {
        return agregarItem(nombre, nombre, descripcion, nombreTipo);
    }

    public boolean modificarItem(String codigoONombre, String nuevaDescripcion, String nuevoTipo) {
        Item item = obtenerItem(codigoONombre);

        if (item != null) {
            item.setDescripcion(nuevaDescripcion);

            Tipo tipo = obtenerTipo(nuevoTipo);

            if (tipo != null) {
                item.setTipo(tipo);
            }

            return true;
        }

        return false;
    }

    public boolean eliminarItem(String codigoONombre) {
        Item item = obtenerItem(codigoONombre);

        if (item != null && !item.isPrestado()) {
            if (item.getTipo() != null) {
                item.getTipo().eliminarItem(item);
            }

            ArrayList<Categoria> copiaCategorias = new ArrayList<Categoria>(item.getCategorias());

            for (Categoria categoria : copiaCategorias) {
                item.eliminarCategoria(categoria);
            }

            items.remove(item);
            return true;
        }

        return false;
    }

    public Item obtenerItem(String codigoONombre) {
        if (codigoONombre == null) {
            return null;
        }

        for (Item item : items) {
            if (item.getCodigo().equalsIgnoreCase(codigoONombre) || item.getNombre().equalsIgnoreCase(codigoONombre)) {
                return item;
            }
        }

        return null;
    }

    public ArrayList<Item> obtenerListadoItems() {
        return items;
    }

    public boolean agregarCategoriaAItem(String codigoONombreItem, String nombreCategoria) {
        Item item = obtenerItem(codigoONombreItem);
        Categoria categoria = obtenerCategoria(nombreCategoria);

        if (item != null && categoria != null) {
            item.agregarCategoria(categoria);
            return true;
        }

        return false;
    }

    public boolean eliminarCategoriaDeItem(String codigoONombreItem, String nombreCategoria) {
        Item item = obtenerItem(codigoONombreItem);
        Categoria categoria = obtenerCategoria(nombreCategoria);

        if (item != null && categoria != null) {
            item.eliminarCategoria(categoria);
            return true;
        }

        return false;
    }

    // ---------------- TIPOS ----------------

    public boolean agregarTipo(String nombre) {
        if (nombre != null && !nombre.isEmpty() && obtenerTipo(nombre) == null) {
            Tipo tipo = new Tipo(nombre);
            tipos.add(tipo);
            return true;
        }

        return false;
    }

    public boolean modificarTipo(String nombreActual, String nuevoNombre) {
        Tipo tipo = obtenerTipo(nombreActual);

        if (tipo != null && nuevoNombre != null && !nuevoNombre.isEmpty()) {
            tipo.setNombre(nuevoNombre);
            return true;
        }

        return false;
    }

    public boolean eliminarTipo(String nombre) {
        Tipo tipo = obtenerTipo(nombre);

        if (tipo == null) {
            return false;
        }

        if (tipo.getNombre().equalsIgnoreCase("Generico")) {
            return false;
        }

        Tipo generico = obtenerTipo("Generico");
        ArrayList<Item> copiaItems = new ArrayList<Item>(tipo.getItems());

        for (Item item : copiaItems) {
            item.setTipo(generico);
        }

        tipos.remove(tipo);
        return true;
    }

    public Tipo obtenerTipo(String nombre) {
        if (nombre == null) {
            return null;
        }

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

    public boolean agregarCategoria(String nombre) {
        if (nombre != null && !nombre.isEmpty() && obtenerCategoria(nombre) == null) {
            Categoria categoria = new Categoria(nombre);
            categorias.add(categoria);
            return true;
        }

        return false;
    }

    public boolean modificarCategoria(String nombreActual, String nuevoNombre) {
        Categoria categoria = obtenerCategoria(nombreActual);

        if (categoria != null && nuevoNombre != null && !nuevoNombre.isEmpty()) {
            categoria.setNombre(nuevoNombre);
            return true;
        }

        return false;
    }

    public boolean eliminarCategoria(String nombre) {
        Categoria categoria = obtenerCategoria(nombre);

        if (categoria != null) {
            ArrayList<Item> copiaItems = new ArrayList<Item>(categoria.getItems());

            for (Item item : copiaItems) {
                item.eliminarCategoria(categoria);
            }

            categorias.remove(categoria);
            return true;
        }

        return false;
    }

    public Categoria obtenerCategoria(String nombre) {
        if (nombre == null) {
            return null;
        }

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

    public boolean crearPrestamoConItem(String nombrePersona, String codigoONombreItem) {
        Persona persona = obtenerPersona(nombrePersona);
        Item item = obtenerItem(codigoONombreItem);

        if (persona == null || item == null || item.isPrestado()) {
            return false;
        }

        Prestamo prestamo = obtenerPrestamoActivo(nombrePersona);

        if (prestamo == null) {
            prestamo = new Prestamo(persona);
            prestamos.add(prestamo);
        }

        prestamo.agregarItem(item);
        return true;
    }

    public boolean agregarPrestamo(String nombrePersona) {
        Persona persona = obtenerPersona(nombrePersona);

        if (persona != null && obtenerPrestamoActivo(nombrePersona) == null) {
            Prestamo prestamo = new Prestamo(persona);
            prestamos.add(prestamo);
            return true;
        }

        return false;
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

    public boolean agregarItemAPrestamo(String nombrePersona, String codigoONombreItem) {
        Prestamo prestamo = obtenerPrestamoActivo(nombrePersona);
        Item item = obtenerItem(codigoONombreItem);

        if (prestamo != null && item != null && !item.isPrestado()) {
            prestamo.agregarItem(item);
            return true;
        }

        return false;
    }

    public boolean eliminarItemDePrestamo(String nombrePersona, String codigoONombreItem) {
        Prestamo prestamo = obtenerPrestamoActivo(nombrePersona);
        Item item = obtenerItem(codigoONombreItem);

        if (prestamo != null && item != null) {
            prestamo.eliminarItem(item);
            return true;
        }

        return false;
    }

    public boolean retornarItemDePrestamo(String nombrePersona, String codigoONombreItem) {
        Prestamo prestamo = obtenerPrestamoActivo(nombrePersona);
        Item item = obtenerItem(codigoONombreItem);

        if (prestamo != null && item != null) {
            prestamo.retornarItem(item);
            return true;
        }

        return false;
    }

    public boolean finalizarPrestamo(String nombrePersona) {
        Prestamo prestamo = obtenerPrestamoActivo(nombrePersona);

        if (prestamo != null) {
            prestamo.finalizarPrestamo();
            return true;
        }

        return false;
    }

    public boolean eliminarPrestamo(String nombrePersona) {
        Prestamo prestamo = obtenerPrestamoActivo(nombrePersona);

        if (prestamo != null) {
            prestamo.finalizarPrestamo();

            if (prestamo.getPersona() != null) {
                prestamo.getPersona().eliminarPrestamo(prestamo);
            }

            prestamos.remove(prestamo);
            return true;
        }

        return false;
    }

    public boolean agregarAlertaAPrestamo(String nombrePersona, int tiempo, boolean recurrente, String mensaje) {
        Prestamo prestamo = obtenerPrestamoActivo(nombrePersona);

        if (prestamo != null && tiempo > 0 && mensaje != null && !mensaje.isEmpty()) {
            Alerta alerta = new Alerta(tiempo, recurrente, mensaje, prestamo.getFechaPrestamo());
            prestamo.setAlerta(alerta);
            return true;
        }

        return false;
    }

    public ArrayList<Prestamo> obtenerListadoPrestamos() {
        return prestamos;
    }

    public String revisarAlertas() {
        String texto = "";

        for (Prestamo prestamo : prestamos) {
            if (!prestamo.isFinalizado() && prestamo.getAlerta() != null && prestamo.getAlerta().debeMostrarse()) {
                texto += "Persona: " + prestamo.getPersona().getNombre() + "\n";
                texto += "Mensaje: " + prestamo.getAlerta().getMensaje() + "\n";
                texto += "Fecha del préstamo: " + prestamo.getFechaPrestamo() + "\n\n";
            }
        }

        return texto;
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
            texto += "- " + item + " | Categorias: " + item.categoriasComoTexto() + "\n";
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

                    if (prestamo.getAlerta() != null) {
                        texto += "Alerta: " + prestamo.getAlerta() + "\n";
                    }

                    if (prestamo.getItems().isEmpty()) {
                        texto += "Sin items en el prestamo.\n";
                    } else {
                        for (Item item : prestamo.getItems()) {
                            texto += "- " + item.getCodigo() + " | " + item.getNombre() + "\n";
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

        ArrayList<Item> copiaItems = new ArrayList<Item>(items);

        Collections.sort(copiaItems, new Comparator<Item>() {
            public int compare(Item item1, Item item2) {
                return item1.getNombre().compareToIgnoreCase(item2.getNombre());
            }
        });

        for (Item item : copiaItems) {
            texto += "\nCódigo: " + item.getCodigo() + "\n";
            texto += "Item: " + item.getNombre() + "\n";
            texto += "Descripcion: " + item.getDescripcion() + "\n";
            texto += "Tipo: " + (item.getTipo() != null ? item.getTipo().getNombre() : "Sin tipo") + "\n";
            texto += "Categorias: " + item.categoriasComoTexto() + "\n";
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
                    texto += "- " + item.getCodigo() + " | " + item.getNombre() + "\n";
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
                    texto += "- " + item.getCodigo() + " | " + item.getNombre() + "\n";
                }
            }
        }

        return texto;
    }

    // ---------------- ARCHIVOS ----------------

    public void guardarDatos() {
        guardarPersonas();
        guardarTipos();
        guardarCategorias();
        guardarItems();
        guardarPrestamos();
    }

    private void guardarPersonas() {
        ArrayList<String> lineas = new ArrayList<String>();

        for (Persona persona : personas) {
            String linea = persona.getNombre() + ";" + persona.getTelefono() + ";" + persona.getCorreo();
            lineas.add(linea);
        }

        archivoDatos.guardarLineas("personas.txt", lineas);
    }

    private void guardarTipos() {
        ArrayList<String> lineas = new ArrayList<String>();

        for (Tipo tipo : tipos) {
            lineas.add(tipo.getNombre());
        }

        archivoDatos.guardarLineas("tipos.txt", lineas);
    }

    private void guardarCategorias() {
        ArrayList<String> lineas = new ArrayList<String>();

        for (Categoria categoria : categorias) {
            lineas.add(categoria.getNombre());
        }

        archivoDatos.guardarLineas("categorias.txt", lineas);
    }

    private void guardarItems() {
        ArrayList<String> lineas = new ArrayList<String>();

        for (Item item : items) {
            String nombreTipo = "Generico";

            if (item.getTipo() != null) {
                nombreTipo = item.getTipo().getNombre();
            }

            String categoriasTexto = "";

            for (int i = 0; i < item.getCategorias().size(); i++) {
                categoriasTexto += item.getCategorias().get(i).getNombre();

                if (i < item.getCategorias().size() - 1) {
                    categoriasTexto += ",";
                }
            }

            String linea = item.getCodigo() + ";" + item.getNombre() + ";" + item.getDescripcion() + ";" + nombreTipo + ";" + categoriasTexto;
            lineas.add(linea);
        }

        archivoDatos.guardarLineas("items.txt", lineas);
    }

    private void guardarPrestamos() {
        ArrayList<String> lineas = new ArrayList<String>();

        for (Prestamo prestamo : prestamos) {
            if (!prestamo.isFinalizado() && prestamo.getPersona() != null) {
                String itemsTexto = "";

                for (int i = 0; i < prestamo.getItems().size(); i++) {
                    itemsTexto += prestamo.getItems().get(i).getCodigo();

                    if (i < prestamo.getItems().size() - 1) {
                        itemsTexto += ",";
                    }
                }

                String alertaTiempo = "";
                String alertaRecurrente = "";
                String alertaMensaje = "";
                String alertaFecha = "";

                if (prestamo.getAlerta() != null) {
                    Alerta alerta = prestamo.getAlerta();
                    alertaTiempo = String.valueOf(alerta.getTiempo());
                    alertaRecurrente = String.valueOf(alerta.isRecurrente());
                    alertaMensaje = alerta.getMensaje().replace(";", ",");
                    alertaFecha = String.valueOf(alerta.getFechaInicio());
                }

                String linea = prestamo.getPersona().getNombre() + ";"
                        + prestamo.getFechaPrestamo() + ";"
                        + itemsTexto + ";"
                        + alertaTiempo + ";"
                        + alertaRecurrente + ";"
                        + alertaMensaje + ";"
                        + alertaFecha;

                lineas.add(linea);
            }
        }

        archivoDatos.guardarLineas("prestamos.txt", lineas);
    }

    public void cargarDatos() {
        personas.clear();
        prestamos.clear();
        items.clear();
        tipos.clear();
        categorias.clear();

        agregarTipo("Generico");

        cargarTipos();
        cargarCategorias();
        cargarPersonas();
        cargarItems();
        cargarPrestamos();
    }

    private void cargarTipos() {
        ArrayList<String> lineas = archivoDatos.leerLineas("tipos.txt");

        for (String linea : lineas) {
            if (!linea.equalsIgnoreCase("Generico")) {
                agregarTipo(linea);
            }
        }
    }

    private void cargarCategorias() {
        ArrayList<String> lineas = archivoDatos.leerLineas("categorias.txt");

        for (String linea : lineas) {
            agregarCategoria(linea);
        }
    }

    private void cargarPersonas() {
        ArrayList<String> lineas = archivoDatos.leerLineas("personas.txt");

        for (String linea : lineas) {
            String[] datos = linea.split(";", -1);

            if (datos.length == 3) {
                agregarPersona(datos[0], datos[1], datos[2]);
            }
        }
    }

    private void cargarItems() {
        ArrayList<String> lineas = archivoDatos.leerLineas("items.txt");

        for (String linea : lineas) {
            String[] datos = linea.split(";", -1);

            if (datos.length >= 4) {
                String codigo = datos[0];
                String nombre = datos[1];
                String descripcion = datos[2];
                String tipo = datos[3];

                agregarItem(codigo, nombre, descripcion, tipo);

                if (datos.length >= 5 && !datos[4].isEmpty()) {
                    String[] categoriasItem = datos[4].split(",");

                    for (String nombreCategoria : categoriasItem) {
                        agregarCategoriaAItem(codigo, nombreCategoria);
                    }
                }
            }
        }
    }

    private void cargarPrestamos() {
        ArrayList<String> lineas = archivoDatos.leerLineas("prestamos.txt");

        for (String linea : lineas) {
            String[] datos = linea.split(";", -1);

            if (datos.length >= 3) {
                String nombrePersona = datos[0];
                LocalDate fechaPrestamo = LocalDate.parse(datos[1]);
                String itemsTexto = datos[2];

                Persona persona = obtenerPersona(nombrePersona);

                if (persona != null) {
                    Prestamo prestamo = new Prestamo(persona, fechaPrestamo);

                    if (!itemsTexto.isEmpty()) {
                        String[] codigosItems = itemsTexto.split(",");

                        for (String codigoItem : codigosItems) {
                            Item item = obtenerItem(codigoItem);

                            if (item != null && !item.isPrestado()) {
                                prestamo.agregarItem(item);
                            }
                        }
                    }

                    if (datos.length >= 7 && !datos[3].isEmpty()) {
                        int tiempo = Integer.parseInt(datos[3]);
                        boolean recurrente = Boolean.parseBoolean(datos[4]);
                        String mensaje = datos[5];
                        LocalDate fechaAlerta = LocalDate.parse(datos[6]);

                        Alerta alerta = new Alerta(tiempo, recurrente, mensaje, fechaAlerta);
                        prestamo.setAlerta(alerta);
                    }

                    prestamos.add(prestamo);
                }
            }
        }
    }
}