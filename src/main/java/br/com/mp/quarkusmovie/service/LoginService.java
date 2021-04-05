package br.com.mp.quarkusmovie.service;

import br.com.mp.quarkusmovie.dto.LoginDTO;
import br.com.mp.quarkusmovie.model.User;
import br.com.mp.quarkusmovie.repository.UserRepository;
import br.com.mp.quarkusmovie.resources.model.LoginResponseDTO;
import br.com.mp.quarkusmovie.util.AuthTokenService;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import java.util.Optional;

@ApplicationScoped
public class LoginService {

    @Inject
    UserRepository userRepository;

    @Inject
    AuthTokenService authTokenService;

    public LoginResponseDTO login(LoginDTO loginDTO) {

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();

        Optional<User> user = userRepository.findByEmail(loginDTO.getEmail());

        if (user.isEmpty()) {
            throw new NotAuthorizedException("Erro ao efetuar a autenticação");
        }

        if (!BCrypt.checkpw(loginDTO.getPassword(), user.get().getPassword())) {
            throw new NotAuthorizedException("E-mail ou senha inválido.");
        }

        String token = authTokenService.generateTokenJWT(user.get());

        loginResponseDTO.setEmail(user.get().getEmail());
        loginResponseDTO.setToken(token);

        return loginResponseDTO;
    }
}
