package jy.demo.security.jwt.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationImpl implements JwtAuthentication {

    private final JwtProvider jwtProvider;

    @Autowired
    JwtAuthenticationImpl(JwtProvider jwtProvider){
        this.jwtProvider = jwtProvider;
    }

    @Override
    public AuthenticationProvider getProvider() {
        return jwtProvider;
    }
}
