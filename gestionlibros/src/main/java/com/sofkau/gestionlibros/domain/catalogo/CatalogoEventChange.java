package com.sofkau.gestionlibros.domain.catalogo;

import com.sofkau.gestionlibros.domain.catalogo.events.CatalogoCreado;
import com.sofkau.gestionlibros.domain.catalogo.events.LibroRegistrado;
import com.sofkau.gestionlibros.domain.generic.EventChange;

import java.util.ArrayList;

public class CatalogoEventChange implements EventChange {

    public CatalogoEventChange(Catalogo catalogo){

        listener((CatalogoCreado event) -> {
            catalogo.nombre = event.getNombre();
            catalogo.libros = new ArrayList<>();
        });

        listener((LibroRegistrado event) -> {
            var libro = new Libro(event.getLibroId(), event.getTitulo(), event.getAutor(),
                    event.getGenero(), event.getDescripcion(), event.getEditorial(),
                    event.getFechaLanzamiento(), event.getPaginas(), event.getImagen(), event.getUrlLectura());
            catalogo.libros.add(libro);
        });
    }
}
