package jy.demo.security.jwt.provider;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static jy.demo.security.jwt.provider.JwtTokenValue.*;


@Component
public class JwtDecoder {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public Map<String, String> decode(String token){
        DecodedJWT decodedJWT = isValidToken(token)
            .orElseThrow(() -> new IllegalArgumentException("토큰이 유효하지 않습니다"));

        Date expireDate = decodedJWT
            .getClaim(CLAIM_EXPIRED_DATE)
            .asDate();
        Date now = new Date();
        if (expireDate.before(now)) {
            throw new IllegalArgumentException("토큰이 유효하지 않습니다 ");
        }

        Map<String, String> jwtData = new HashMap<>();
        String email = decodedJWT
            .getClaim(CLAIM_USER_EMAIL)
            .asString();
        jwtData.put(CLAIM_USER_EMAIL, email);

        String id = String.valueOf(decodedJWT
            .getClaim(CLAIM_USER_ID)
            .asLong());
        jwtData.put(CLAIM_USER_ID, id);

        String nickname = decodedJWT
            .getClaim(CLAIM_USER_NICK)
            .asString();
        jwtData.put(CLAIM_USER_NICK, nickname);
        return jwtData;
    }

    private Optional<DecodedJWT> isValidToken(String token){
        DecodedJWT jwt = null;

        try{
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            JWTVerifier verifier = JWT
                .require(algorithm)
                .build();

            jwt = verifier.verify(token);
        } catch (Exception e) {

            log.error(e.getMessage());
        }

        return Optional.ofNullable(jwt);
    }
}
