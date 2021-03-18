package br.com.mp.quarkusmovie.resources;

import br.com.mp.quarkusmovie.model.Movie;
import br.com.mp.quarkusmovie.resources.model.UserMovieModelAPI;
import br.com.mp.quarkusmovie.restclient.IMDBAPIRestClient;
import br.com.mp.quarkusmovie.restclient.model.MovieIMDB;
import br.com.mp.quarkusmovie.service.MovieService;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/movies")
public class MovieResource {

    @Inject
    MovieService movieService;

    @GET
    @Path("/search/{query}")
    @Produces(MediaType.APPLICATION_JSON)
    public MovieIMDB hello(@PathParam("query") String query) {
       return movieService.search(query);
    }

    @GET
    @Path("/list")
    public List<Movie> list() {
        return movieService.list();
    }

    @POST
    @Path("/add")
    public Movie add(UserMovieModelAPI userMovieModelAPI) {
        return movieService.add(userMovieModelAPI);
    }
}