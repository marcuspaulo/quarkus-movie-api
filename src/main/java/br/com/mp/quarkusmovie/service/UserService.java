package br.com.mp.quarkusmovie.service;

import br.com.mp.quarkusmovie.exception.BusinessException;
import br.com.mp.quarkusmovie.model.User;
import br.com.mp.quarkusmovie.model.dto.UserDTO;
import br.com.mp.quarkusmovie.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import java.util.Optional;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    @Transactional
    public void create(UserDTO userDTO) {

        Optional<User> userOptional = userRepository.findByEmail(userDTO.getEmail());

        if (userOptional.isPresent()) {
            throw new BusinessException("Usuário já cadastrado");
        }

        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt()));

        userRepository.persist(user);
    }
}
