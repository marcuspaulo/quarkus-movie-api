package br.com.mp.quarkusmovie.exception;

import io.quarkus.arc.ArcUndeclaredThrowableException;

import javax.validation.ConstraintViolationException;
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

        if (e instanceof ConstraintViolationException) {
            messageError.setMessage("Erro: Registro duplicado!");
            System.out.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(messageError).build();
        }

        if (e instanceof BusinessException) {
            messageError.setMessage(e.getMessage());
            System.out.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(messageError).build();
        }

        System.out.println(e.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro: Por favor, entre em contato com o suporte").build();
    }
}
