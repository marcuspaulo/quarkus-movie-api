package br.com.mp.quarkusmovie.resources;

import br.com.mp.quarkusmovie.model.User;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

@QuarkusTest
//@TestHTTPEndpoint(LoginResource.class)
public class LoginResourceTest {

    final User user = new User();

    public String token = "";

    @BeforeEach
    public void setUp() {
        user.setEmail("mp@mp.com");
        user.setPassword("movie123");
    }

    @Test
    public void login() {
        given()
                .contentType(ContentType.JSON)
                .body(user)
                .when().post("/login")
                .then()
                .statusCode(200);
    }

    @Test
    public void loginToken() {
        token = given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/login")
                .then()
                .statusCode(200)
                .body(containsString("token"))
                .extract()
                .response().jsonPath().getString("token");
    }

    public String recuperarToken() {
        setUp();
        loginToken();
        return token;
    }
}
