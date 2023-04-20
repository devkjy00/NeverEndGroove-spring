package jy.demo.security.jwt.filter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class JwtToken extends UsernamePasswordAuthenticationToken {

    private JwtToken(
        Object principal,
        Object credentials
    ) {
        super(
            principal,
            credentials
        );
    }

    public JwtToken(String token) {
        this(
            token,
            token.length()
        );
    }
}
