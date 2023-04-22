package jy.demo.security.jwt.provider;

import java.util.List;
import jy.demo.security.oauth2.CustomOAuth2User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

public interface JwtAuthentication {
    AuthenticationProvider getProvider();
    String generateToken(CustomOAuth2User user);

}
