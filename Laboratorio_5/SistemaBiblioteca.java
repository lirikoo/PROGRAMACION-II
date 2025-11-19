import java.util.Set;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.time.LocalDate;

class Autor {
    private String nombre;
    private String nacionalidad;

    public Autor(String nombre, String nacionalidad) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void mostrarInfo() {
        System.out.println("  Autor: " + nombre + " (" + nacionalidad + ")");
    }
}

class Estudiante {
    private String codigo;
    private String nombre;

    public Estudiante(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void mostrarInfo() {
        System.out.println("  Estudiante: " + nombre + " (Codigo: " + codigo + ")");
    }
}

class Libro {
    private String titulo;
    private String isbn;
    private Set<Pagina> paginas; 

    private class Pagina {
        private int numero;
        private String contenido;

        public Pagina(int numero, String contenido) {
            this.numero = numero;
            this.contenido = contenido;
        }

        public void mostrar() {
            System.out.println("    --- Pag. " + numero + " ---");
            System.out.println("    " + contenido);
        }
    }

    public Libro(String titulo, String isbn, String[] contenidoPaginas) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.paginas = new LinkedHashSet<>();
        
        for (int i = 0; i < contenidoPaginas.length; i++) {
            this.paginas.add(new Pagina(i + 1, contenidoPaginas[i]));
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void leer() {
        System.out.println("\nLeyendo el libro: '" + titulo + "' (ISBN: " + isbn + ")");
        for (Pagina p : paginas) {
            p.mostrar();
        }
    }
}

class Prestamo {
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private Estudiante estudiante; 
    private Libro libro;           

    public Prestamo(Estudiante estudiante, Libro libro) {
        this.estudiante = estudiante;
        this.libro = libro;
        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucion = LocalDate.now().plusDays(14);
    }

    public void mostrarInfo() {
        System.out.println("  - Prestamo Activo:");
        System.out.println("    Libro: " + libro.getTitulo());
        System.out.println("    Estudiante: " + estudiante.getNombre());
        System.out.println("    Fecha Prestamo: " + fechaPrestamo);
        System.out.println("    Fecha Devolucion: " + fechaDevolucion);
    }
}

class Biblioteca {
    private String nombre;
    private Set<Libro> libros;       
    private Set<Autor> autores;      
    private Set<Prestamo> prestamos; 
    private Horario horario;          

    private class Horario {
        private String diasApertura;
        private String horaApertura;
        private String horaCierre;

        public Horario(String dias, String hApertura, String hCierre) {
            this.diasApertura = dias;
            this.horaApertura = hApertura;
            this.horaCierre = hCierre;
        }

        public void mostrarHorario() {
            System.out.println("  Horario de Atencion:");
            System.out.println("    Dias: " + diasApertura);
            System.out.println("    Horario: " + horaApertura + " - " + horaCierre);
        }
    }

    public Biblioteca(String nombre) {
        this.nombre = nombre;
        this.libros = new HashSet<>();
        this.autores = new HashSet<>();
        this.prestamos = new HashSet<>();
        
        this.horario = new Horario("Lunes a Viernes", "08:00", "18:00");
    }

    public void agregarLibro(Libro libro) {
        this.libros.add(libro);
        System.out.println("AGREGACION: Libro '" + libro.getTitulo() + "' agregado a la biblioteca.");
    }

    public void agregarAutor(Autor autor) {
        this.autores.add(autor);
        System.out.println("AGREGACION: Autor '" + autor.getNombre() + "' registrado.");
    }

    public void prestarLibro(Estudiante estudiante, Libro libro) {
        if (this.libros.contains(libro)) {
            Prestamo nuevoPrestamo = new Prestamo(estudiante, libro);
            this.prestamos.add(nuevoPrestamo);
            System.out.println(" Prestamo creado exitosamente.");
            nuevoPrestamo.mostrarInfo();
        } else {
            System.out.println("\nError: El libro '" + libro.getTitulo() + "' no esta en esta biblioteca.");
        }
    }

    public void cerrarBiblioteca() {
        System.out.println("\n--- CERRANDO BIBLIOTECA " + nombre + " ---");
        System.out.println("COMPOSICION: Todos los prestamos activos dejan de existir.");
        this.prestamos.clear();
        this.horario = null;
    }

    public void mostrarEstado() {
        System.out.println(" ESTADO DE LA BIBLIOTECA: " + nombre + " ");
        
        if (horario != null) {
            horario.mostrarHorario();
        } else {
            System.out.println("  La biblioteca esta cerrada.");
        }

        System.out.println("\n  Autores Registrados (" + autores.size() + "):");
        if (autores.isEmpty()) System.out.println("    Ninguno.");
        for (Autor a : autores) {
            a.mostrarInfo();
        }

        System.out.println("\n  Libros Disponibles (" + libros.size() + "):");
        if (libros.isEmpty()) System.out.println("    Ninguno.");
        for (Libro l : libros) {
            System.out.println("    - " + l.getTitulo());
        }

        System.out.println("\n  Prestamos Activos (" + prestamos.size() + "):");
        if (prestamos.isEmpty()) System.out.println("    Ninguno.");
        for (Prestamo p : prestamos) {
            p.mostrarInfo();
        }
        System.out.println("");
    }
}


public class SistemaBiblioteca {

    public static void main(String[] args) {

        System.out.println(" Creando objetos independientes (para Agregacion) ");
        Autor autor1 = new Autor("Gabriel Garcia Marquez", "Colombiano");
        Autor autor2 = new Autor("Isaac Asimov", "Ruso-Estadounidense");

        Libro libro1 = new Libro("Cien Anios de Soledad", "978-0307350438", 
            new String[]{
                "Muchos anios despues, frente al peloton...",
                "El mundo era tan reciente, que muchas cosas..."
            });
        
        Libro libro2 = new Libro("Fundacion", "978-0553293357", 
            new String[]{
                "La galaxia estaba en decadencia...",
                "Hari Seldon desarrollo la psicohistoria..."
            });
        
        Estudiante estudiante1 = new Estudiante("2020-001", "Alam Brito Fernandez");

        
        System.out.println(" Creando la Biblioteca (Composicion Horario) ");
        Biblioteca biblioCentral = new Biblioteca("Biblioteca Central UMSA");

        biblioCentral.agregarLibro(libro1);
        biblioCentral.agregarLibro(libro2);
        biblioCentral.agregarAutor(autor1);
        biblioCentral.agregarAutor(autor2);
        
        biblioCentral.mostrarEstado();

        libro1.leer();

        biblioCentral.prestarLibro(estudiante1, libro2);
        
        biblioCentral.mostrarEstado();

        biblioCentral.cerrarBiblioteca();
        biblioCentral.mostrarEstado(); 

        System.out.println("Comprobando la AGREGACION ");
        System.out.println("Aunque la biblioteca cerro, los objetos agregados AUN EXISTEN:");
        autor1.mostrarInfo();
        libro1.leer();
    }
}