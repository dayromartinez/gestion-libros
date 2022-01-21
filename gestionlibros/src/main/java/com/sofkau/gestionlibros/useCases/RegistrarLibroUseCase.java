package com.sofkau.gestionlibros.useCases;

import com.sofkau.gestionlibros.domain.catalogo.Catalogo;
import com.sofkau.gestionlibros.domain.catalogo.commands.RegistrarLibroCommand;
import com.sofkau.gestionlibros.domain.generic.DomainEvent;
import com.sofkau.gestionlibros.domain.generic.EventStoreRepository;

import com.google.gson.*;

import javax.enterprise.context.Dependent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.logging.Logger;

@Dependent
public class RegistrarLibroUseCase implements Function<RegistrarLibroCommand, List<DomainEvent>> {

    private final EventStoreRepository eventStoreRepository;
    private String url = "https://www.googleapis.com/books/v1/volumes?q=%27%27+inauthor:%27Gabriel%20Garcia%20Marquez%27&maxResults=11&key=%20AIzaSyDqNrqlxSCcYdzWw0uiFbQPdQ_QJ4SVVMM";
    private String respuesta = "";

    Logger logger = Logger.getLogger("MyLog");

    String titulo, autor, genero, descripcion, editorial, fechaLanzamiento, imagen, urlLectura;
    int paginas;

    public RegistrarLibroUseCase(EventStoreRepository eventStoreRepository) {
        this.eventStoreRepository = eventStoreRepository;
    }

    @Override
    public List<DomainEvent> apply(RegistrarLibroCommand registrarLibroCommand) {
        //Propiedad para traer el autor que el usuario indico en la UI
        //String autorBusqueda = registrarLibroCommand.getAutor();
        var catalogo = Catalogo.from(registrarLibroCommand.getCatalogoId(),
                eventStoreRepository.getEventsBy("catalogo", registrarLibroCommand.getCatalogoId()));

        //Extraccion datos de la consulta
        try {
            respuesta = peticionHttpGet(url);

            JsonObject jsonObject = new JsonParser().parse(respuesta).getAsJsonObject();
            JsonArray jsonlibros = jsonObject.get("items").getAsJsonArray();
            boolean isDescripcion, isGenero, isPaginas, isImagen, isEditorial;

            for (int i = 0; i < jsonlibros.size(); i++) {

                JsonObject entrada = jsonlibros.get(i).getAsJsonObject().get("volumeInfo").getAsJsonObject();

                isDescripcion = entrada.has("description");
                if(!isDescripcion){
                    descripcion = "Descripción no disponible";
                }else{
                    descripcion = entrada.get("description").getAsString();
                }

                isGenero = entrada.has("categories");
                if(!isGenero){
                    genero = "Ficción";
                }else{
                    genero = entrada.get("categories").getAsJsonArray().get(0).getAsString();
                }

                isEditorial = entrada.has("publisher");
                if(!isEditorial){
                    editorial = "Editorial no disponible";
                }else{
                    editorial = entrada.get("publisher").getAsString();
                }

                isPaginas = entrada.has("pageCount");
                if(!isPaginas){
                    paginas = 0;
                }else{
                    paginas = entrada.get("pageCount").getAsInt();
                }

                isImagen = entrada.has("imageLinks");
                if(!isImagen){
                    imagen = "https://edit.org/images/cat/portadas-libros-big-2019101610.jpg";
                }else{
                    imagen = entrada.get("imageLinks").getAsJsonObject().get("thumbnail").getAsString();
                }

                titulo = entrada.get("title").getAsString();
                autor = entrada.get("authors").getAsJsonArray().get(0).getAsString();
                fechaLanzamiento = entrada.get("publishedDate").getAsString();
                urlLectura = jsonlibros.get(i).getAsJsonObject().get("accessInfo").getAsJsonObject().get("webReaderLink").getAsString();
                logger.info("Log to test");
                UUID uuid = UUID.randomUUID();
                catalogo.registrarLibro(String.valueOf(uuid), titulo, autor, genero, descripcion,
                editorial, fechaLanzamiento, paginas, imagen, urlLectura);
            }

        } catch (Exception e) {
            // Manejar excepción
            e.printStackTrace();
        }
        return catalogo.getUncommittedChanges();
    }

    public static String peticionHttpGet(String urlParaVisitar) throws Exception {

        // Esto es lo que vamos a devolver
        StringBuilder resultado = new StringBuilder();
        // Crear un objeto de tipo URL
        URL url = new URL(urlParaVisitar);

        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");

        BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
        String linea;

        // Mientras el BufferedReader se pueda leer, agregar contenido a resultado
        while ((linea = rd.readLine()) != null) {
            resultado.append(linea);
        }

        // Cerrar el BufferedReader
        rd.close();
        return resultado.toString();
    }
}
