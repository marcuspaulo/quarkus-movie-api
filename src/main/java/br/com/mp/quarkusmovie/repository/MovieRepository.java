package br.com.mp.quarkusmovie.repository;

import br.com.mp.quarkusmovie.model.Movie;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class MovieRepository implements PanacheRepository<Movie> {

    public List<Movie> list() {
        return listAll();
    }

    public Movie findByIMDBId(String imdbId) {
        return find("imdbId", imdbId).firstResult();
    }
}
