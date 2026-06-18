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
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import control.Controladora;

public class VentanaPrestamos extends JFrame {

    private static final long serialVersionUID = 1L;

    private Controladora control;

    private JTextField txtPersona;
    private JTextField txtItem;
    private JTextField txtTiempoAlerta;
    private JTextField txtMensajeAlerta;
    private JCheckBox chkRecurrente;

    private BotonRedondo btnCrearPrestamo;
    private BotonRedondo btnAgregarItem;
    private BotonRedondo btnRetornarItem;
    private BotonRedondo btnFinalizarPrestamo;
    private BotonRedondo btnEliminarPrestamo;
    private BotonRedondo btnAgregarAlerta;
    private BotonRedondo btnConsultar;
    private BotonRedondo btnLimpiar;
    private BotonRedondo btnCerrar;

    public VentanaPrestamos() {
        control = Controladora.getInstancia();

        setTitle("Administrar Prestamos");
        setSize(900, 780);
        setMinimumSize(new Dimension(760, 700));
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

        JLabel titulo = new JLabel("Administrar Prestamos");
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(textoOscuro);

        JLabel ayuda = new JLabel("Registre qué persona tiene prestado un objeto y controle su devolución.");
        ayuda.setAlignmentX(CENTER_ALIGNMENT);
        ayuda.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        ayuda.setForeground(new Color(130, 80, 100));

        panelPrincipal.add(titulo);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 8)));
        panelPrincipal.add(ayuda);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 25)));

        JPanel panelFormulario = new JPanel();
        panelFormulario.setBackground(rosaFondo);
        panelFormulario.setLayout(new GridLayout(5, 2, 12, 12));
        panelFormulario.setMaximumSize(new Dimension(780, 230));

        txtPersona = crearCampo("Ejemplo: Jimena");
        txtItem = crearCampo("Código o nombre. Ejemplo: LIB001 o Libro de POO");
        txtTiempoAlerta = crearCampo("Ejemplo: 7");
        txtMensajeAlerta = crearCampo("Ejemplo: Recordar devolver el libro");

        chkRecurrente = new JCheckBox("Recordatorio recurrente");
        chkRecurrente.setBackground(rosaFondo);
        chkRecurrente.setForeground(textoOscuro);
        chkRecurrente.setFont(new Font("Segoe UI", Font.BOLD, 14));

        panelFormulario.add(crearEtiqueta("Persona que recibe el préstamo:"));
        panelFormulario.add(txtPersona);

        panelFormulario.add(crearEtiqueta("Objeto prestado:"));
        panelFormulario.add(txtItem);

        panelFormulario.add(crearEtiqueta("Recordatorio en días:"));
        panelFormulario.add(txtTiempoAlerta);

        panelFormulario.add(crearEtiqueta("Mensaje del recordatorio:"));
        panelFormulario.add(txtMensajeAlerta);

        panelFormulario.add(crearEtiqueta("Tipo de recordatorio:"));
        panelFormulario.add(chkRecurrente);

        panelPrincipal.add(panelFormulario);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 12)));

        JLabel nota = new JLabel("Para crear un préstamo debe indicar una persona y un objeto ya registrado.");
        nota.setAlignmentX(CENTER_ALIGNMENT);
        nota.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        nota.setForeground(new Color(130, 80, 100));

        JLabel notaAlerta = new JLabel("La alerta sirve para recordar cuándo revisar o devolver el préstamo.");
        notaAlerta.setAlignmentX(CENTER_ALIGNMENT);
        notaAlerta.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        notaAlerta.setForeground(new Color(130, 80, 100));

        panelPrincipal.add(nota);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 5)));
        panelPrincipal.add(notaAlerta);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 25)));

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(rosaFondo);
        panelBotones.setLayout(new GridLayout(3, 3, 12, 12));
        panelBotones.setMaximumSize(new Dimension(800, 150));

        btnCrearPrestamo = new BotonRedondo("Crear préstamo con objeto", rosaBoton, rosaHover);
        btnAgregarItem = new BotonRedondo("Agregar otro objeto", rosaBoton, rosaHover);
        btnRetornarItem = new BotonRedondo("Devolver objeto", rosaBoton, rosaHover);
        btnFinalizarPrestamo = new BotonRedondo("Finalizar préstamo", rosaBoton, rosaHover);
        btnEliminarPrestamo = new BotonRedondo("Eliminar préstamo", rosaBoton, rosaHover);
        btnAgregarAlerta = new BotonRedondo("Agregar recordatorio", rosaBoton, rosaHover);
        btnConsultar = new BotonRedondo("Consultar préstamos", rosaBoton, rosaHover);
        btnLimpiar = new BotonRedondo("Limpiar campos", rosaBoton, rosaHover);
        btnCerrar = new BotonRedondo("Cerrar", new Color(230, 140, 170), new Color(220, 120, 155));

        panelBotones.add(btnCrearPrestamo);
        panelBotones.add(btnAgregarItem);
        panelBotones.add(btnRetornarItem);
        panelBotones.add(btnFinalizarPrestamo);
        panelBotones.add(btnEliminarPrestamo);
        panelBotones.add(btnAgregarAlerta);
        panelBotones.add(btnConsultar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnCerrar);

        panelPrincipal.add(panelBotones);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 25)));

        JLabel explicacion = new JLabel("Use “Consultar préstamos” para ver quién tiene objetos prestados.");
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

    private JTextField crearCampo(String textoAyuda) {
        JTextField campo = new JTextField();
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campo.setForeground(new Color(90, 60, 75));
        campo.setBackground(new Color(255, 245, 248));
        campo.setToolTipText(textoAyuda);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 170, 190), 1),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)
        ));
        return campo;
    }

    private void agregarEventos() {
        btnCrearPrestamo.addActionListener(e -> crearPrestamo());
        btnAgregarItem.addActionListener(e -> agregarItemAPrestamo());
        btnRetornarItem.addActionListener(e -> retornarItem());
        btnFinalizarPrestamo.addActionListener(e -> finalizarPrestamo());
        btnEliminarPrestamo.addActionListener(e -> eliminarPrestamo());
        btnAgregarAlerta.addActionListener(e -> agregarAlerta());
        btnConsultar.addActionListener(e -> mostrarPrestamos());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnCerrar.addActionListener(e -> dispose());
    }

    private void crearPrestamo() {
        String persona = txtPersona.getText();
        String item = txtItem.getText();

        if (persona.isEmpty() || item.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe escribir la persona y el objeto que se va a prestar.");
            return;
        }

        boolean creado = control.crearPrestamoConItem(persona, item);

        if (creado) {
            JOptionPane.showMessageDialog(this, "Préstamo creado correctamente con el objeto indicado.");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo crear. Revise que la persona exista, el objeto exista y esté disponible.");
        }
    }

    private void agregarItemAPrestamo() {
        String persona = txtPersona.getText();
        String item = txtItem.getText();

        if (persona.isEmpty() || item.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe escribir la persona y el objeto que se desea agregar.");
            return;
        }

        boolean agregado = control.agregarItemAPrestamo(persona, item);

        if (agregado) {
            JOptionPane.showMessageDialog(this, "Objeto agregado al préstamo.");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo agregar. Revise que exista un préstamo activo y que el objeto esté disponible.");
        }
    }

    private void retornarItem() {
        String persona = txtPersona.getText();
        String item = txtItem.getText();

        if (persona.isEmpty() || item.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe escribir la persona y el objeto que se va a devolver.");
            return;
        }

        boolean retornado = control.retornarItemDePrestamo(persona, item);

        if (retornado) {
            JOptionPane.showMessageDialog(this, "Objeto devuelto correctamente.");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo devolver. Revise la persona, el objeto y el préstamo activo.");
        }
    }

    private void finalizarPrestamo() {
        String persona = txtPersona.getText();

        if (persona.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe escribir el nombre de la persona.");
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que desea finalizar el préstamo activo de " + persona + "?",
                "Confirmar finalización",
                JOptionPane.YES_NO_OPTION
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            boolean finalizado = control.finalizarPrestamo(persona);

            if (finalizado) {
                JOptionPane.showMessageDialog(this, "Préstamo finalizado correctamente.");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró préstamo activo para esa persona.");
            }
        }
    }

    private void eliminarPrestamo() {
        String persona = txtPersona.getText();

        if (persona.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe escribir el nombre de la persona.");
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que desea eliminar el préstamo activo de " + persona + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            boolean eliminado = control.eliminarPrestamo(persona);

            if (eliminado) {
                JOptionPane.showMessageDialog(this, "Préstamo eliminado correctamente.");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró préstamo activo para esa persona.");
            }
        }
    }

    private void agregarAlerta() {
        String persona = txtPersona.getText();
        String tiempoTexto = txtTiempoAlerta.getText();
        String mensaje = txtMensajeAlerta.getText();

        if (persona.isEmpty() || tiempoTexto.isEmpty() || mensaje.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe escribir persona, días del recordatorio y mensaje.");
            return;
        }

        try {
            int tiempo = Integer.parseInt(tiempoTexto);
            boolean recurrente = chkRecurrente.isSelected();

            boolean agregado = control.agregarAlertaAPrestamo(persona, tiempo, recurrente, mensaje);

            if (agregado) {
                JOptionPane.showMessageDialog(this, "Recordatorio agregado correctamente.");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo agregar. Revise que exista un préstamo activo.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El tiempo del recordatorio debe ser un número entero.");
        }
    }

    private void mostrarPrestamos() {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        String texto = "INFORMACIÓN DE PRÉSTAMOS\n";
        texto += "-----------------------------\n\n";
        texto += control.generarReportePorUsuario();
        texto += "\n";
        texto += control.generarReportePorItem();

        area.setText(texto);

        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(700, 420));

        JOptionPane.showMessageDialog(this, scroll, "Préstamos Registrados", JOptionPane.INFORMATION_MESSAGE);
    }

    private void limpiarCampos() {
        txtPersona.setText("");
        txtItem.setText("");
        txtTiempoAlerta.setText("");
        txtMensajeAlerta.setText("");
        chkRecurrente.setSelected(false);
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