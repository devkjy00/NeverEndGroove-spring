package jy.demo.security.oauth2;

import java.util.List;
import java.util.Map;
import jy.demo.common.HttpResponse;
import jy.demo.exception.DataNotFoundException;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class GoogleOAuth2User extends CustomOAuth2User {

    public static final String ATTRIBUTES_NAME = "name";
    public static final String ATTRIBUTES_EMAIL = "email";
    public static final String ATTRIBUTES_KEY = "sub";

    public GoogleOAuth2User(Map<String, Object> attributes) {

        try {
            if (!attributes.containsKey(ATTRIBUTES_KEY) ||
                !attributes.keySet().containsAll(List.of(ATTRIBUTES_NAME, ATTRIBUTES_EMAIL))) {
                throw new IllegalArgumentException();
            }

            super.name = String.valueOf(attributes.get(ATTRIBUTES_NAME));
            super.email = String.valueOf(attributes.get(ATTRIBUTES_EMAIL));
            super.socialProviderKey = String.valueOf(attributes.get(ATTRIBUTES_KEY));
        } catch (NullPointerException | IllegalArgumentException e) {
            throw new DataNotFoundException(HttpResponse.INVALID_GOOGLE_OAUTH);
        }
    }
}
