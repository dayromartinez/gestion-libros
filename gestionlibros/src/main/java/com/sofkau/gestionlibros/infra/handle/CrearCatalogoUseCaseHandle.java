package com.sofkau.gestionlibros.infra.handle;

import com.sofkau.gestionlibros.domain.catalogo.commands.CrearCatalogoCommand;
import com.sofkau.gestionlibros.infra.generic.UseCaseHandle;
import com.sofkau.gestionlibros.useCases.CrearCatalogoUseCase;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CrearCatalogoUseCaseHandle extends UseCaseHandle {

    private final CrearCatalogoUseCase crearCatalogoUseCase;

    public CrearCatalogoUseCaseHandle(CrearCatalogoUseCase crearCatalogoUseCase) {
        this.crearCatalogoUseCase = crearCatalogoUseCase;
    }

    @ConsumeEvent(value = "sofka.crearcatalogo")
    void consumeBlocking(CrearCatalogoCommand crearCatalogoCommand) {
        var events = crearCatalogoUseCase.apply(crearCatalogoCommand);
        saveCatalogo(crearCatalogoCommand.getCatalogoId(), events);
    }
}
