package com.sofkau.gestionlibros.infra.handle;

import com.sofkau.gestionlibros.domain.catalogo.commands.RegistrarLibroCommand;
import com.sofkau.gestionlibros.infra.generic.UseCaseHandle;
import com.sofkau.gestionlibros.useCases.RegistrarLibroUseCase;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RegistrarLibroUseCaseHandle extends UseCaseHandle {

    private final RegistrarLibroUseCase registrarLibroUseCase;

    public RegistrarLibroUseCaseHandle(RegistrarLibroUseCase registrarLibroUseCase) {
        this.registrarLibroUseCase = registrarLibroUseCase;
    }

    @ConsumeEvent(value = "sofkau.registrarlibro")
    void consumeBlocking(RegistrarLibroCommand registrarLibroCommand) {
        var events = registrarLibroUseCase.apply(registrarLibroCommand);
        saveCatalogo(registrarLibroCommand.getCatalogoId(), events);
    }
}
