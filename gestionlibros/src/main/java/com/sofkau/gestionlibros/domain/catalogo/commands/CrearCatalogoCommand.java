package com.sofkau.gestionlibros.domain.catalogo.commands;

import com.sofkau.gestionlibros.domain.generic.Command;

public class CrearCatalogoCommand extends Command {

    private String catalogoId;
    private String nombre;

    public CrearCatalogoCommand() {
    }

    public String getCatalogoId() {
        return catalogoId;
    }

    public void setCatalogoId(String catalogoId) {
        this.catalogoId = catalogoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
