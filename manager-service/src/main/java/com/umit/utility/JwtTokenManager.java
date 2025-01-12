package com.umit.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.umit.exception.ErrorType;
import com.umit.exception.ManagerServiceException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtTokenManager {
    private final String SECRETKEY ="secretkey";
    private final String ISSUER ="workforce";
    private final Long EXDATE = 1000L * 60 * 5 ; // 5 minutes

    public Optional<String> createToken (Long id){
        String token;
        try{
            token = JWT.create().withAudience()
                    .withClaim("id", id)
                    .withIssuer(ISSUER)
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXDATE))
                    .sign(Algorithm.HMAC512(SECRETKEY));
            return Optional.of(token);
        }catch (Exception e){
            return Optional.empty();
        }
    }

    public Optional<Long> validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC512(SECRETKEY);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if(decodedJWT == null)
                return Optional.empty();
            Long authId = decodedJWT.getClaim("id").asLong();
            return Optional.of(authId);
        }catch (Exception e){
            return Optional.empty();
        }
    }

    public Optional<Long> getIdFromToken(String token){
        try {
            Algorithm algorithm=Algorithm.HMAC512(SECRETKEY);
            JWTVerifier verifier=JWT.require(algorithm).withIssuer(ISSUER).build();
            DecodedJWT decodedJWT= verifier.verify(token);

            if (decodedJWT==null){
                throw new ManagerServiceException(ErrorType.INVALID_TOKEN);
            }
            Long id=decodedJWT.getClaim("id").asLong();
            return Optional.of(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new ManagerServiceException(ErrorType.INVALID_TOKEN);
        }
    }
}
