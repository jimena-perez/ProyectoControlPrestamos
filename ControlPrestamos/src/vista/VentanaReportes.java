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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import control.Controladora;

public class VentanaReportes extends JFrame {

    private static final long serialVersionUID = 1L;

    private Controladora control;

    private JTextArea areaReportes;

    private BotonRedondo btnListadoGeneral;
    private BotonRedondo btnReporteUsuario;
    private BotonRedondo btnReporteItem;
    private BotonRedondo btnReporteCategoria;
    private BotonRedondo btnReporteTipo;
    private BotonRedondo btnLimpiar;
    private BotonRedondo btnCerrar;

    public VentanaReportes() {
        control = Controladora.getInstancia();

        setTitle("Reportes del Sistema");
        setSize(900, 720);
        setMinimumSize(new Dimension(760, 650));
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

        JLabel titulo = new JLabel("Reportes del Sistema");
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(textoOscuro);

        JLabel ayuda = new JLabel("Consulte la información registrada en el sistema de préstamos.");
        ayuda.setAlignmentX(CENTER_ALIGNMENT);
        ayuda.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        ayuda.setForeground(new Color(130, 80, 100));

        JLabel nota = new JLabel("Seleccione un reporte según la información que necesita revisar.");
        nota.setAlignmentX(CENTER_ALIGNMENT);
        nota.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        nota.setForeground(new Color(130, 80, 100));

        panelPrincipal.add(titulo);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 8)));
        panelPrincipal.add(ayuda);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 5)));
        panelPrincipal.add(nota);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 25)));

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(rosaFondo);
        panelBotones.setLayout(new GridLayout(2, 4, 12, 12));
        panelBotones.setMaximumSize(new Dimension(820, 125));

        btnListadoGeneral = new BotonRedondo("Ver todo el sistema", rosaBoton, rosaHover);
        btnReporteUsuario = new BotonRedondo("Préstamos por persona", rosaBoton, rosaHover);
        btnReporteItem = new BotonRedondo("Estado de objetos", rosaBoton, rosaHover);
        btnReporteCategoria = new BotonRedondo("Objetos por categoría", rosaBoton, rosaHover);
        btnReporteTipo = new BotonRedondo("Objetos por tipo", rosaBoton, rosaHover);
        btnLimpiar = new BotonRedondo("Limpiar reporte", rosaBoton, rosaHover);
        btnCerrar = new BotonRedondo("Cerrar", new Color(230, 140, 170), new Color(220, 120, 155));

        panelBotones.add(btnListadoGeneral);
        panelBotones.add(btnReporteUsuario);
        panelBotones.add(btnReporteItem);
        panelBotones.add(btnReporteCategoria);
        panelBotones.add(btnReporteTipo);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnCerrar);

        panelPrincipal.add(panelBotones);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 25)));

        JLabel explicacion = new JLabel("El resultado del reporte seleccionado aparecerá en el cuadro inferior.");
        explicacion.setAlignmentX(CENTER_ALIGNMENT);
        explicacion.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        explicacion.setForeground(new Color(130, 80, 100));

        panelPrincipal.add(explicacion);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));

        areaReportes = new JTextArea();
        areaReportes.setEditable(false);
        areaReportes.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        areaReportes.setForeground(textoOscuro);
        areaReportes.setBackground(new Color(255, 245, 248));
        areaReportes.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        areaReportes.setText("Seleccione un botón para generar un reporte.\n\n"
                + "• Ver todo el sistema: muestra personas, objetos, tipos, categorías y préstamos.\n"
                + "• Préstamos por persona: muestra quién tiene préstamos registrados.\n"
                + "• Estado de objetos: muestra si un objeto está disponible o prestado.\n"
                + "• Objetos por categoría: agrupa los objetos según su categoría.\n"
                + "• Objetos por tipo: agrupa los objetos según su tipo.");

        JScrollPane scroll = new JScrollPane(areaReportes);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(230, 170, 190), 1));
        scroll.setPreferredSize(new Dimension(820, 430));

        panelPrincipal.add(scroll);

        add(panelPrincipal, BorderLayout.CENTER);
    }

    private void agregarEventos() {
        btnListadoGeneral.addActionListener(e -> {
            String texto = "REPORTE GENERAL DEL SISTEMA\n";
            texto += "---------------------------------\n\n";
            texto += "Este reporte muestra toda la información registrada en el sistema.\n\n";
            texto += control.generarListadoElementos();

            areaReportes.setText(texto);
        });

        btnReporteUsuario.addActionListener(e -> {
            String texto = "REPORTE DE PRÉSTAMOS POR PERSONA\n";
            texto += "---------------------------------\n\n";
            texto += "Este reporte muestra las personas registradas y los objetos que tienen prestados.\n\n";
            texto += control.generarReportePorUsuario();

            areaReportes.setText(texto);
        });

        btnReporteItem.addActionListener(e -> {
            String texto = "REPORTE DE ESTADO DE OBJETOS\n";
            texto += "---------------------------------\n\n";
            texto += "Este reporte muestra si los objetos están disponibles o prestados.\n\n";
            texto += control.generarReportePorItem();

            areaReportes.setText(texto);
        });

        btnReporteCategoria.addActionListener(e -> {
            String texto = "REPORTE DE OBJETOS POR CATEGORÍA\n";
            texto += "---------------------------------\n\n";
            texto += "Este reporte agrupa los objetos según la categoría asignada.\n\n";
            texto += control.generarReportePorCategoria();

            areaReportes.setText(texto);
        });

        btnReporteTipo.addActionListener(e -> {
            String texto = "REPORTE DE OBJETOS POR TIPO\n";
            texto += "---------------------------------\n\n";
            texto += "Este reporte agrupa los objetos según su tipo, por ejemplo libro, juego, CD o DVD.\n\n";
            texto += control.generarReportePorTipo();

            areaReportes.setText(texto);
        });

        btnLimpiar.addActionListener(e -> {
            areaReportes.setText("Seleccione un botón para generar un reporte.\n\n"
                    + "• Ver todo el sistema: muestra personas, objetos, tipos, categorías y préstamos.\n"
                    + "• Préstamos por persona: muestra quién tiene préstamos registrados.\n"
                    + "• Estado de objetos: muestra si un objeto está disponible o prestado.\n"
                    + "• Objetos por categoría: agrupa los objetos según su categoría.\n"
                    + "• Objetos por tipo: agrupa los objetos según su tipo.");
        });

        btnCerrar.addActionListener(e -> {
            dispose();
        });
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