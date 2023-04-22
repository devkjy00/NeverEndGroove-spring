package jy.demo.security.jwt.provider;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jy.demo.security.oauth2.CustomOAuth2User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

import static jy.demo.security.jwt.provider.JwtTokenValue.*;

@Slf4j
@Component
public final class JwtTokenUtils {

    public String generateJwtToken(CustomOAuth2User userInfo) {
        String token = null;
        try {
            token = JWT.create()
                .withIssuer("huddleUp")
                //                .withClaim(CLAIM_USER_EMAIL, userInfo.getName())
                .withClaim(CLAIM_USER_ID, userInfo.getId())
                .withClaim(CLAIM_USER_NICK, userInfo.getName())
                .withClaim(CLAIM_EXPIRED_DATE, new Date(System.currentTimeMillis() + JWT_TOKEN_VALID_MILLI_SEC))
                .sign(generateAlgorithm());

            log.info(token);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return token;
    }

    private Algorithm generateAlgorithm() {
        return Algorithm.HMAC256(JWT_SECRET);
    }

}