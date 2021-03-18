package br.com.mp.quarkusmovie.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

@Embeddable
public class UserMoviePK implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "movie_id")
    private Long movieId;

    public UserMoviePK() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserMoviePK that = (UserMoviePK) o;
        return userId == that.userId && movieId == that.movieId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, movieId);
    }
}
