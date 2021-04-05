package br.com.mp.quarkusmovie.util;

import br.com.mp.quarkusmovie.model.User;
import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.Claims;

import javax.enterprise.context.ApplicationScoped;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;

@ApplicationScoped
public class AuthTokenService {

    @ConfigProperty(name = "mp.jwt.verify.issuer")
    String issuer;

    public String generateTokenJWT(User user) {

        String token =
                Jwt.issuer(issuer)
                        .expiresAt(Instant.now().plus(Duration.ofHours(1)))
                        .upn("marcus-movie@app-movie-api.io")
                        .groups(new HashSet<>(Arrays.asList("User")))
                        .claim(Claims.email, user.getEmail())
                        .sign();
        System.out.println(token);

        return token;
    }
}
