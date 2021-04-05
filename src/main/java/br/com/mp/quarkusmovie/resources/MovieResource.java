package br.com.mp.quarkusmovie.resources;

import br.com.mp.quarkusmovie.model.Movie;
import br.com.mp.quarkusmovie.model.dto.UserMovieModelAPI;
import br.com.mp.quarkusmovie.restclient.model.MovieIMDB;
import br.com.mp.quarkusmovie.service.MovieService;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/movies")
public class MovieResource {

    @Inject
    MovieService movieService;

    @Inject
    JsonWebToken jwt;

    @Operation(summary = "Método para buscar filmes")
    @APIResponse(responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON, //
                    schema = @Schema(implementation = MovieIMDB.class, type = SchemaType.ARRAY)))
    @Tag(name="movie")
    @GET
    @Path("/search/{query}")
    @Produces(MediaType.APPLICATION_JSON)
    public MovieIMDB search(@PathParam("query") String query) {

       return movieService.search(query);
    }

    @GET
    public List<Movie> list() {
        return movieService.list();
    }

    @GET
    @Path("/listBestRated")
    public List<Movie> listBestRated() {
        return movieService.listBestRated();
    }

    @POST
    @RolesAllowed("User")
    @Path("/evaluate")
    public Response evaluate(UserMovieModelAPI userMovieModelAPI) {
        String emailUser = jwt.getName();
        movieService.evaluate(userMovieModelAPI, emailUser);
        return Response.status(Response.Status.CREATED).build();
    }
}