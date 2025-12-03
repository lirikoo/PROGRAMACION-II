package practica6_biblioteca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SistemaPantalla extends JFrame {

    private Biblioteca biblioteca;
    private final String ARCHIVO_DATOS = "biblioteca.json";

    // Componentes
    private JTextField txtTitulo, txtIsbn, txtAutorNombre, txtAutorNacion;
    private JTextField txtEstudiante, txtMatricula, txtIsbnPrestar;
    private JTextArea areaSalida;

    public SistemaPantalla() {
        // Cargar datos al inicio
        cargarDatos();
        if (biblioteca == null) {
            biblioteca = new Biblioteca("Biblioteca Central");
            // Datos por defecto
            biblioteca.agregarAutor(new Autor("Gabriel G. Marquez", "COL"));
            biblioteca.agregarLibro(new Libro("Cien Anios de Soledad", "978-01", null));
        }

        setTitle("Practica 6 - Biblioteca"); // Titulo simple
        setSize(800, 700); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        // Usamos un diseño simple
        setLayout(new BorderLayout());

        inicializarComponentes();
        mostrarInventario(); // Muestra los datos al abrir

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                salirYGuardar();
            }
        });
    }

    private void inicializarComponentes() {
        // --- PANEL SUPERIOR: CAMPOS DE TEXTO ---
        JPanel panelEntradas = new JPanel(new GridLayout(0, 2, 5, 5));
        
        // SECCION 1: REGISTRO
        panelEntradas.add(new JLabel("--- REGISTRO LIBRO ---"));
        panelEntradas.add(new JLabel("")); // Espacio vacio
        
        panelEntradas.add(new JLabel("Titulo Libro:"));
        txtTitulo = new JTextField();
        panelEntradas.add(txtTitulo);
        
        panelEntradas.add(new JLabel("ISBN Libro:"));
        txtIsbn = new JTextField();
        panelEntradas.add(txtIsbn);
        
        panelEntradas.add(new JLabel("Nombre Autor:"));
        txtAutorNombre = new JTextField();
        panelEntradas.add(txtAutorNombre);
        
        panelEntradas.add(new JLabel("Nacionalidad:"));
        txtAutorNacion = new JTextField();
        panelEntradas.add(txtAutorNacion);
        
        // Boton simple para guardar
        JButton btnGuardar = new JButton("Guardar Libro y Autor");
        panelEntradas.add(new JLabel("")); 
        panelEntradas.add(btnGuardar);
        
        // SEPARADOR VISUAL
        panelEntradas.add(new JSeparator());
        panelEntradas.add(new JSeparator());

        // SECCION 2: PRESTAMO
        panelEntradas.add(new JLabel("--- PRESTAMOS ---"));
        panelEntradas.add(new JLabel(""));
        
        panelEntradas.add(new JLabel("Estudiante:"));
        txtEstudiante = new JTextField();
        panelEntradas.add(txtEstudiante);
        
        panelEntradas.add(new JLabel("Matricula:"));
        txtMatricula = new JTextField();
        panelEntradas.add(txtMatricula);
        
        panelEntradas.add(new JLabel("ISBN a Prestar/Devolver:"));
        txtIsbnPrestar = new JTextField();
        panelEntradas.add(txtIsbnPrestar);
        
        // Panelito extra para poner los botones de prestar juntos
        JPanel panelBotonesPrestamo = new JPanel(new FlowLayout());
        JButton btnPrestar = new JButton("Prestar");
        JButton btnDevolver = new JButton("Devolver");
        panelBotonesPrestamo.add(btnPrestar);
        panelBotonesPrestamo.add(btnDevolver);
        
        panelEntradas.add(new JLabel("Acciones:"));
        panelEntradas.add(panelBotonesPrestamo);

        add(panelEntradas, BorderLayout.NORTH);

        // --- CENTRO: AREA DE TEXTO ---
        areaSalida = new JTextArea();
        areaSalida.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaSalida);
        add(scroll, BorderLayout.CENTER);

        // --- ABAJO: BOTONES GENERALES ---
        JPanel panelAbajo = new JPanel(new FlowLayout());
        JButton btnMostrar = new JButton("Actualizar Lista");
        JButton btnSalir = new JButton("Salir");
        
        panelAbajo.add(btnMostrar);
        panelAbajo.add(btnSalir);
        add(panelAbajo, BorderLayout.SOUTH);

        // --- EVENTOS ---
        
        // 1. Guardar
        btnGuardar.addActionListener(e -> {
            String t = txtTitulo.getText();
            String i = txtIsbn.getText();
            String a = txtAutorNombre.getText();
            String n = txtAutorNacion.getText();
            
            if(!t.isEmpty() && !i.isEmpty() && !a.isEmpty()) {
                biblioteca.agregarAutor(new Autor(a, n));
                biblioteca.agregarLibro(new Libro(t, i, null));
                JOptionPane.showMessageDialog(this, "Registrado.");
                
                txtTitulo.setText(""); txtIsbn.setText("");
                txtAutorNombre.setText(""); txtAutorNacion.setText("");
                mostrarInventario();
            } else {
                JOptionPane.showMessageDialog(this, "Faltan datos.");
            }
        });

        // 2. Prestar
        btnPrestar.addActionListener(e -> {
            String est = txtEstudiante.getText();
            String mat = txtMatricula.getText();
            String isbn = txtIsbnPrestar.getText();
            
            if(!est.isEmpty() && !isbn.isEmpty()) {
                Libro lib = buscarLibro(isbn);
                if(lib != null) {
                    biblioteca.prestarLibro(new Estudiante(mat, est), lib);
                    JOptionPane.showMessageDialog(this, "Prestado.");
                    mostrarInventario();
                } else {
                    JOptionPane.showMessageDialog(this, "ISBN no existe.");
                }
            }
        });

        // 3. Devolver
        btnDevolver.addActionListener(e -> {
            String isbn = txtIsbnPrestar.getText();
            if(!isbn.isEmpty()) {
                boolean ok = biblioteca.devolverLibro(isbn);
                if(ok) {
                    JOptionPane.showMessageDialog(this, "Libro devuelto.");
                    mostrarInventario();
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontro prestamo con ese ISBN.");
                }
            }
        });

        // 4. Mostrar y Salir
        btnMostrar.addActionListener(e -> mostrarInventario());
        btnSalir.addActionListener(e -> salirYGuardar());
    }

    private void mostrarInventario() {
        areaSalida.setText("");
        areaSalida.append("=== ESTADO BIBLIOTECA ===\n\n");
        
        areaSalida.append("AUTORES:\n");
        if(biblioteca.getAutores() != null) {
            for(Autor a : biblioteca.getAutores()) {
                areaSalida.append("- " + a.getNombre() + " (" + a.getNacionalidad() + ")\n");
            }
        }
        
        areaSalida.append("\nLIBROS:\n");
        if(biblioteca.getLibros() != null) {
            for(Libro l : biblioteca.getLibros()) {
                areaSalida.append("- " + l.getTitulo() + " [ISBN: " + l.getIsbn() + "]\n");
            }
        }
        
        areaSalida.append("\nPRESTAMOS ACTIVOS:\n");
        if(biblioteca.getPrestamos() != null) {
            for(Prestamo p : biblioteca.getPrestamos()) {
                String l = (p.getLibro()!=null) ? p.getLibro().getTitulo() : "?";
                String e = (p.getEstudiante()!=null) ? p.getEstudiante().getNombre() : "?";
                String m = (p.getEstudiante()!=null) ? p.getEstudiante().getCodigo() : "?";
                areaSalida.append("- " + l + " prestado a " + e + " (" + m + ")\n");
            }
        }
    }

    private Libro buscarLibro(String isbn) {
        if(biblioteca.getLibros()!=null) {
            for(Libro l : biblioteca.getLibros()) {
                if(l.getIsbn().equals(isbn)) return l;
            }
        }
        return null;
    }

    private void cargarDatos() {
        try {
            Gson gson = new Gson();
            try (Reader reader = new FileReader(ARCHIVO_DATOS)) {
                biblioteca = gson.fromJson(reader, Biblioteca.class);
            }
        } catch(Exception e) {}
    }

    private void salirYGuardar() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try (FileWriter w = new FileWriter(ARCHIVO_DATOS)) {
                gson.toJson(biblioteca, w);
            }
        } catch(Exception ex) { }
        System.exit(0);
    }

    // --- ESTA ES LA PARTE QUE FALTABA PARA QUE ARRANQUE ---
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SistemaPantalla().setVisible(true);
        });
    }
}