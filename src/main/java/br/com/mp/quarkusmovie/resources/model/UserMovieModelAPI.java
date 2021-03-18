package br.com.mp.quarkusmovie.resources.model;

public class UserMovieModelAPI {

    private Long userId;
    private String movieIMDBId;
    private Boolean alreadyWatched;
    private Boolean watchlist;
    private Integer rate;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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
