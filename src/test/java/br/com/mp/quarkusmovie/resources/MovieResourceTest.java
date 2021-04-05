package br.com.mp.quarkusmovie.resources;

import br.com.mp.quarkusmovie.model.dto.UserMovieModelAPI;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
public class MovieResourceTest {

    @Inject
    LoginResourceTest loginResourceTest;

    String token = "";

    @BeforeEach
    public void getToken() {
        token = loginResourceTest.getToken();
    }

    @Test
    @DisplayName("Search Movies")
    public void searchMoviesTest() {
        String query = "Cidade invisível";
        String imdbID = "tt8878862";

        given()
            .when()
            .get("/movies/search/{query}", query)
            .then()
            .statusCode(200)
                .body(containsString(imdbID))
                .extract()
                .jsonPath()
                .getString(imdbID);
    }

    @Test
    @DisplayName("Load list movies")
    public void loadListMoviesTest() {
        given()
          .when().get("/movies")
          .then()
             .statusCode(200);
    }

    @Test
    @DisplayName("List best rate movies")
    public void listBestRatedTest() {
        given()
            .when()
            .get("/movies/listBestRated")
            .then()
            .statusCode(200);
    }

    @Test
    @DisplayName("Rating/Evaluate Movie")
    public void evaluate() {

        UserMovieModelAPI userMovieModelAPI = new UserMovieModelAPI();
        userMovieModelAPI.setMovieIMDBId("tt8878862");
        userMovieModelAPI.setRate(4);
        userMovieModelAPI.setAlreadyWatched(true);
        userMovieModelAPI.setWatchlist(true);


        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .body(userMovieModelAPI)
                .contentType(ContentType.JSON)
                .post("/movies/evaluate")
                .then()
                .statusCode(201);
    }

}