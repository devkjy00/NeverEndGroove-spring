package jy.demo.security.oauth2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jy.demo.security.jwt.provider.JwtAuthenticationImpl;
import jy.demo.security.jwt.provider.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Value("${frontend.url}")
    private String FRONTEND_URL;
    private final JwtAuthenticationImpl jwtAuthentication;

    public CustomAuthenticationSuccessHandler(JwtAuthenticationImpl jwtAuthentication) {
        this.jwtAuthentication = jwtAuthentication;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {

        log.info("social login success");

        String jwt = jwtAuthentication.generateToken((CustomOAuth2User) authentication.getPrincipal());

        Cookie cookie = new Cookie("Authorization", jwt);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        response.sendRedirect(FRONTEND_URL);

    }

}