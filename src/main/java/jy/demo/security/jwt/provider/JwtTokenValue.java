package jy.demo.security.jwt.provider;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public final class JwtTokenValue {

    public static final int SEC = 1;
    public static final int MINUTE = 60 * SEC;
    public static final int HOUR = 60 * MINUTE;
    public static final int DAY = 24 * HOUR;

    public static final int JWT_TOKEN_VALID_SEC = 3 * DAY;
    public static final int JWT_TOKEN_VALID_MILLI_SEC = JWT_TOKEN_VALID_SEC * 1000;

    public static final String CLAIM_EXPIRED_DATE = "EXPIRED_DATE";
    public static final String CLAIM_USER_NICK = "USER_NICK";
    public static final String CLAIM_USER_ID= "USER_ID";
    public static final String CLAIM_USER_EMAIL = "USER_EMAIL";

    public static String JWT_SECRET;


    @Value("${jwt.key}")
    public void setJwtSecret(String key) {
        this.JWT_SECRET = key;
    }


}
