package com.jefiro.rastreamento.android.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.jefiro.rastreamento.android.Model.UserModel;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    public String genereteToken(UserModel user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("Jefiroo");
            return JWT.create().withIssuer("jefiro-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(gerInstant())
                    .sign(algorithm);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("Jefiroo");
            return JWT.require(algorithm)
                    .withIssuer("jefiro-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Instant gerInstant() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
