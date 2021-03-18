package br.com.mp.quarkusmovie.exception;

import io.quarkus.arc.ArcUndeclaredThrowableException;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GeneralExceptions implements ExceptionMapper<Exception> {

    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(Exception e) {
        MessageError messageError = new MessageError();


        if (e instanceof ArcUndeclaredThrowableException) {
            messageError.setMessage("Erro: Registro duplicado!");
            return Response.status(Response.Status.BAD_REQUEST).entity(messageError).build();
        }


        return null;
    }
}
