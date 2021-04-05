package br.com.mp.quarkusmovie.repository;

import br.com.mp.quarkusmovie.model.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    public Optional<User> findByEmail(String email) {
        return find("email = :email",
                Parameters.with("email", email))
                .firstResultOptional();
    }
}
