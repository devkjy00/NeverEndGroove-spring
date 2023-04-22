package jy.demo.security.oauth2;

import jy.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Autowired
    public CustomOAuth2UserService(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> defaultOAuth2UserService = new DefaultOAuth2UserService();

        OAuth2User defaultOAuth2User = defaultOAuth2UserService.loadUser(userRequest);
        CustomOAuth2User customOAuth2User = new GoogleOAuth2User(defaultOAuth2User.getAttributes());

        userService.saveOrUpdate(customOAuth2User);

        return customOAuth2User;
    }

}
