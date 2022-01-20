package com.sofkau.gestionlibros.domain.catalogo.commands;

import com.sofkau.gestionlibros.domain.generic.Command;

public class RegistrarLibroCommand extends Command {

    private String catalogoId;
    private String libroId;
    private String titulo;
    private String autor;
    private String genero;
    private String descripcion;
    private String editorial;
    private String fechaLanzamiento;
    private String imagen;
    private String urlLectura;

    public RegistrarLibroCommand() {
    }

    public String getCatalogoId() {
        return catalogoId;
    }

    public void setCatalogoId(String catalogoId) {
        this.catalogoId = catalogoId;
    }

    public String getLibroId() {
        return libroId;
    }

    public void setLibroId(String libroId) {
        this.libroId = libroId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(String fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getUrlLectura() {
        return urlLectura;
    }

    public void setUrlLectura(String urlLectura) {
        this.urlLectura = urlLectura;
    }
}
