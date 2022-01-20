package com.sofkau.gestionlibros.domain.catalogo;

public class Libro {

    private final String id;
    private final String titulo;
    private final String autor;
    private final String genero;
    private final String descripcion;
    private final String editorial;
    private final String fechaLanzamiento;
    private final int paginas;
    private final String imagen;
    private final String urlLectura;

    public Libro(String id, String titulo, String autor, String genero, String descripcion, String editorial, String fechaLanzamiento, int paginas, String imagen, String urlLectura) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.descripcion = descripcion;
        this.editorial = editorial;
        this.fechaLanzamiento = fechaLanzamiento;
        this.paginas = paginas;
        this.imagen = imagen;
        this.urlLectura = urlLectura;
    }

    public String id() {
        return id;
    }

    public String titulo() {
        return titulo;
    }

    public String autor() {
        return autor;
    }

    public String genero() {
        return genero;
    }

    public String descripcion() {
        return descripcion;
    }

    public String editorial() {
        return editorial;
    }

    public String fechaLanzamiento() {
        return fechaLanzamiento;
    }

    public int paginas() {
        return paginas;
    }

    public String imagen() {
        return imagen;
    }

    public String urlLectura() {
        return urlLectura;
    }
}
