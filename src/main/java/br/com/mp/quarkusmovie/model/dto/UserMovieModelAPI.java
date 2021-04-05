package br.com.mp.quarkusmovie.model.dto;

public class UserMovieModelAPI {

    private String movieIMDBId;

    private Boolean alreadyWatched;

    private Boolean watchlist;

    private Integer rate;

    public String getMovieIMDBId() {
        return movieIMDBId;
    }

    public void setMovieIMDBId(String movieIMDBId) {
        this.movieIMDBId = movieIMDBId;
    }

    public Boolean getAlreadyWatched() {
        return alreadyWatched;
    }

    public void setAlreadyWatched(Boolean alreadyWatched) {
        this.alreadyWatched = alreadyWatched;
    }

    public Boolean getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(Boolean watchlist) {
        this.watchlist = watchlist;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}
