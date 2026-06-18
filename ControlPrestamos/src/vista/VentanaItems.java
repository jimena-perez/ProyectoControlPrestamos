package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import control.Controladora;

public class VentanaItems extends JFrame {

    private static final long serialVersionUID = 1L;

    private Controladora control;

    private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JTextField txtTipo;
    private JTextField txtCategoria;

    private BotonRedondo btnAgregar;
    private BotonRedondo btnModificar;
    private BotonRedondo btnEliminar;
    private BotonRedondo btnAgregarCategoria;
    private BotonRedondo btnEliminarCategoria;
    private BotonRedondo btnConsultar;
    private BotonRedondo btnLimpiar;
    private BotonRedondo btnCerrar;

    public VentanaItems() {
        control = Controladora.getInstancia();

        setTitle("Administrar Objetos");
        setSize(900, 650);
        setMinimumSize(new Dimension(760, 580));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);

        inicializarComponentes();
        agregarEventos();
    }

    private void inicializarComponentes() {
        Color rosaFondo = new Color(255, 228, 236);
        Color rosaBoton = new Color(244, 182, 200);
        Color rosaHover = new Color(236, 160, 186);
        Color textoOscuro = new Color(90, 60, 75);

        getContentPane().setBackground(rosaFondo);
        setLayout(new BorderLayout());

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(rosaFondo);
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 45, 30, 45));

        JLabel titulo = new JLabel("Administrar Objetos");
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titulo.setForeground(textoOscuro);

        JLabel ayuda = new JLabel("Registre los objetos que luego podrán prestarse a una persona.");
        ayuda.setAlignmentX(CENTER_ALIGNMENT);
        ayuda.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        ayuda.setForeground(new Color(130, 80, 100));

        JLabel nota = new JLabel("Ejemplos de objetos: Libro de POO, UNO, CD de música, DVD, juego de mesa.");
        nota.setAlignmentX(CENTER_ALIGNMENT);
        nota.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        nota.setForeground(new Color(130, 80, 100));

        panelPrincipal.add(titulo);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));
        panelPrincipal.add(ayuda);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 5)));
        panelPrincipal.add(nota);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 35)));

        JPanel panelFormulario = new JPanel();
        panelFormulario.setBackground(rosaFondo);
        panelFormulario.setLayout(new GridLayout(4, 2, 12, 12));
        panelFormulario.setMaximumSize(new Dimension(820, 190));

        txtNombre = crearCampo("Ejemplo: Libro de POO");
        txtDescripcion = crearCampo("Ejemplo: Libro para estudiar programación");
        txtTipo = crearCampo("Ejemplo: Libro, Juego, CD, DVD");
        txtCategoria = crearCampo("Ejemplo: Estudio, Entretenimiento, Universidad");

        panelFormulario.add(crearEtiqueta("Nombre del objeto:"));
        panelFormulario.add(txtNombre);

        panelFormulario.add(crearEtiqueta("Descripción del objeto:"));
        panelFormulario.add(txtDescripcion);

        panelFormulario.add(crearEtiqueta("Tipo del objeto:"));
        panelFormulario.add(txtTipo);

        panelFormulario.add(crearEtiqueta("Categoría del objeto:"));
        panelFormulario.add(txtCategoria);

        panelPrincipal.add(panelFormulario);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 15)));

        JLabel notaTipo = new JLabel("Tipo: indica qué clase de objeto es. Categoría: ayuda a clasificarlo según su uso.");
        notaTipo.setAlignmentX(CENTER_ALIGNMENT);
        notaTipo.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        notaTipo.setForeground(new Color(130, 80, 100));

        JLabel notaRegistro = new JLabel("Antes de registrar un objeto, se recomienda crear su tipo y categoría en la sección anterior.");
        notaRegistro.setAlignmentX(CENTER_ALIGNMENT);
        notaRegistro.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        notaRegistro.setForeground(new Color(130, 80, 100));

        panelPrincipal.add(notaTipo);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 5)));
        panelPrincipal.add(notaRegistro);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 35)));

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(rosaFondo);
        panelBotones.setLayout(new GridLayout(2, 4, 14, 14));
        panelBotones.setMaximumSize(new Dimension(820, 130));

        btnAgregar = new BotonRedondo("Registrar objeto", rosaBoton, rosaHover);
        btnModificar = new BotonRedondo("Actualizar objeto", rosaBoton, rosaHover);
        btnEliminar = new BotonRedondo("Eliminar objeto", rosaBoton, rosaHover);
        btnAgregarCategoria = new BotonRedondo("Asignar categoría", rosaBoton, rosaHover);
        btnEliminarCategoria = new BotonRedondo("Quitar categoría", rosaBoton, rosaHover);
        btnConsultar = new BotonRedondo("Ver objetos", rosaBoton, rosaHover);
        btnLimpiar = new BotonRedondo("Limpiar campos", rosaBoton, rosaHover);
        btnCerrar = new BotonRedondo("Cerrar", new Color(230, 140, 170), new Color(220, 120, 155));

        panelBotones.add(btnAgregar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnAgregarCategoria);
        panelBotones.add(btnEliminarCategoria);
        panelBotones.add(btnConsultar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnCerrar);

        panelPrincipal.add(panelBotones);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 25)));

        JLabel explicacion = new JLabel("Use el botón “Ver objetos” para consultar la lista de objetos registrados.");
        explicacion.setAlignmentX(CENTER_ALIGNMENT);
        explicacion.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        explicacion.setForeground(new Color(130, 80, 100));

        panelPrincipal.add(explicacion);

        add(panelPrincipal, BorderLayout.CENTER);
    }

    private JLabel crearEtiqueta(String texto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setFont(new Font("Segoe UI", Font.BOLD, 15));
        etiqueta.setForeground(new Color(90, 60, 75));
        return etiqueta;
    }

    private JTextField crearCampo(String ayuda) {
        JTextField campo = new JTextField();
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campo.setForeground(new Color(90, 60, 75));
        campo.setBackground(new Color(255, 245, 248));
        campo.setToolTipText(ayuda);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 170, 190), 1),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)
        ));
        return campo;
    }

    private void agregarEventos() {
        btnAgregar.addActionListener(e -> agregarItem());
        btnModificar.addActionListener(e -> modificarItem());
        btnEliminar.addActionListener(e -> eliminarItem());
        btnAgregarCategoria.addActionListener(e -> agregarCategoriaAItem());
        btnEliminarCategoria.addActionListener(e -> eliminarCategoriaDeItem());
        btnConsultar.addActionListener(e -> mostrarItems());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnCerrar.addActionListener(e -> dispose());
    }

    private void agregarItem() {
        String nombre = txtNombre.getText();
        String descripcion = txtDescripcion.getText();
        String tipo = txtTipo.getText();
        String categoria = txtCategoria.getText();

        if (nombre.isEmpty() || descripcion.isEmpty() || tipo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe completar nombre, descripción y tipo del objeto.");
            return;
        }

        control.agregarItem(nombre, descripcion, tipo);

        if (!categoria.isEmpty()) {
            control.agregarCategoriaAItem(nombre, categoria);
        }

        JOptionPane.showMessageDialog(this, "Objeto registrado correctamente.");

        limpiarCampos();
    }

    private void modificarItem() {
        String nombre = txtNombre.getText();
        String descripcion = txtDescripcion.getText();
        String tipo = txtTipo.getText();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe escribir el nombre del objeto que desea actualizar.");
            return;
        }

        if (descripcion.isEmpty() || tipo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe escribir la nueva descripción y el tipo.");
            return;
        }

        control.modificarItem(nombre, descripcion, tipo);

        JOptionPane.showMessageDialog(this, "Objeto actualizado correctamente.");

        limpiarCampos();
    }

    private void eliminarItem() {
        String nombre = txtNombre.getText();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe escribir el nombre del objeto que desea eliminar.");
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que desea eliminar el objeto " + nombre + "?\nNo se eliminará si está prestado.",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            control.eliminarItem(nombre);
            JOptionPane.showMessageDialog(this, "Proceso de eliminación realizado.");
            limpiarCampos();
        }
    }

    private void agregarCategoriaAItem() {
        String nombreItem = txtNombre.getText();
        String nombreCategoria = txtCategoria.getText();

        if (nombreItem.isEmpty() || nombreCategoria.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe escribir el nombre del objeto y la categoría que desea asignar.");
            return;
        }

        control.agregarCategoriaAItem(nombreItem, nombreCategoria);

        JOptionPane.showMessageDialog(this, "Categoría asignada al objeto.");

        limpiarCampos();
    }

    private void eliminarCategoriaDeItem() {
        String nombreItem = txtNombre.getText();
        String nombreCategoria = txtCategoria.getText();

        if (nombreItem.isEmpty() || nombreCategoria.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe escribir el nombre del objeto y la categoría que desea quitar.");
            return;
        }

        control.eliminarCategoriaDeItem(nombreItem, nombreCategoria);

        JOptionPane.showMessageDialog(this, "Categoría quitada del objeto.");

        limpiarCampos();
    }

    private void mostrarItems() {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        String texto = "OBJETOS REGISTRADOS\n";
        texto += "-----------------------------\n\n";
        texto += "Este reporte muestra si los objetos están disponibles o prestados.\n\n";
        texto += control.generarReportePorItem();

        area.setText(texto);

        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(650, 400));

        JOptionPane.showMessageDialog(this, scroll, "Objetos Registrados", JOptionPane.INFORMATION_MESSAGE);
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtTipo.setText("");
        txtCategoria.setText("");
    }

    class BotonRedondo extends JButton {

        private static final long serialVersionUID = 1L;

        private Color colorNormal;
        private Color colorHover;
        private boolean mouseEncima;

        public BotonRedondo(String texto, Color colorNormal, Color colorHover) {
            super(texto);
            this.colorNormal = colorNormal;
            this.colorHover = colorHover;
            this.mouseEncima = false;

            setFocusPainted(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setForeground(new Color(90, 60, 75));
            setFont(new Font("Segoe UI", Font.BOLD, 13));
            setCursor(new Cursor(Cursor.HAND_CURSOR));

            addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    mouseEncima = true;
                    repaint();
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    mouseEncima = false;
                    repaint();
                }
            });
        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (mouseEncima) {
                g2.setColor(colorHover);
            } else {
                g2.setColor(colorNormal);
            }

            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 35, 35);

            g2.setColor(new Color(255, 255, 255, 180));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 35, 35);

            g2.dispose();

            super.paintComponent(g);
        }
    }
}