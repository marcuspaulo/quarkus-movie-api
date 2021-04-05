package br.com.mp.quarkusmovie.resources;

import br.com.mp.quarkusmovie.dto.LoginDTO;
import br.com.mp.quarkusmovie.resources.model.LoginResponseDTO;
import br.com.mp.quarkusmovie.service.LoginService;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {

    @Inject
    LoginService loginService;

    @PermitAll
    @POST
    public LoginResponseDTO login(LoginDTO loginDTO) {
        return loginService.login(loginDTO);
    }
}
