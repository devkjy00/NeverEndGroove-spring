package jy.demo.security.jwt.provider;

import java.util.List;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

public interface JwtAuthentication {
    AuthenticationProvider getProvider();

}
