package br.com.mp.quarkusmovie.exception;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GeneralException implements ExceptionMapper<Exception> {

    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(Exception e) {

        MessageError messageError = new MessageError();

        if (e instanceof BusinessException) {
            messageError.setMessage(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(messageError).build();
        }

        if (e instanceof NotAuthorizedException) {
            messageError.setMessage("Erro ao validar as credenciais.");
            return Response.status(Response.Status.BAD_REQUEST).entity(messageError).build();
        }

        messageError.setMessage("Erro: Por favor entre em contato com o suporte.");
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(messageError).build();
    }
}
