package br.com.mp.quarkusmovie.resources;

import br.com.mp.quarkusmovie.model.dto.LoginDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class LoginResourceTest {

    final LoginDTO loginDTO = new LoginDTO();

    String token = "";

    @BeforeEach
    public void setUp() {
        loginDTO.setEmail("mp@mp.com");
        loginDTO.setPassword("movie123");
    }

    @Test
    public void login() {
        given().
                contentType(ContentType.JSON)
                .body(loginDTO)
                .when().post("/login")
                .then()
                .statusCode(200);
    }

    @Test
    public void loginToken() {
        token = given().
                contentType(ContentType.JSON)
                .body(loginDTO)
                .when().post("/login")
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("token");
    }

    public String getToken() {
        setUp();
        loginToken();
        return token;
    }

}
