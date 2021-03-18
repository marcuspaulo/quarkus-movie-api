package br.com.mp.quarkusmovie.repository;

import br.com.mp.quarkusmovie.model.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
}
