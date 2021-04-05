package br.com.mp.quarkusmovie.repository;

import br.com.mp.quarkusmovie.model.Movie;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class MovieRepository implements PanacheRepository<Movie> {

    public List<Movie> list() {
        return listAll();
    }

    public Movie findbyIMDBID(String idIMDB) {
        return find("imdbId", idIMDB).firstResult();
    }

    public List<Movie> listBestRated() {
        Long value = 4000L;
        return find("rank < :value", Parameters.with("value", value)).list();
    }
}
