package br.com.mp.quarkusmovie.resources;

import br.com.mp.quarkusmovie.resources.model.UserMovieModelAPI;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.Response.Status.*;

@QuarkusTest
public class MovieResourceTest {

    @Inject
    LoginResourceTest loginResourceTest;

    String token = "";


    @BeforeEach
    public void recuperarToken() {
        token = loginResourceTest.recuperarToken();
    }

    @Test
    @DisplayName("Load list Movies")
    public void loadListMoviesTest() {
        given()
          .when().get("movies/list")
          .then()
             .statusCode(200);
    }

    @Test
    @DisplayName("Load list of the best rank Movies")
    public void loadlistBestRatedTest() {
        given()
                .header("Authorization","Bearer " + token)
                .when().get("movies/listBestRated")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Search movies")
    public void searchMoviesTest() {
        String query = "Cidade invisível";
        String imdbID = "tt8878862";

        String result = given()
                .header("Authorization","Bearer " + token)
                .when().get("/movies/search/{query}", query)
                .then()
                .statusCode(200)
                .extract().response().jsonPath().getString(imdbID);

        System.out.println(result);
    }

    @Test
    @DisplayName("Add Movies")
    public void create() {

        String imdbID = "tt8878862";

        UserMovieModelAPI userMovieModelAPI = new UserMovieModelAPI();
        userMovieModelAPI.setMovieIMDBId("tt8878862");
        userMovieModelAPI.setRate(4);
        userMovieModelAPI.setAlreadyWatched(true);
        userMovieModelAPI.setWatchlist(true);

        String result = given()
                .log().all()
                .header("Authorization","Bearer " + token)
                .contentType(ContentType.JSON)
                .body(userMovieModelAPI)
                .when().post("/movies/add")
                .then()
                .statusCode(CREATED.getStatusCode())
                .extract().response().jsonPath().getString("imdbId");

        Assertions.assertEquals(result, imdbID);
    }

    @Test
    @DisplayName("Rate Movie")
    public void rateMovie() {

        UserMovieModelAPI userMovieModelAPI = new UserMovieModelAPI();
        userMovieModelAPI.setMovieIMDBId("tt8878862");
        userMovieModelAPI.setRate(4);
        userMovieModelAPI.setAlreadyWatched(false);
        userMovieModelAPI.setWatchlist(false);

        given()
                .log().all()
                .header("Authorization","Bearer " + token)
                .contentType(ContentType.JSON)
                .body(userMovieModelAPI)
                .when().post("/movies/add")
                .then()
                .statusCode(BAD_REQUEST.getStatusCode());
    }
}