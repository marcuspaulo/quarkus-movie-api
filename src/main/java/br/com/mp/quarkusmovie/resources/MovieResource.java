package br.com.mp.quarkusmovie.resources;

import br.com.mp.quarkusmovie.model.Movie;
import br.com.mp.quarkusmovie.resources.model.UserMovieModelAPI;
import br.com.mp.quarkusmovie.restclient.IMDBAPIRestClient;
import br.com.mp.quarkusmovie.restclient.model.MovieIMDB;
import br.com.mp.quarkusmovie.service.MovieService;
import io.quarkus.security.Authenticated;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Components;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeIn;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.*;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/movies")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MovieResource {

    @Inject
    MovieService movieService;

    @Inject
    JsonWebToken jwt;

    @GET
    @Path("/search/{query}")

    public MovieIMDB search(@PathParam("query") String query) {
       return movieService.search(query);
    }

    @GET
    @Path("/list")
    public List<Movie> list() {
        return movieService.list();
    }

    @GET
    @Path("/listBestRated")
    public List<Movie> listBestRated() {
        return movieService.listBestRated();
    }

    @SecurityRequirement(name = "movie-jtw")
    @RolesAllowed("User")
    @Path("/add")
    @POST
    public Response add(UserMovieModelAPI userMovieModelAPI) {
        String emailUser = jwt.getClaim(Claims.email.name());

         Movie movie = movieService.add(userMovieModelAPI, emailUser);
         return Response.status(Response.Status.CREATED).build();
    }
}