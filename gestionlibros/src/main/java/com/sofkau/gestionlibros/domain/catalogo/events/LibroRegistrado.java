package com.sofkau.gestionlibros.domain.catalogo.events;

import com.sofkau.gestionlibros.domain.generic.DomainEvent;

public class LibroRegistrado extends DomainEvent {

    private final String libroId;
    private final String titulo;
    private final String autor;
    private final String genero;
    private final String descripcion;
    private final String editorial;
    private final String fechaLanzamiento;
    private final int paginas;
    private final String imagen;
    private final String urlLectura;

    public LibroRegistrado(String libroId, String titulo, String autor, String genero, String descripcion, String editorial, String fechaLanzamiento, int paginas, String imagen, String urlLectura) {
        super("sofkau.libroregistrado");
        this.libroId = libroId;
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

    public String getLibroId() {
        return libroId;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEditorial() {
        return editorial;
    }

    public String getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public int getPaginas() {
        return paginas;
    }

    public String getImagen() {
        return imagen;
    }

    public String getUrlLectura() {
        return urlLectura;
    }
}
