package com.sofkau.gestionlibros.domain.catalogo.events;

import com.sofkau.gestionlibros.domain.generic.DomainEvent;

public class CatalogoCreado extends DomainEvent {

    private String nombre;

    public CatalogoCreado(String nombre) {
        super("sofkau.catalogocreado");
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
