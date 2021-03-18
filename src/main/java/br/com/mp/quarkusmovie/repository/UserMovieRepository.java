package br.com.mp.quarkusmovie.repository;

import br.com.mp.quarkusmovie.model.UserMovie;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserMovieRepository implements PanacheRepository<UserMovie> {

}
