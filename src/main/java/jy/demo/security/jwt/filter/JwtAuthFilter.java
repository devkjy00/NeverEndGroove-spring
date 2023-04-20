package jy.demo.security.jwt.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import jy.demo.common.HttpResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet
public class JwtAuthFilter extends AbstractAuthenticationProcessingFilter {

    private final HeaderTokenExtractor extractor;
    private final ObjectMapper mapper;

    public JwtAuthFilter(
        RequestMatcher requestMatcher,
        HeaderTokenExtractor extractor){
        super(requestMatcher);
        this.extractor = extractor;
        this.mapper = new ObjectMapper();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String tokenPayload = request.getHeader("Authorization");
        if (Objects.isNull(tokenPayload)){
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            ResponseEntity<String> responseEntity =
                HttpResponse.TOKEN_NOT_FOUND.getResponseEntity();

            String message = mapper.writeValueAsString(responseEntity);

            response.getWriter().write(message);

            throw new IllegalArgumentException();
        }

        JwtToken jwtToken = new JwtToken(
            extractor.extract(tokenPayload, request));

        return super
            .getAuthenticationManager()
            .authenticate(jwtToken);
    }

    @Override
    protected void successfulAuthentication(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain,
        Authentication authResult
    ) throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);

        chain.doFilter(
            request,
            response
        );
    }

    @Override
    protected void unsuccessfulAuthentication(
        HttpServletRequest request,
        HttpServletResponse response,
        AuthenticationException failed
    ) throws IOException, ServletException {
        SecurityContextHolder.clearContext();

        super.unsuccessfulAuthentication(
            request,
            response,
            failed
        );
    }


}