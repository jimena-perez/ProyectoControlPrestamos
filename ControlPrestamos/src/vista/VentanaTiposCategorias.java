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

public class VentanaTiposCategorias extends JFrame {

    private static final long serialVersionUID = 1L;

    private Controladora control;

    private JTextField txtTipoActual;
    private JTextField txtTipoNuevo;
    private JTextField txtCategoriaActual;
    private JTextField txtCategoriaNueva;

    private BotonRedondo btnAgregarTipo;
    private BotonRedondo btnModificarTipo;
    private BotonRedondo btnEliminarTipo;
    private BotonRedondo btnAgregarCategoria;
    private BotonRedondo btnModificarCategoria;
    private BotonRedondo btnEliminarCategoria;
    private BotonRedondo btnConsultar;
    private BotonRedondo btnLimpiar;
    private BotonRedondo btnCerrar;

    public VentanaTiposCategorias() {
        control = Controladora.getInstancia();

        setTitle("Tipos y Categorías");
        setSize(950, 650);
        setMinimumSize(new Dimension(820, 600));
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
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(35, 55, 35, 55));

        JLabel titulo = new JLabel("Administrar Tipos y Categorías");
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titulo.setForeground(textoOscuro);

        JLabel ayuda = new JLabel("Los tipos y categorías ayudan a ordenar los objetos antes de prestarlos.");
        ayuda.setAlignmentX(CENTER_ALIGNMENT);
        ayuda.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        ayuda.setForeground(new Color(130, 80, 100));

        JLabel nota = new JLabel("Ejemplos de tipo: Libro, Juego, DVD. Ejemplos de categoría: Estudio, Entretenimiento, Universidad.");
        nota.setAlignmentX(CENTER_ALIGNMENT);
        nota.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        nota.setForeground(new Color(130, 80, 100));

        panelPrincipal.add(titulo);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));
        panelPrincipal.add(ayuda);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 5)));
        panelPrincipal.add(nota);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 35)));

        JPanel panelFormularios = new JPanel();
        panelFormularios.setBackground(rosaFondo);
        panelFormularios.setLayout(new GridLayout(1, 2, 25, 0));
        panelFormularios.setMaximumSize(new Dimension(900, 185));

        JPanel panelTipos = crearPanelSeccion("Tipos de objeto");
        txtTipoActual = crearCampo("Ejemplo: Libro");
        txtTipoNuevo = crearCampo("Ejemplo: Material académico");

        panelTipos.add(crearEtiqueta("Nombre actual o nuevo tipo:"));
        panelTipos.add(txtTipoActual);
        panelTipos.add(crearEtiqueta("Nuevo nombre si desea modificar:"));
        panelTipos.add(txtTipoNuevo);

        JPanel panelCategorias = crearPanelSeccion("Categorías");
        txtCategoriaActual = crearCampo("Ejemplo: Estudio");
        txtCategoriaNueva = crearCampo("Ejemplo: Universidad");

        panelCategorias.add(crearEtiqueta("Nombre actual o nueva categoría:"));
        panelCategorias.add(txtCategoriaActual);
        panelCategorias.add(crearEtiqueta("Nuevo nombre si desea modificar:"));
        panelCategorias.add(txtCategoriaNueva);

        panelFormularios.add(panelTipos);
        panelFormularios.add(panelCategorias);

        panelPrincipal.add(panelFormularios);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 35)));

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(rosaFondo);
        panelBotones.setLayout(new GridLayout(3, 3, 16, 16));
        panelBotones.setMaximumSize(new Dimension(900, 165));

        btnAgregarTipo = new BotonRedondo("Registrar tipo", rosaBoton, rosaHover);
        btnModificarTipo = new BotonRedondo("Renombrar tipo", rosaBoton, rosaHover);
        btnEliminarTipo = new BotonRedondo("Eliminar tipo", rosaBoton, rosaHover);
        btnAgregarCategoria = new BotonRedondo("Registrar categoría", rosaBoton, rosaHover);
        btnModificarCategoria = new BotonRedondo("Renombrar categoría", rosaBoton, rosaHover);
        btnEliminarCategoria = new BotonRedondo("Eliminar categoría", rosaBoton, rosaHover);
        btnConsultar = new BotonRedondo("Ver tipos y categorías", rosaBoton, rosaHover);
        btnLimpiar = new BotonRedondo("Limpiar campos", rosaBoton, rosaHover);
        btnCerrar = new BotonRedondo("Cerrar", new Color(230, 140, 170), new Color(220, 120, 155));

        panelBotones.add(btnAgregarTipo);
        panelBotones.add(btnModificarTipo);
        panelBotones.add(btnEliminarTipo);
        panelBotones.add(btnAgregarCategoria);
        panelBotones.add(btnModificarCategoria);
        panelBotones.add(btnEliminarCategoria);
        panelBotones.add(btnConsultar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnCerrar);

        panelPrincipal.add(panelBotones);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 25)));

        JLabel explicacion = new JLabel("Use el botón “Ver tipos y categorías” para consultar la información registrada.");
        explicacion.setAlignmentX(CENTER_ALIGNMENT);
        explicacion.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        explicacion.setForeground(new Color(130, 80, 100));

        panelPrincipal.add(explicacion);

        add(panelPrincipal, BorderLayout.CENTER);
    }

    private JPanel crearPanelSeccion(String titulo) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 228, 236));
        panel.setLayout(new GridLayout(4, 1, 8, 8));
        panel.setBorder(BorderFactory.createTitledBorder(titulo));
        return panel;
    }

    private JLabel crearEtiqueta(String texto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setFont(new Font("Segoe UI", Font.BOLD, 14));
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
        btnAgregarTipo.addActionListener(e -> agregarTipo());
        btnModificarTipo.addActionListener(e -> modificarTipo());
        btnEliminarTipo.addActionListener(e -> eliminarTipo());
        btnAgregarCategoria.addActionListener(e -> agregarCategoria());
        btnModificarCategoria.addActionListener(e -> modificarCategoria());
        btnEliminarCategoria.addActionListener(e -> eliminarCategoria());
        btnConsultar.addActionListener(e -> mostrarDatos());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnCerrar.addActionListener(e -> dispose());
    }

    private void agregarTipo() {
        String nombre = txtTipoActual.getText();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe escribir el nombre del tipo que desea registrar.");
            return;
        }

        control.agregarTipo(nombre);
        JOptionPane.showMessageDialog(this, "Tipo registrado correctamente.");
        limpiarCampos();
    }

    private void modificarTipo() {
        String actual = txtTipoActual.getText();
        String nuevo = txtTipoNuevo.getText();

        if (actual.isEmpty() || nuevo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe escribir el nombre actual y el nuevo nombre del tipo.");
            return;
        }

        control.modificarTipo(actual, nuevo);
        JOptionPane.showMessageDialog(this, "Tipo renombrado correctamente.");
        limpiarCampos();
    }

    private void eliminarTipo() {
        String nombre = txtTipoActual.getText();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe escribir el nombre del tipo que desea eliminar.");
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que desea eliminar el tipo " + nombre + "?\nLos objetos asociados pasarán a tipo Genérico.",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            control.eliminarTipo(nombre);
            JOptionPane.showMessageDialog(this, "Proceso de eliminación realizado.");
            limpiarCampos();
        }
    }

    private void agregarCategoria() {
        String nombre = txtCategoriaActual.getText();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe escribir el nombre de la categoría que desea registrar.");
            return;
        }

        control.agregarCategoria(nombre);
        JOptionPane.showMessageDialog(this, "Categoría registrada correctamente.");
        limpiarCampos();
    }

    private void modificarCategoria() {
        String actual = txtCategoriaActual.getText();
        String nuevo = txtCategoriaNueva.getText();

        if (actual.isEmpty() || nuevo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe escribir el nombre actual y el nuevo nombre de la categoría.");
            return;
        }

        control.modificarCategoria(actual, nuevo);
        JOptionPane.showMessageDialog(this, "Categoría renombrada correctamente.");
        limpiarCampos();
    }

    private void eliminarCategoria() {
        String nombre = txtCategoriaActual.getText();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe escribir el nombre de la categoría que desea eliminar.");
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que desea eliminar la categoría " + nombre + "?\nSe quitará de los objetos asociados.",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            control.eliminarCategoria(nombre);
            JOptionPane.showMessageDialog(this, "Proceso de eliminación realizado.");
            limpiarCampos();
        }
    }

    private void mostrarDatos() {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        area.setText("TIPOS Y CATEGORÍAS REGISTRADAS\n"
                + "-----------------------------\n\n"
                + control.generarReportePorTipo()
                + "\n"
                + control.generarReportePorCategoria());

        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(600, 350));

        JOptionPane.showMessageDialog(this, scroll, "Tipos y Categorías Registradas", JOptionPane.INFORMATION_MESSAGE);
    }

    private void limpiarCampos() {
        txtTipoActual.setText("");
        txtTipoNuevo.setText("");
        txtCategoriaActual.setText("");
        txtCategoriaNueva.setText("");
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
            g2.setColor(mouseEncima ? colorHover : colorNormal);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 35, 35);
            g2.setColor(new Color(255, 255, 255, 180));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 35, 35);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}