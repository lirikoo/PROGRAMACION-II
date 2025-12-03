package practica6_biblioteca;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        // 1. Configuración de la Ventana
        setTitle("Sistema Bibliotecario UMSA");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 2. Fondo y Título (Panel Central)
        // Usamos un color de fondo elegante en vez de imagen para evitar errores de ruta
        JPanel panelFondo = new JPanel();
        panelFondo.setLayout(new GridBagLayout());
        panelFondo.setBackground(new Color(44, 62, 80)); // Un azul oscuro profesional

        JLabel lblTitulo = new JLabel("BIBLIOTECA UNIVERSITARIA");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(Color.WHITE);
        
        JLabel lblSubtitulo = new JLabel("Sistema de Gestión y Préstamos");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblSubtitulo.setForeground(new Color(189, 195, 199)); // Gris claro

        // Agregamos titulo y subtitulo al centro
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.insets = new Insets(0, 0, 10, 0);
        panelFondo.add(lblTitulo, gbc);
        
        gbc.gridy = 1;
        panelFondo.add(lblSubtitulo, gbc);

        add(panelFondo, BorderLayout.CENTER);

        // 3. Botones Grandes (Panel Inferior)
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelBotones.setBackground(Color.WHITE);

        JButton btnAbrir = crearBoton("ABRIR GESTIÓN", new Color(52, 152, 219));
        JButton btnSalir = crearBoton("SALIR", new Color(231, 76, 60));

        panelBotones.add(btnAbrir);
        panelBotones.add(btnSalir);

        add(panelBotones, BorderLayout.SOUTH);

        // --- ACCIONES ---
        btnAbrir.addActionListener(e -> {
            // Abre la otra ventana y cierra esta (o la oculta)
            new SistemaPantalla().setVisible(true);
            this.dispose(); // Cierra el menú
        });

        btnSalir.addActionListener(e -> System.exit(0));
    }

    // Método auxiliar para hacer botones bonitos rápido
    private JButton crearBoton(String texto, Color colorFondo) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(colorFondo);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(180, 50));
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuPrincipal().setVisible(true));
    }
}