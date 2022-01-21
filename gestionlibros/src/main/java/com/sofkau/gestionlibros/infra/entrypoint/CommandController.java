package com.sofkau.gestionlibros.infra.entrypoint;
import com.sofkau.gestionlibros.domain.catalogo.commands.CrearCatalogoCommand;
import com.sofkau.gestionlibros.domain.catalogo.commands.RegistrarLibroCommand;
import io.vertx.mutiny.core.eventbus.EventBus;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api")
public class CommandController {

    private final EventBus bus;

    public CommandController(EventBus bus){
        this.bus = bus;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/registrarLibros")
    public Response executor(RegistrarLibroCommand command) {
        bus.publish(command.getType(), command);
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/crearCatalogo")
    public Response executor(CrearCatalogoCommand command) {
        bus.publish(command.getType(), command);
        return Response.ok().build();
    }
}