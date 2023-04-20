package jy.demo.security.jwt.provider;

import jy.demo.repository.UserRepository;
import jy.demo.security.UserDetailsImpl;
import jy.demo.security.jwt.filter.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JwtProvider implements AuthenticationProvider {

    private final JwtDecoder jwtDecoder;
    private final UserRepository userRepository;

    @Autowired
    public JwtProvider(JwtDecoder jwtDecoder, UserRepository userRepository){
        this.jwtDecoder = jwtDecoder;
        this.userRepository = userRepository;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String) authentication.getPrincipal();
        Map<String, String> jwtData = jwtDecoder.decode(token);

        UserDetailsImpl userDetails = new UserDetailsImpl(jwtData);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtToken.class.isAssignableFrom(authentication);
    }
}

