package com.gestao.backend.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.gestao.backend.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user){
        try {
            //Algorith cria o token com o algoritmo HMAC256
            Algorithm algorithm = Algorithm.HMAC256(secret);

            //Cria o token com o algoritmo e o usuário
            String token = JWT.create()
                    .withIssuer("auth-api")//Emissor do token
                    .withSubject(user.getMatricula())//Sujeito do token
                    .withExpiresAt(getExpirationDate())//Data de expiração do token
                    .sign(algorithm);//Assinatura do token

            return token;
        }catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.

            throw new RuntimeException("Error while generating token", exception);

        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            //Verifica se o token é válido
            String subject = JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
            return subject;
        }catch (JWTCreationException exception){
            return "";
        }
    }

    private Instant getExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
