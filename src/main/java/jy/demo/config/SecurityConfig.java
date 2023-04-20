package jy.demo.config;

import java.util.ArrayList;
import java.util.List;
import jy.demo.security.jwt.filter.FilterSkipMatcher;
import jy.demo.security.jwt.filter.HeaderTokenExtractor;
import jy.demo.security.jwt.filter.JwtAuthFilter;
import jy.demo.security.jwt.provider.JwtAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthentication jwtAuthentication;
    private final HeaderTokenExtractor headerTokenExtractor;

    @Autowired
    public SecurityConfig(JwtAuthentication jwtAuthentication,
        HeaderTokenExtractor headerTokenExtractor) {
        this.jwtAuthentication = jwtAuthentication;
        this.headerTokenExtractor = headerTokenExtractor;
    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(jwtAuthentication.getProvider());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception{

        http
            .csrf().disable()
//            .cors().configurationSource(corsConfigurationSource())
//            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

//        http
//            .oauth2Login()
//            .defaultSuccessUrl("/api/main")
//            .successHandler(customAuthenticationSuccessHandler)
//            .failureHandler(customAuthenticationFailureHandler)
//            .userInfoEndpoint()
//            .userService(customOAuth2UserService);
    }


    private JwtAuthFilter jwtFilter() throws Exception {
        List<String> skipPathList = new ArrayList<>();

        // 회원 관리 API 허용
        skipPathList.add("POST,/signup");
        skipPathList.add("POST,/login");
        skipPathList.add("GET,/login");
        skipPathList.add("GET,/main");
        skipPathList.add("GET,/test");

        FilterSkipMatcher matcher = new FilterSkipMatcher(
            skipPathList,
            "/**"
        );

        JwtAuthFilter filter = new JwtAuthFilter(
            matcher,
            headerTokenExtractor
        );
        filter.setAuthenticationManager(super.authenticationManagerBean());

        return filter;
    }

}
