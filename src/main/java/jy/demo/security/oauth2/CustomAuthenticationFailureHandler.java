package jy.demo.security.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jy.demo.common.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private final ObjectMapper mapper;

    public CustomAuthenticationFailureHandler() {
        this.mapper = new ObjectMapper();
    }


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        ResponseEntity<String> responseEntity =
            HttpResponse.INVALID_ID_PASSWORD.getResponseEntity();

        String message = mapper.writeValueAsString(responseEntity);

        response.getWriter().write(message);
    }

}
