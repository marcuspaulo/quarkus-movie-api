package br.com.mp.quarkusmovie.resources;

import br.com.mp.quarkusmovie.model.User;
import br.com.mp.quarkusmovie.model.dto.UserDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class UserResourceTest {
    final UserDTO userDTO = new UserDTO();

    @BeforeEach
    public void setUp() {
        userDTO.setName("Marcus");
        userDTO.setEmail("mp@email.com");
        userDTO.setPassword("movie123");
    }

    @Test
    @DisplayName("Create user test")
    public void createUserTest() {
        given()
                .when()
                .contentType(ContentType.JSON)
                .body(userDTO)
                .post("/users")
                .then()
                .statusCode(201);
    }
}
