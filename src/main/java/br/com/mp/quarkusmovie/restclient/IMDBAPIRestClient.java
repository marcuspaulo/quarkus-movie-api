package br.com.mp.quarkusmovie.restclient;

import br.com.mp.quarkusmovie.restclient.model.MovieIMDB;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RegisterRestClient(configKey = "restcliente-imdb-api")
@Produces(MediaType.APPLICATION_JSON)
public interface IMDBAPIRestClient {

    @GET
    @Path("/title/auto-complete")
    MovieIMDB search(@HeaderParam("x-rapidapi-key") String xRapidapiKey,
                     @HeaderParam("x-rapidapi-host") String xRapidapiHost,
                     @QueryParam("q") String query);
}
