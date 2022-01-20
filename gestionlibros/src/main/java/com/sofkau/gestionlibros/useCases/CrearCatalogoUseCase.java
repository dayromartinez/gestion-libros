package com.sofkau.gestionlibros.useCases;

import com.sofkau.gestionlibros.domain.catalogo.Catalogo;
import com.sofkau.gestionlibros.domain.catalogo.commands.CrearCatalogoCommand;
import com.sofkau.gestionlibros.domain.generic.DomainEvent;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.function.Function;

@Dependent
public class CrearCatalogoUseCase implements Function<CrearCatalogoCommand, List<DomainEvent>> {

    @Override
    public List<DomainEvent> apply(CrearCatalogoCommand crearCatalogoCommand) {
        var catalogo = new Catalogo(crearCatalogoCommand.getCatalogoId(), crearCatalogoCommand.getNombre());
        return catalogo.getUncommittedChanges();
    }
}
