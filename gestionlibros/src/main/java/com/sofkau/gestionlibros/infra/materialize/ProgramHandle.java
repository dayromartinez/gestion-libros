package com.sofkau.gestionlibros.infra.materialize;

import com.sofkau.gestionlibros.domain.catalogo.events.CatalogoCreado;
import com.sofkau.gestionlibros.domain.catalogo.events.LibroRegistrado;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;
import io.quarkus.vertx.ConsumeEvent;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;


@ApplicationScoped
public class ProgramHandle {
    private final MongoClient mongoClient;

    public ProgramHandle(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }


    @ConsumeEvent(value = "sofkau.catalogocreado", blocking = true)
    void consumeProgramCreated(CatalogoCreado event) {
        Map<String, Object> document = new HashMap<>();
        document.put("_id", event.getAggregateId());
        document.put("nombre", event.getNombre());

        mongoClient.getDatabase("queries")
                .getCollection("catalogo")
                .insertOne(new Document(document));
    }

    @ConsumeEvent(value = "sofkau.libroregistrado", blocking = true)
    void consumeCourseAssigned(LibroRegistrado event) {

        BasicDBObject document = new BasicDBObject();
        document.put("libro."+event.getTitulo()+".titulo", event.getTitulo());
        document.put("libro."+event.getTitulo()+".autor", event.getAutor());
        document.put("libro."+event.getTitulo()+".genero", event.getGenero());
        document.put("libro."+event.getTitulo()+".descripcion", event.getDescripcion());
        document.put("libro."+event.getTitulo()+".editorial", event.getEditorial());
        document.put("libro."+event.getTitulo()+".fechalanzamiento", event.getFechaLanzamiento());
        document.put("libro."+event.getTitulo()+".paginas", event.getPaginas());
        document.put("libro."+event.getTitulo()+".imagen", event.getImagen());
        document.put("libro."+event.getTitulo()+".urllectura", event.getUrlLectura());

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", document);

        mongoClient.getDatabase("queries")
                .getCollection("catalogo")
                .updateOne( Filters.eq("_id", event.getAggregateId()), updateObject);
    }
}