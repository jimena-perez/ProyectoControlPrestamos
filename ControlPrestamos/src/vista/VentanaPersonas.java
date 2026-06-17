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

public class VentanaPersonas extends JFrame {

    private static final long serialVersionUID = 1L;

    private Controladora control;

    private JTextField txtNombre;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JTextArea areaPersonas;

    private BotonRedondo btnAgregar;
    private BotonRedondo btnModificar;
    private BotonRedondo btnEliminar;
    private BotonRedondo btnConsultar;
    private BotonRedondo btnLimpiar;
    private BotonRedondo btnCerrar;

    public VentanaPersonas() {
        control = Controladora.getInstancia();

        setTitle("Administrar Personas");
        setSize(700, 600);
        setMinimumSize(new Dimension(600, 520));
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

        JLabel titulo = new JLabel("Administrar Personas");
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(textoOscuro);

        panelPrincipal.add(titulo);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 25)));

        JPanel panelFormulario = new JPanel();
        panelFormulario.setBackground(rosaFondo);
        panelFormulario.setLayout(new GridLayout(3, 2, 12, 12));
        panelFormulario.setMaximumSize(new Dimension(600, 130));

        JLabel lblNombre = crearEtiqueta("Nombre:");
        JLabel lblTelefono = crearEtiqueta("Teléfono:");
        JLabel lblCorreo = crearEtiqueta("Correo:");

        txtNombre = crearCampo();
        txtTelefono = crearCampo();
        txtCorreo = crearCampo();

        panelFormulario.add(lblNombre);
        panelFormulario.add(txtNombre);
        panelFormulario.add(lblTelefono);
        panelFormulario.add(txtTelefono);
        panelFormulario.add(lblCorreo);
        panelFormulario.add(txtCorreo);

        panelPrincipal.add(panelFormulario);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 25)));

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(rosaFondo);
        panelBotones.setLayout(new GridLayout(2, 3, 12, 12));
        panelBotones.setMaximumSize(new Dimension(600, 120));

        btnAgregar = new BotonRedondo("Agregar", rosaBoton, rosaHover);
        btnModificar = new BotonRedondo("Modificar", rosaBoton, rosaHover);
        btnEliminar = new BotonRedondo("Eliminar", rosaBoton, rosaHover);
        btnConsultar = new BotonRedondo("Consultar", rosaBoton, rosaHover);
        btnLimpiar = new BotonRedondo("Limpiar", rosaBoton, rosaHover);
        btnCerrar = new BotonRedondo("Cerrar", new Color(230, 140, 170), new Color(220, 120, 155));

        panelBotones.add(btnAgregar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnConsultar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnCerrar);

        panelPrincipal.add(panelBotones);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 25)));

        areaPersonas = new JTextArea();
        areaPersonas.setEditable(false);
        areaPersonas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        areaPersonas.setForeground(textoOscuro);
        areaPersonas.setBackground(new Color(255, 245, 248));
        areaPersonas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scroll = new JScrollPane(areaPersonas);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(230, 170, 190), 1));
        scroll.setPreferredSize(new Dimension(600, 230));

        panelPrincipal.add(scroll);

        add(panelPrincipal, BorderLayout.CENTER);
    }

    private JLabel crearEtiqueta(String texto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setFont(new Font("Segoe UI", Font.BOLD, 15));
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
        btnAgregar.addActionListener(e -> agregarPersona());
        btnModificar.addActionListener(e -> modificarPersona());
        btnEliminar.addActionListener(e -> eliminarPersona());
        btnConsultar.addActionListener(e -> mostrarPersonas());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnCerrar.addActionListener(e -> dispose());
    }

    private void agregarPersona() {
        String nombre = txtNombre.getText();
        String telefono = txtTelefono.getText();
        String correo = txtCorreo.getText();

        if (nombre.isEmpty() || telefono.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe completar todos los campos.");
            return;
        }

        control.agregarPersona(nombre, telefono, correo);
        JOptionPane.showMessageDialog(this, "Persona agregada correctamente.");

        limpiarCampos();
        mostrarPersonas();
    }

    private void modificarPersona() {
        String nombre = txtNombre.getText();
        String telefono = txtTelefono.getText();
        String correo = txtCorreo.getText();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe escribir el nombre de la persona a modificar.");
            return;
        }

        if (telefono.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe escribir el nuevo teléfono y correo.");
            return;
        }

        control.modificarPersona(nombre, telefono, correo);
        JOptionPane.showMessageDialog(this, "Persona modificada correctamente.");

        limpiarCampos();
        mostrarPersonas();
    }

    private void eliminarPersona() {
        String nombre = txtNombre.getText();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe escribir el nombre de la persona a eliminar.");
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que desea eliminar a " + nombre + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            control.eliminarPersona(nombre);
            JOptionPane.showMessageDialog(this, "Proceso de eliminación realizado.");
            limpiarCampos();
            mostrarPersonas();
        }
    }

    private void mostrarPersonas() {
        areaPersonas.setText(control.generarReportePorUsuario());
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
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
            setFont(new Font("Segoe UI", Font.BOLD, 14));
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