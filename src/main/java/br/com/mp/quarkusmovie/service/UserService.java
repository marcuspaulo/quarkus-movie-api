package br.com.mp.quarkusmovie.service;

import br.com.mp.quarkusmovie.model.User;
import br.com.mp.quarkusmovie.repository.UserRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    public Optional<User> findById(User user) {
        return userRepository.findByIdOptional(user.getId());
    }

    @Transactional
    public User create(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userRepository.persist(user);
        return user;
    }
}
