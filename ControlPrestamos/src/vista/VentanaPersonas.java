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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import control.Controladora;
import modelo.Persona;

public class VentanaPersonas extends JFrame {

    private static final long serialVersionUID = 1L;

    private Controladora control;

    private JComboBox<String> cmbPersonas;

    private JTextField txtNombre;
    private JTextField txtTelefono;
    private JTextField txtCorreo;

    private BotonRedondo btnRegistrar;
    private BotonRedondo btnCargarSeleccion;
    private BotonRedondo btnActualizar;
    private BotonRedondo btnEliminar;
    private BotonRedondo btnConsultar;
    private BotonRedondo btnLimpiar;
    private BotonRedondo btnCerrar;

    public VentanaPersonas() {
        control = Controladora.getInstancia();

        setTitle("Administrar Personas");
        setSize(850, 700);
        setMinimumSize(new Dimension(740, 620));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);

        inicializarComponentes();
        agregarEventos();
        actualizarComboPersonas();
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

        JLabel titulo = new JLabel("Administrar Personas");
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titulo.setForeground(textoOscuro);

        JLabel ayuda = new JLabel("Registre personas o seleccione una existente para editarla o eliminarla.");
        ayuda.setAlignmentX(CENTER_ALIGNMENT);
        ayuda.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        ayuda.setForeground(new Color(130, 80, 100));

        JLabel nota = new JLabel("La selección evita tener que escribir el nombre exacto de la persona.");
        nota.setAlignmentX(CENTER_ALIGNMENT);
        nota.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        nota.setForeground(new Color(130, 80, 100));

        panelPrincipal.add(titulo);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));
        panelPrincipal.add(ayuda);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 5)));
        panelPrincipal.add(nota);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel panelSeleccion = new JPanel();
        panelSeleccion.setBackground(rosaFondo);
        panelSeleccion.setLayout(new GridLayout(1, 2, 12, 12));
        panelSeleccion.setMaximumSize(new Dimension(760, 45));

        cmbPersonas = new JComboBox<String>();
        cmbPersonas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cmbPersonas.setForeground(textoOscuro);
        cmbPersonas.setBackground(new Color(255, 245, 248));

        panelSeleccion.add(crearEtiqueta("Seleccionar persona registrada:"));
        panelSeleccion.add(cmbPersonas);

        panelPrincipal.add(panelSeleccion);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 25)));

        JPanel panelFormulario = new JPanel();
        panelFormulario.setBackground(rosaFondo);
        panelFormulario.setLayout(new GridLayout(3, 2, 12, 12));
        panelFormulario.setMaximumSize(new Dimension(760, 145));

        txtNombre = crearCampo("Ejemplo: Jimena");
        txtTelefono = crearCampo("Ejemplo: 8888-8888");
        txtCorreo = crearCampo("Ejemplo: jimena@correo.com");

        panelFormulario.add(crearEtiqueta("Nombre de la persona:"));
        panelFormulario.add(txtNombre);

        panelFormulario.add(crearEtiqueta("Teléfono:"));
        panelFormulario.add(txtTelefono);

        panelFormulario.add(crearEtiqueta("Correo electrónico:"));
        panelFormulario.add(txtCorreo);

        panelPrincipal.add(panelFormulario);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel instruccion = new JLabel("Para editar o eliminar, seleccione una persona y presione “Cargar selección”.");
        instruccion.setAlignmentX(CENTER_ALIGNMENT);
        instruccion.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        instruccion.setForeground(new Color(130, 80, 100));

        panelPrincipal.add(instruccion);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 25)));

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(rosaFondo);
        panelBotones.setLayout(new GridLayout(2, 4, 14, 14));
        panelBotones.setMaximumSize(new Dimension(780, 130));

        btnRegistrar = new BotonRedondo("Registrar persona", rosaBoton, rosaHover);
        btnCargarSeleccion = new BotonRedondo("Cargar selección", rosaBoton, rosaHover);
        btnActualizar = new BotonRedondo("Actualizar persona", rosaBoton, rosaHover);
        btnEliminar = new BotonRedondo("Eliminar persona", rosaBoton, rosaHover);
        btnConsultar = new BotonRedondo("Ver personas", rosaBoton, rosaHover);
        btnLimpiar = new BotonRedondo("Limpiar campos", rosaBoton, rosaHover);
        btnCerrar = new BotonRedondo("Cerrar", new Color(230, 140, 170), new Color(220, 120, 155));

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnCargarSeleccion);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnConsultar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnCerrar);

        panelPrincipal.add(panelBotones);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 25)));

        JLabel explicacion = new JLabel("Use “Ver personas” para consultar las personas y sus préstamos.");
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
        btnRegistrar.addActionListener(e -> registrarPersona());
        btnCargarSeleccion.addActionListener(e -> cargarPersonaSeleccionada());
        btnActualizar.addActionListener(e -> actualizarPersona());
        btnEliminar.addActionListener(e -> eliminarPersona());
        btnConsultar.addActionListener(e -> mostrarPersonas());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnCerrar.addActionListener(e -> dispose());
    }

    private void actualizarComboPersonas() {
        cmbPersonas.removeAllItems();
        cmbPersonas.addItem("Seleccione una persona");

        for (Persona persona : control.obtenerListadoPersonas()) {
            cmbPersonas.addItem(persona.getNombre());
        }
    }

    private void registrarPersona() {
        String nombre = txtNombre.getText();
        String telefono = txtTelefono.getText();
        String correo = txtCorreo.getText();

        if (nombre.isEmpty() || telefono.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe completar nombre, teléfono y correo.");
            return;
        }

        boolean agregado = control.agregarPersona(nombre, telefono, correo);

        if (agregado) {
            JOptionPane.showMessageDialog(this, "Persona registrada correctamente.");
            limpiarCampos();
            actualizarComboPersonas();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo registrar. Puede que la persona ya exista.");
        }
    }

    private void cargarPersonaSeleccionada() {
        String nombreSeleccionado = obtenerSeleccionCombo();

        if (nombreSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una persona registrada.");
            return;
        }

        Persona persona = control.obtenerPersona(nombreSeleccionado);

        if (persona != null) {
            txtNombre.setText(persona.getNombre());
            txtTelefono.setText(persona.getTelefono());
            txtCorreo.setText(persona.getCorreo());
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró la persona seleccionada.");
        }
    }

    private void actualizarPersona() {
        String nombreSeleccionado = obtenerSeleccionCombo();

        if (nombreSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una persona para actualizar.");
            return;
        }

        String telefono = txtTelefono.getText();
        String correo = txtCorreo.getText();

        if (telefono.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe escribir el nuevo teléfono y correo.");
            return;
        }

        boolean modificado = control.modificarPersona(nombreSeleccionado, telefono, correo);

        if (modificado) {
            JOptionPane.showMessageDialog(this, "Persona actualizada correctamente.");
            limpiarCampos();
            actualizarComboPersonas();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo actualizar la persona seleccionada.");
        }
    }

    private void eliminarPersona() {
        String nombreSeleccionado = obtenerSeleccionCombo();

        if (nombreSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una persona para eliminar.");
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que desea eliminar a " + nombreSeleccionado + "?\nNo se eliminará si tiene préstamos activos.",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            boolean eliminado = control.eliminarPersona(nombreSeleccionado);

            if (eliminado) {
                JOptionPane.showMessageDialog(this, "Persona eliminada correctamente.");
                limpiarCampos();
                actualizarComboPersonas();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar. Puede que tenga préstamos activos.");
            }
        }
    }

    private void mostrarPersonas() {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        String texto = "PERSONAS REGISTRADAS\n";
        texto += "-----------------------------\n\n";
        texto += control.generarReportePorUsuario();

        area.setText(texto);

        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(650, 400));

        JOptionPane.showMessageDialog(this, scroll, "Personas Registradas", JOptionPane.INFORMATION_MESSAGE);
    }

    private String obtenerSeleccionCombo() {
        Object seleccionado = cmbPersonas.getSelectedItem();

        if (seleccionado == null) {
            return null;
        }

        String texto = seleccionado.toString();

        if (texto.equals("Seleccione una persona")) {
            return null;
        }

        return texto;
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        cmbPersonas.setSelectedIndex(0);
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