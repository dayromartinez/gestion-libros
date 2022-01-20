package com.sofkau.gestionlibros.infra.generic;

import com.sofkau.gestionlibros.domain.generic.DomainEvent;
import com.sofkau.gestionlibros.domain.generic.EventSerializer;
import com.sofkau.gestionlibros.domain.generic.EventStoreRepository;
import com.sofkau.gestionlibros.domain.generic.StoredEvent;
import com.sofkau.gestionlibros.infra.message.BusService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public abstract class UseCaseHandle {

    @Inject
    private EventStoreRepository repository;

    @Inject
    private BusService busService;

    public void saveCatalogo(String catalogoId, List<DomainEvent> events) {
        events.stream().map(event -> {
            String eventBody = EventSerializer.instance().serialize(event);
            return new StoredEvent(event.getClass().getTypeName(), new Date(), eventBody);
        }).forEach(storedEvent -> repository.saveEvent("catalogo", catalogoId, storedEvent));

        events.forEach(busService::send);
    }
}
