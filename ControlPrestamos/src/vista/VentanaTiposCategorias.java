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
import modelo.Categoria;
import modelo.Tipo;

public class VentanaTiposCategorias extends JFrame {

    private static final long serialVersionUID = 1L;

    private Controladora control;

    private JTextField txtTipo;
    private JTextField txtNuevoTipo;
    private JTextField txtCategoria;
    private JTextField txtNuevaCategoria;

    private BotonRedondo btnAgregarTipo;
    private BotonRedondo btnModificarTipo;
    private BotonRedondo btnEliminarTipo;
    private BotonRedondo btnVerTipos;

    private BotonRedondo btnAgregarCategoria;
    private BotonRedondo btnModificarCategoria;
    private BotonRedondo btnEliminarCategoria;
    private BotonRedondo btnVerCategorias;

    private BotonRedondo btnLimpiar;
    private BotonRedondo btnCerrar;

    public VentanaTiposCategorias() {
        control = Controladora.getInstancia();

        setTitle("Administrar Tipos y Categorias");
        setSize(900, 720);
        setMinimumSize(new Dimension(780, 650));
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
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(25, 40, 25, 40));

        JLabel titulo = new JLabel("Administrar Tipos y Categorias");
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titulo.setForeground(textoOscuro);

        JLabel ayuda = new JLabel("Registre, modifique o elimine tipos y categorías de los objetos.");
        ayuda.setAlignmentX(CENTER_ALIGNMENT);
        ayuda.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        ayuda.setForeground(new Color(130, 80, 100));

        panelPrincipal.add(titulo);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 8)));
        panelPrincipal.add(ayuda);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 25)));

        JPanel panelFormulario = new JPanel();
        panelFormulario.setBackground(rosaFondo);
        panelFormulario.setLayout(new GridLayout(4, 2, 12, 12));
        panelFormulario.setMaximumSize(new Dimension(800, 190));

        txtTipo = crearCampo("Ejemplo: Libro");
        txtNuevoTipo = crearCampo("Ejemplo: Material de estudio");
        txtCategoria = crearCampo("Ejemplo: Estudio");
        txtNuevaCategoria = crearCampo("Ejemplo: Universidad");

        panelFormulario.add(crearEtiqueta("Nombre del tipo:"));
        panelFormulario.add(txtTipo);

        panelFormulario.add(crearEtiqueta("Nuevo nombre del tipo:"));
        panelFormulario.add(txtNuevoTipo);

        panelFormulario.add(crearEtiqueta("Nombre de la categoría:"));
        panelFormulario.add(txtCategoria);

        panelFormulario.add(crearEtiqueta("Nuevo nombre de la categoría:"));
        panelFormulario.add(txtNuevaCategoria);

        panelPrincipal.add(panelFormulario);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 18)));

        JLabel nota = new JLabel("Para modificar, escriba el nombre actual y el nuevo nombre.");
        nota.setAlignmentX(CENTER_ALIGNMENT);
        nota.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        nota.setForeground(new Color(130, 80, 100));

        JLabel nota2 = new JLabel("El tipo Generico no se puede eliminar.");
        nota2.setAlignmentX(CENTER_ALIGNMENT);
        nota2.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        nota2.setForeground(new Color(130, 80, 100));

        panelPrincipal.add(nota);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 5)));
        panelPrincipal.add(nota2);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 25)));

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(rosaFondo);
        panelBotones.setLayout(new GridLayout(3, 4, 12, 12));
        panelBotones.setMaximumSize(new Dimension(840, 180));

        btnAgregarTipo = new BotonRedondo("Agregar tipo", rosaBoton, rosaHover);
        btnModificarTipo = new BotonRedondo("Modificar tipo", rosaBoton, rosaHover);
        btnEliminarTipo = new BotonRedondo("Eliminar tipo", rosaBoton, rosaHover);
        btnVerTipos = new BotonRedondo("Ver tipos", rosaBoton, rosaHover);

        btnAgregarCategoria = new BotonRedondo("Agregar categoría", rosaBoton, rosaHover);
        btnModificarCategoria = new BotonRedondo("Modificar categoría", rosaBoton, rosaHover);
        btnEliminarCategoria = new BotonRedondo("Eliminar categoría", rosaBoton, rosaHover);
        btnVerCategorias = new BotonRedondo("Ver categorías", rosaBoton, rosaHover);

        btnLimpiar = new BotonRedondo("Limpiar campos", rosaBoton, rosaHover);
        btnCerrar = new BotonRedondo("Cerrar", new Color(230, 140, 170), new Color(220, 120, 155));

        panelBotones.add(btnAgregarTipo);
        panelBotones.add(btnModificarTipo);
        panelBotones.add(btnEliminarTipo);
        panelBotones.add(btnVerTipos);

        panelBotones.add(btnAgregarCategoria);
        panelBotones.add(btnModificarCategoria);
        panelBotones.add(btnEliminarCategoria);
        panelBotones.add(btnVerCategorias);

        panelBotones.add(btnLimpiar);
        panelBotones.add(btnCerrar);

        panelPrincipal.add(panelBotones);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 25)));

        JLabel explicacion = new JLabel("Los tipos y categorías se usan para organizar los objetos registrados.");
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
        btnAgregarTipo.addActionListener(e -> agregarTipo());
        btnModificarTipo.addActionListener(e -> modificarTipo());
        btnEliminarTipo.addActionListener(e -> eliminarTipo());
        btnVerTipos.addActionListener(e -> mostrarTipos());

        btnAgregarCategoria.addActionListener(e -> agregarCategoria());
        btnModificarCategoria.addActionListener(e -> modificarCategoria());
        btnEliminarCategoria.addActionListener(e -> eliminarCategoria());
        btnVerCategorias.addActionListener(e -> mostrarCategorias());

        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnCerrar.addActionListener(e -> dispose());
    }

    private void agregarTipo() {
        String tipo = txtTipo.getText();

        if (tipo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe escribir el nombre del tipo.");
            return;
        }

        boolean agregado = control.agregarTipo(tipo);

        if (agregado) {
            JOptionPane.showMessageDialog(this, "Tipo agregado correctamente.");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo agregar. Puede que el tipo ya exista.");
        }
    }

    private void modificarTipo() {
        String tipoActual = txtTipo.getText();
        String tipoNuevo = txtNuevoTipo.getText();

        if (tipoActual.isEmpty() || tipoNuevo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe escribir el nombre actual y el nuevo nombre del tipo.");
            return;
        }

        boolean modificado = control.modificarTipo(tipoActual, tipoNuevo);

        if (modificado) {
            JOptionPane.showMessageDialog(this, "Tipo modificado correctamente.");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo modificar. Revise que exista, que no sea Generico o que no esté repetido.");
        }
    }

    private void eliminarTipo() {
        String tipo = txtTipo.getText();

        if (tipo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe escribir el nombre del tipo que desea eliminar.");
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que desea eliminar el tipo " + tipo + "?\nLos objetos asociados pasarán al tipo Generico.",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            boolean eliminado = control.eliminarTipo(tipo);

            if (eliminado) {
                JOptionPane.showMessageDialog(this, "Tipo eliminado correctamente.");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar. El tipo Generico no se puede borrar.");
            }
        }
    }

    private void agregarCategoria() {
        String categoria = txtCategoria.getText();

        if (categoria.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe escribir el nombre de la categoría.");
            return;
        }

        boolean agregado = control.agregarCategoria(categoria);

        if (agregado) {
            JOptionPane.showMessageDialog(this, "Categoría agregada correctamente.");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo agregar. Puede que la categoría ya exista.");
        }
    }

    private void modificarCategoria() {
        String categoriaActual = txtCategoria.getText();
        String categoriaNueva = txtNuevaCategoria.getText();

        if (categoriaActual.isEmpty() || categoriaNueva.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe escribir la categoría actual y el nuevo nombre.");
            return;
        }

        boolean modificado = control.modificarCategoria(categoriaActual, categoriaNueva);

        if (modificado) {
            JOptionPane.showMessageDialog(this, "Categoría modificada correctamente.");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo modificar. Revise que exista o que no esté repetida.");
        }
    }

    private void eliminarCategoria() {
        String categoria = txtCategoria.getText();

        if (categoria.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe escribir el nombre de la categoría que desea eliminar.");
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que desea eliminar la categoría " + categoria + "?\nLos objetos dejarán de pertenecer a esta categoría.",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            boolean eliminado = control.eliminarCategoria(categoria);

            if (eliminado) {
                JOptionPane.showMessageDialog(this, "Categoría eliminada correctamente.");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar la categoría seleccionada.");
            }
        }
    }

    private void mostrarTipos() {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        String texto = "TIPOS REGISTRADOS\n";
        texto += "-----------------------------\n\n";

        for (Tipo tipo : control.obtenerListadoTipos()) {
            texto += "- " + tipo.getNombre() + "\n";
        }

        area.setText(texto);

        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(600, 350));

        JOptionPane.showMessageDialog(this, scroll, "Tipos Registrados", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarCategorias() {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        String texto = "CATEGORÍAS REGISTRADAS\n";
        texto += "-----------------------------\n\n";

        for (Categoria categoria : control.obtenerListadoCategorias()) {
            texto += "- " + categoria.getNombre() + "\n";
        }

        area.setText(texto);

        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(600, 350));

        JOptionPane.showMessageDialog(this, scroll, "Categorías Registradas", JOptionPane.INFORMATION_MESSAGE);
    }

    private void limpiarCampos() {
        txtTipo.setText("");
        txtNuevoTipo.setText("");
        txtCategoria.setText("");
        txtNuevaCategoria.setText("");
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