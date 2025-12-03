package practica6_biblioteca;

import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.time.LocalDate;

// --- LOGICA ACTUALIZADA CON GETTERS PARA EL REPORTE ---

class Autor implements Serializable {
    private String nombre;
    private String nacionalidad;

    public Autor(String nombre, String nacionalidad) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }
    public String getNombre() { return nombre; }
    public String getNacionalidad() { return nacionalidad; }
}

class Estudiante implements Serializable {
    private String codigo; // Ahora lo llamaremos matricula visualmente
    private String nombre;

    public Estudiante(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
    public String getNombre() { return nombre; }
    public String getCodigo() { return codigo; }
}

class Libro implements Serializable {
    private String titulo;
    private String isbn;
    private Set<String> paginas; 

    public Libro(String titulo, String isbn, String[] contenidoPaginas) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.paginas = new LinkedHashSet<>();
        if(contenidoPaginas != null) {
            for (String p : contenidoPaginas) this.paginas.add(p);
        }
    }

    public String getTitulo() { return titulo; }
    public String getIsbn() { return isbn; }
}

class Prestamo implements Serializable {
    private String fechaPrestamo;
    private String fechaDevolucion;
    private Estudiante estudiante; 
    private Libro libro;           

    public Prestamo(Estudiante estudiante, Libro libro) {
        this.estudiante = estudiante;
        this.libro = libro;
        this.fechaPrestamo = LocalDate.now().toString();
        this.fechaDevolucion = LocalDate.now().plusDays(14).toString();
    }
    
    // Estos son los que faltaban para el reporte:
    public Libro getLibro() { return libro; }
    public Estudiante getEstudiante() { return estudiante; }
    public String getFechaDevolucion() { return fechaDevolucion; }
}

class Biblioteca implements Serializable {
    private String nombre;
    private Set<Libro> libros;       
    private Set<Autor> autores;      
    private Set<Prestamo> prestamos; 
    
    public Biblioteca(String nombre) {
        this.nombre = nombre;
        this.libros = new HashSet<>();
        this.autores = new HashSet<>();
        this.prestamos = new HashSet<>();
    }

    public void agregarLibro(Libro libro) { this.libros.add(libro); }
    public void agregarAutor(Autor autor) { this.autores.add(autor); }
    
    public void prestarLibro(Estudiante est, Libro lib) {
        prestamos.add(new Prestamo(est, lib));
    }

    public Set<Libro> getLibros() { return libros; }
    public Set<Autor> getAutores() { return autores; }
    public Set<Prestamo> getPrestamos() { return prestamos; }
}