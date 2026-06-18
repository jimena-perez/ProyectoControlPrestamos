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

    private JTextArea areaDatos;

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

        setTitle("Tipos y Categorias");
        setSize(800, 650);
        setMinimumSize(new Dimension(700, 580));
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
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(25, 35, 25, 35));

        JLabel titulo = new JLabel("Administrar Tipos y Categorias");
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(textoOscuro);

        panelPrincipal.add(titulo);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 25)));

        JPanel panelFormularios = new JPanel();
        panelFormularios.setBackground(rosaFondo);
        panelFormularios.setLayout(new GridLayout(1, 2, 20, 0));
        panelFormularios.setMaximumSize(new Dimension(720, 150));

        JPanel panelTipos = crearPanelSeccion("Tipos");
        txtTipoActual = crearCampo();
        txtTipoNuevo = crearCampo();

        panelTipos.add(crearEtiqueta("Nombre actual:"));
        panelTipos.add(txtTipoActual);
        panelTipos.add(crearEtiqueta("Nuevo nombre:"));
        panelTipos.add(txtTipoNuevo);

        JPanel panelCategorias = crearPanelSeccion("Categorias");
        txtCategoriaActual = crearCampo();
        txtCategoriaNueva = crearCampo();

        panelCategorias.add(crearEtiqueta("Nombre actual:"));
        panelCategorias.add(txtCategoriaActual);
        panelCategorias.add(crearEtiqueta("Nuevo nombre:"));
        panelCategorias.add(txtCategoriaNueva);

        panelFormularios.add(panelTipos);
        panelFormularios.add(panelCategorias);

        panelPrincipal.add(panelFormularios);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 25)));

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(rosaFondo);
        panelBotones.setLayout(new GridLayout(3, 3, 12, 12));
        panelBotones.setMaximumSize(new Dimension(720, 150));

        btnAgregarTipo = new BotonRedondo("Agregar tipo", rosaBoton, rosaHover);
        btnModificarTipo = new BotonRedondo("Modificar tipo", rosaBoton, rosaHover);
        btnEliminarTipo = new BotonRedondo("Eliminar tipo", rosaBoton, rosaHover);

        btnAgregarCategoria = new BotonRedondo("Agregar categoria", rosaBoton, rosaHover);
        btnModificarCategoria = new BotonRedondo("Modificar categoria", rosaBoton, rosaHover);
        btnEliminarCategoria = new BotonRedondo("Eliminar categoria", rosaBoton, rosaHover);

        btnConsultar = new BotonRedondo("Consultar", rosaBoton, rosaHover);
        btnLimpiar = new BotonRedondo("Limpiar", rosaBoton, rosaHover);
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

        areaDatos = new JTextArea();
        areaDatos.setEditable(false);
        areaDatos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        areaDatos.setForeground(textoOscuro);
        areaDatos.setBackground(new Color(255, 245, 248));
        areaDatos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scroll = new JScrollPane(areaDatos);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(230, 170, 190), 1));
        scroll.setPreferredSize(new Dimension(720, 230));

        panelPrincipal.add(scroll);

        add(panelPrincipal, BorderLayout.CENTER);
    }

    private JPanel crearPanelSeccion(String titulo) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 228, 236));
        panel.setLayout(new GridLayout(3, 1, 8, 8));
        panel.setBorder(BorderFactory.createTitledBorder(titulo));

        return panel;
    }

    private JLabel crearEtiqueta(String texto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setFont(new Font("Segoe UI", Font.BOLD, 14));
        etiqueta.setForeground(new Color(90, 60, 75));

        return etiqueta;
    }

    private JTextField crearCampo() {
        JTextField campo = new JTextField();
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campo.setForeground(new Color(90, 60, 75));
        campo.setBackground(new Color(255, 245, 248));
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
            JOptionPane.showMessageDialog(this, "Debe escribir el nombre del tipo.");
            return;
        }

        control.agregarTipo(nombre);
        JOptionPane.showMessageDialog(this, "Tipo agregado correctamente.");

        limpiarCampos();
        mostrarDatos();
    }

    private void modificarTipo() {
        String nombreActual = txtTipoActual.getText();
        String nuevoNombre = txtTipoNuevo.getText();

        if (nombreActual.isEmpty() || nuevoNombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe escribir el nombre actual y el nuevo nombre del tipo.");
            return;
        }

        control.modificarTipo(nombreActual, nuevoNombre);
        JOptionPane.showMessageDialog(this, "Tipo modificado correctamente.");

        limpiarCampos();
        mostrarDatos();
    }

    private void eliminarTipo() {
        String nombre = txtTipoActual.getText();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe escribir el nombre del tipo a eliminar.");
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que desea eliminar el tipo " + nombre + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            control.eliminarTipo(nombre);
            JOptionPane.showMessageDialog(this, "Proceso de eliminación realizado.");
            limpiarCampos();
            mostrarDatos();
        }
    }

    private void agregarCategoria() {
        String nombre = txtCategoriaActual.getText();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe escribir el nombre de la categoria.");
            return;
        }

        control.agregarCategoria(nombre);
        JOptionPane.showMessageDialog(this, "Categoria agregada correctamente.");

        limpiarCampos();
        mostrarDatos();
    }

    private void modificarCategoria() {
        String nombreActual = txtCategoriaActual.getText();
        String nuevoNombre = txtCategoriaNueva.getText();

        if (nombreActual.isEmpty() || nuevoNombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe escribir el nombre actual y el nuevo nombre de la categoria.");
            return;
        }

        control.modificarCategoria(nombreActual, nuevoNombre);
        JOptionPane.showMessageDialog(this, "Categoria modificada correctamente.");

        limpiarCampos();
        mostrarDatos();
    }

    private void eliminarCategoria() {
        String nombre = txtCategoriaActual.getText();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe escribir el nombre de la categoria a eliminar.");
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que desea eliminar la categoria " + nombre + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            control.eliminarCategoria(nombre);
            JOptionPane.showMessageDialog(this, "Proceso de eliminación realizado.");
            limpiarCampos();
            mostrarDatos();
        }
    }

    private void mostrarDatos() {
        String texto = "";

        texto += control.generarReportePorTipo();
        texto += "\n";
        texto += control.generarReportePorCategoria();

        areaDatos.setText(texto);
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
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    mouseEncima = true;
                    repaint();
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    mouseEncima = false;
                    repaint();
                }
            });
        }

        @Override
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