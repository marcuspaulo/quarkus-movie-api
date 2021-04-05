package br.com.mp.quarkusmovie.resources;

import br.com.mp.quarkusmovie.model.dto.UserDTO;
import br.com.mp.quarkusmovie.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserResource {

    @Inject
    UserService userService;

    @POST
    public Response create(UserDTO userDTO) {
        userService.create(userDTO);

        return Response.status(Response.Status.CREATED).build();
    }
}
