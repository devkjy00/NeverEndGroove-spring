package jy.demo.security.jwt.provider;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jy.demo.security.oauth2.CustomOAuth2User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public final class JwtTokenUtils {

    private static final int SEC = 1;
    private static final int MINUTE = 60 * SEC;
    private static final int HOUR = 60 * MINUTE;
    private static final int DAY = 24 * HOUR;

    private static final int JWT_TOKEN_VALID_SEC = 3 * DAY;
    private static final int JWT_TOKEN_VALID_MILLI_SEC = JWT_TOKEN_VALID_SEC * 1000;

    public static final String CLAIM_EXPIRED_DATE = "EXPIRED_DATE";
    public static final String CLAIM_USER_NICK = "USER_NICK";
    public static final String CLAIM_USER_ID= "USER_ID";
    public static final String CLAIM_USER_EMAIL = "USER_EMAIL";

    public static String JWT_SECRET;

    public static String generateJwtToken(CustomOAuth2User userInfo) {
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

    private static Algorithm generateAlgorithm() {
        return Algorithm.HMAC256(JWT_SECRET);
    }

//    @Value("${spring.jwt.key}")
    public void setName(String key) {
//        this.JWT_SECRET = key;
        this.JWT_SECRET = "test";
    }
}