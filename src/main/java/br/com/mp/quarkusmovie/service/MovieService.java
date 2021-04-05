package br.com.mp.quarkusmovie.service;

import br.com.mp.quarkusmovie.exception.BusinessException;
import br.com.mp.quarkusmovie.exception.GeneralExceptions;
import br.com.mp.quarkusmovie.model.Movie;
import br.com.mp.quarkusmovie.model.User;
import br.com.mp.quarkusmovie.model.UserMovie;
import br.com.mp.quarkusmovie.model.UserMoviePK;
import br.com.mp.quarkusmovie.repository.MovieRepository;
import br.com.mp.quarkusmovie.repository.UserMovieRepository;
import br.com.mp.quarkusmovie.repository.UserRepository;
import br.com.mp.quarkusmovie.resources.model.UserMovieModelAPI;
import br.com.mp.quarkusmovie.restclient.IMDBAPIRestClient;
import br.com.mp.quarkusmovie.restclient.model.MovieIMDB;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.ServerErrorException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MovieService {

    @Inject
    @RestClient
    IMDBAPIRestClient imdbapiRestClient;

    @ConfigProperty(name = "x-rapidapi-key")
    String xRapidapiKey;

    @ConfigProperty(name = "x-rapidapi-host")
    String xRapidapiHost;

    @Inject
    MovieRepository movieRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    UserMovieRepository userMovieRepository;

    @Transactional
    public MovieIMDB search(String query) {
        MovieIMDB movieIMDB = imdbapiRestClient.search(xRapidapiKey, xRapidapiHost, query);

        saveMovieDatabase(movieIMDB);

        return movieIMDB;
    }

    public List<Movie> listBestRated() {
        return movieRepository.listBestRated();
    }

    private void saveMovieDatabase(MovieIMDB movieIMDB) {
        List<Movie> movies = new ArrayList<>();

        movieIMDB.getDescriptionIMDBS().forEach(m -> {
            Movie movieDatabase = movieRepository.findByIMDBId(m.getIdIMDB());

            if (movieDatabase == null) {
                movies.add(new Movie(m));
            }
        });

        movieRepository.persist(movies);
    }

    public List<Movie> list() {
        return movieRepository.listAll();
    }

    @Transactional
    public Movie add(UserMovieModelAPI userMovieModelAPI, String emailUser) {

        if (!userMovieModelAPI.getAlreadyWatched()) {
            throw new BusinessException("Erro: Para dar nota, você precisa ter assistido o filme!");
        }

        Optional<User> user = userRepository.findByEmail(emailUser);

        Movie movie = movieRepository.findByIMDBId(userMovieModelAPI.getMovieIMDBId());

        UserMoviePK userMoviePK = new UserMoviePK();
        userMoviePK.setUserId(user.get().getId());
        userMoviePK.setMovieId(movie.getId());

        UserMovie userMovie = new UserMovie();
        userMovie.setMovie(movie);
        userMovie.setUserMoviePK(userMoviePK);
        userMovie.setAlreadyWatched(userMovieModelAPI.getAlreadyWatched());
        userMovie.setWatchlist(userMovieModelAPI.getWatchlist());
        userMovie.setRate(userMovieModelAPI.getRate());

        userMovieRepository.getEntityManager().merge(userMovie);

        return movie;

    }
}
