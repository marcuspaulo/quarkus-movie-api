package br.com.mp.quarkusmovie.service;

import br.com.mp.quarkusmovie.exception.BusinessException;
import br.com.mp.quarkusmovie.model.Movie;
import br.com.mp.quarkusmovie.model.User;
import br.com.mp.quarkusmovie.model.UserMovie;
import br.com.mp.quarkusmovie.model.UserMoviePK;
import br.com.mp.quarkusmovie.model.dto.UserMovieModelAPI;
import br.com.mp.quarkusmovie.repository.MovieRepository;
import br.com.mp.quarkusmovie.repository.UserMovieRepository;
import br.com.mp.quarkusmovie.repository.UserRepository;
import br.com.mp.quarkusmovie.restclient.IMDBAPIRestClient;
import br.com.mp.quarkusmovie.restclient.model.MovieIMDB;
import io.quarkus.panache.common.Parameters;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotAuthorizedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MovieService {

    @ConfigProperty(name = "x-rapidapi-key")
    String xRapidapiKey;

    @ConfigProperty(name = "x-rapidapi-host")
    String xRapidapiHost;

    @Inject
    MovieRepository movieRepository;

    @Inject
    @RestClient
    IMDBAPIRestClient imdbapiRestClient;

    @Inject
    UserMovieRepository userMovieRepository;

    @Inject
    UserRepository userRepository;

    @Transactional
    public MovieIMDB search(String query) {

        MovieIMDB movieIMDB = imdbapiRestClient.search(xRapidapiKey, xRapidapiHost, query);

        saveMovie(movieIMDB);

        return movieIMDB;
    }

    private void saveMovie(MovieIMDB movieIMDB) {
        List<Movie> movies = new ArrayList<>();

        movieIMDB.getDescriptionIMDBS().forEach(m -> {
            Movie movieDatabase = movieRepository.findbyIMDBID(m.getIdIMDB());

            if (movieDatabase == null && m.getQualifier() != null) {
                movies.add(new Movie(m));
            }

            movieRepository.persist(movies);
        });
    }

    public List<Movie> list() {
        return movieRepository.listAll();
    }

    public List<Movie> listBestRated() {
        return movieRepository.listBestRated();
    }

    @Transactional
    public Movie evaluate(UserMovieModelAPI userMovieModelAPI, String emailUser) {

        Optional<User> user = userRepository.findByEmail(emailUser);

        if (user.isEmpty()) {
            throw new NotAuthorizedException("Erro ao obter credenciais do usuário");
        }

        if (!userMovieModelAPI.getAlreadyWatched()) {
            throw new BusinessException("Erro: Para dar uma avaliação, é necessário ter assistido o filme/série!");
        }

        Movie movie = movieRepository.findbyIMDBID(userMovieModelAPI.getMovieIMDBId());

        UserMoviePK userMoviePK = new UserMoviePK();
        userMoviePK.setUserId(user.get().getId());
        userMoviePK.setMovieId(movie.getId());

        UserMovie userMovie = new UserMovie();
        userMovie.setMovie(movie);
        userMovie.setUserMoviePK(userMoviePK);
        userMovie.setAreadyWatched(userMovieModelAPI.getAlreadyWatched());
        userMovie.setWatchlist(userMovieModelAPI.getWatchlist());
        userMovie.setRate(userMovieModelAPI.getRate());

        userMovieRepository.getEntityManager().merge(userMovie);

        return movie;
    }
}
