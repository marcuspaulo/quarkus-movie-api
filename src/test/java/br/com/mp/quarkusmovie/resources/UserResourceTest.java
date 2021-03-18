package br.com.mp.quarkusmovie.resources;

import br.com.mp.quarkusmovie.resources.model.UserDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class UserResourceTest {

    final UserDTO userDTO = new UserDTO();

    @BeforeEach
    public void setUp() {
        userDTO.setName("Marcus Paulo");
        userDTO.setEmail("mp@quarkusmovie.com");
        userDTO.setPassword("P@ssW0OrdM0vie");
    }

    @Test
    @DisplayName("Create User")
    public void create() {

        String email = "mp@quarkusmovie.com";

        String result = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(userDTO)
                .when().post("/users")
                .then()
//                .statusCode(CREATED.getStatusCode())
                .extract().jsonPath().prettyPrint();
//                .extract().response().jsonPath().getString("email");

//        Assertions.assertEquals(result, email);
    }
}
