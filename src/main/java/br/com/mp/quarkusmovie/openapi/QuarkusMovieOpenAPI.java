package br.com.mp.quarkusmovie.openapi;

import org.eclipse.microprofile.openapi.annotations.Components;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeIn;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.security.*;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        tags = {
                @Tag(name = "movie", description = "Movies and Series"),
        },
//        security = @SecurityRequirement(name = "movie-jtw", scopes = {"admin"}),
//        components = @Components(
//                securitySchemes = {
//                        @SecurityScheme(
//                                securitySchemeName = "movie-jtw",
//                                type = SecuritySchemeType.HTTP,
//                                in = SecuritySchemeIn.HEADER,
//                                scheme = "Bearer"
////                                flows = @OAuthFlows(
////                                        clientCredentials = @OAuthFlow(
////                                                tokenUrl = "http://localhost:8080/login",
////                                                scopes = {
////                                                        @OAuthScope(name = "User"),
////                                                }
////                                        )
////                                )
//                        )
//                }),
        info = @Info(
                title = "Quarkus Movie API",
                version = "1.0.0",
                contact = @Contact(
                        name = "MP - Quarkus Movie API Support",
                        url = "http://quarkusmovie-api.com/contact",
                        email = "contact@example.com"),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"))
)
public class QuarkusMovieOpenAPI extends Application {
}

