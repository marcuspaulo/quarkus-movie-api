package br.com.mp.quarkusmovie.model;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.*;

@Entity
@Table(name = "user_movie")
public class UserMovie implements Serializable {

    @EmbeddedId
    protected UserMoviePK userMoviePK;

    @Column(name = "rate")
    private int rate;

    private boolean watchlist;

    @Column(name = "watched")
    private boolean isAlreadyWatched;

    @JoinColumn(name = "movie_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne()
    private Movie movie;

    public UserMovie() {
    }

    public UserMoviePK getUserMoviePK() {
        return userMoviePK;
    }

    public void setUserMoviePK(UserMoviePK userMoviePK) {
        this.userMoviePK = userMoviePK;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public boolean isWatchlist() {
        return watchlist;
    }

    public void setWatchlist(boolean watchlist) {
        this.watchlist = watchlist;
    }

    public boolean isAlreadyWatched() {
        return isAlreadyWatched;
    }

    public void setAlreadyWatched(boolean alreadyWatched) {
        isAlreadyWatched = alreadyWatched;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
