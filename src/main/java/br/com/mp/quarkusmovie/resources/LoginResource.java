package br.com.mp.quarkusmovie.resources;

import br.com.mp.quarkusmovie.model.dto.LoginDTO;
import br.com.mp.quarkusmovie.model.dto.LoginResponseDTO;
import br.com.mp.quarkusmovie.service.LoginService;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/login")
public class LoginResource {

    @Inject
    LoginService loginService;

    @PermitAll
    @POST
    public LoginResponseDTO login(LoginDTO loginDTO) {

        return loginService.login(loginDTO);
    }
}
