package com.sofkau.gestionlibros.useCases;

import com.sofkau.gestionlibros.domain.catalogo.Catalogo;
import com.sofkau.gestionlibros.domain.catalogo.commands.RegistrarLibroCommand;
import com.sofkau.gestionlibros.domain.generic.DomainEvent;
import com.sofkau.gestionlibros.domain.generic.EventStoreRepository;

import java.util.List;
import java.util.function.Function;

public class RegistrarLibroUseCase implements Function<RegistrarLibroCommand, List<DomainEvent>> {

    private final EventStoreRepository eventStoreRepository;

    public RegistrarLibroUseCase(EventStoreRepository eventStoreRepository) {
        this.eventStoreRepository = eventStoreRepository;
    }

    @Override
    public List<DomainEvent> apply(RegistrarLibroCommand registrarLibroCommand) {

        var catalogo = Catalogo.from(registrarLibroCommand.getCatalogoId(),
                eventStoreRepository.getEventsBy("catalogo", registrarLibroCommand.getCatalogoId()));

        return catalogo.getUncommittedChanges();
    }
}
