package com.sofkau.gestionlibros.domain.catalogo;

import com.sofkau.gestionlibros.domain.catalogo.events.CatalogoCreado;
import com.sofkau.gestionlibros.domain.catalogo.events.LibroRegistrado;
import com.sofkau.gestionlibros.domain.generic.AggregateRoot;
import com.sofkau.gestionlibros.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.List;

public class Catalogo extends AggregateRoot {

    protected List<Libro> libros;
    protected String nombre;

    public Catalogo(String catalogoId, String nombre) {
        super(catalogoId);
        subscribe(new CatalogoEventChange(this));
        this.libros = new ArrayList<>();
        appendChange(new CatalogoCreado(nombre)).apply();
    }

    private Catalogo(String catalogoId) {
        super(catalogoId);
        subscribe(new CatalogoEventChange(this));
    }

    public static Catalogo from(String catalogoId, List<DomainEvent> events){
        var catalogo = new Catalogo(catalogoId);
        events.forEach(catalogo::applyEvent);
        return catalogo;
    }

    public void registrarLibro(String libroId, String titulo, String autor, String genero, String descripcion, String editorial, String fechaLanzamiento, int paginas, String imagen, String urlLectura){
        appendChange(new LibroRegistrado(libroId, titulo, autor, genero, descripcion, editorial, fechaLanzamiento, paginas, imagen, urlLectura)).apply();
    }

    public List<Libro> libros() {
        return libros;
    }

    public String nombre() {
        return nombre;
    }
}
