package com.example.avochadbackend.security;

import org.springframework.stereotype.Component;
import java.time.ZonedDateTime;
import java.util.Date;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.beans.factory.annotation.Value;

@Component
public class JWTUtil {
    
    @Value("${jwt.secret}")
    private String key;

    // generate token
    public String generateToken(String username) {
        Date expirationDate = Date.from(ZonedDateTime.now().plusMinutes(60).toInstant());

        return JWT.create()
            .withSubject("User details")
            .withClaim("username", username)
            .withIssuedAt(new Date())
            .withIssuer("avochad")
            .withExpiresAt(expirationDate)
            .sign(Algorithm.HMAC256(key));
    }

    //  validate token
    public String validateToken(String token) {
       
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(key))
            .withSubject("User details")
            .withIssuer("avochad")
            .build();

        DecodedJWT decodeJWT = verifier.verify(token);

        return decodeJWT.getClaim("username").asString();
    }


}
