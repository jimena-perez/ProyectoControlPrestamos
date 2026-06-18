package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import control.Controladora;

public class VentanaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;

    private Controladora control;

    private BotonRedondo btnPersonas;
    private BotonRedondo btnTiposCategorias;
    private BotonRedondo btnItems;
    private BotonRedondo btnPrestamos;
    private BotonRedondo btnReportes;
    private BotonRedondo btnGuardar;
    private BotonRedondo btnCargar;
    private BotonRedondo btnSalir;

    public VentanaPrincipal() {
        control = Controladora.getInstancia();
        control.cargarDatos();

        setTitle("Control de Prestamos");
        setSize(700, 760);
        setMinimumSize(new Dimension(550, 650));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        inicializarComponentes();
        agregarEventos();

        SwingUtilities.invokeLater(() -> mostrarAlertasPendientes());
    }

    private void inicializarComponentes() {
        Color rosaPanel = new Color(255, 228, 236);
        Color rosaBoton = new Color(244, 182, 200);
        Color rosaBotonHover = new Color(236, 160, 186);
        Color textoOscuro = new Color(90, 60, 75);

        getContentPane().setBackground(rosaPanel);
        setLayout(new BorderLayout());

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(rosaPanel);
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(35, 40, 30, 40));

        JLabel titulo = new JLabel("Sistema de Control de Prestamos");
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(textoOscuro);

        panelPrincipal.add(titulo);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 35)));

        btnPersonas = new BotonRedondo("Administrar personas", rosaBoton, rosaBotonHover);
        btnTiposCategorias = new BotonRedondo("Administrar tipos y categorias", rosaBoton, rosaBotonHover);
        btnItems = new BotonRedondo("Administrar objetos", rosaBoton, rosaBotonHover);
        btnPrestamos = new BotonRedondo("Administrar prestamos", rosaBoton, rosaBotonHover);
        btnReportes = new BotonRedondo("Ver reportes", rosaBoton, rosaBotonHover);
        btnGuardar = new BotonRedondo("Guardar datos", rosaBoton, rosaBotonHover);
        btnCargar = new BotonRedondo("Cargar datos", rosaBoton, rosaBotonHover);
        btnSalir = new BotonRedondo("Salir", new Color(230, 140, 170), new Color(220, 120, 155));

        agregarBoton(panelPrincipal, btnPersonas);
        agregarBoton(panelPrincipal, btnTiposCategorias);
        agregarBoton(panelPrincipal, btnItems);
        agregarBoton(panelPrincipal, btnPrestamos);
        agregarBoton(panelPrincipal, btnReportes);
        agregarBoton(panelPrincipal, btnGuardar);
        agregarBoton(panelPrincipal, btnCargar);
        agregarBoton(panelPrincipal, btnSalir);

        add(panelPrincipal, BorderLayout.CENTER);
    }

    private void agregarBoton(JPanel panel, JButton boton) {
        boton.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(boton);
        panel.add(Box.createRigidArea(new Dimension(0, 17)));
    }

    private void agregarEventos() {
        btnPersonas.addActionListener(e -> {
            VentanaPersonas ventana = new VentanaPersonas();
            ventana.setVisible(true);
        });

        btnTiposCategorias.addActionListener(e -> {
            VentanaTiposCategorias ventana = new VentanaTiposCategorias();
            ventana.setVisible(true);
        });

        btnItems.addActionListener(e -> {
            VentanaItems ventana = new VentanaItems();
            ventana.setVisible(true);
        });

        btnPrestamos.addActionListener(e -> {
            VentanaPrestamos ventana = new VentanaPrestamos();
            ventana.setVisible(true);
        });

        btnReportes.addActionListener(e -> {
            VentanaReportes ventana = new VentanaReportes();
            ventana.setVisible(true);
        });

        btnGuardar.addActionListener(e -> {
            control.guardarDatos();
            JOptionPane.showMessageDialog(null, "Datos guardados correctamente.");
        });

        btnCargar.addActionListener(e -> {
            control.cargarDatos();
            JOptionPane.showMessageDialog(null, "Datos cargados correctamente.");
            mostrarAlertasPendientes();
        });

        btnSalir.addActionListener(e -> {
            control.guardarDatos();
            System.exit(0);
        });
    }

    private void mostrarAlertasPendientes() {
        String alertas = control.revisarAlertas();

        if (alertas != null && !alertas.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    alertas,
                    "Alertas de prestamos",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
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
            setFont(new Font("Segoe UI", Font.BOLD, 16));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setPreferredSize(new Dimension(420, 55));
            setMaximumSize(new Dimension(420, 55));

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

            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 45, 45);

            g2.setColor(new Color(255, 255, 255, 180));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 45, 45);

            g2.dispose();

            super.paintComponent(g);
        }
    }
}