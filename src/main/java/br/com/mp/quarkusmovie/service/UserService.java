package br.com.mp.quarkusmovie.service;

import br.com.mp.quarkusmovie.model.User;
import br.com.mp.quarkusmovie.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    @Transactional
    public User create(User user) {
        userRepository.persist(user);
        return user;
    }
}
