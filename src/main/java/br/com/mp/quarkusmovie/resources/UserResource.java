package br.com.mp.quarkusmovie.resources;

import br.com.mp.quarkusmovie.model.User;
import br.com.mp.quarkusmovie.resources.model.UserDTO;
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
    public Response add(UserDTO userDTO) {

        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

         userService.create(user);
         return Response.status(Response.Status.CREATED).entity(userDTO).build();
    }
}