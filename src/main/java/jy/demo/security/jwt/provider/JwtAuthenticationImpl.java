package jy.demo.security.jwt.provider;

import jy.demo.security.oauth2.CustomOAuth2User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationImpl implements JwtAuthentication {

    private final JwtProvider jwtProvider;
    private final JwtTokenUtils jwtTokenUtils;

    @Autowired
    JwtAuthenticationImpl(JwtProvider jwtProvider, JwtTokenUtils jwtTokenUtils){
        this.jwtProvider = jwtProvider;
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @Override
    public AuthenticationProvider getProvider() {
        return jwtProvider;
    }

    @Override
    public String generateToken(CustomOAuth2User user) {
        return jwtTokenUtils.generateJwtToken(user);
    }


}
