package com.hk.prj.expense360oauth.service;

import com.hk.prj.expense360oauth.model.AppOAuth2User;
import com.hk.prj.expense360oauth.model.AppUser;
import com.hk.prj.expense360oauth.repository.AppUserRepository;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class OAuthUsersService extends DefaultOAuth2UserService {

    private final AppUserRepository appUserRepository;

    public OAuthUsersService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public AppOAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("On OAuthUsersService  ------------ ***************** " + oAuth2User);
        return new AppOAuth2User(oAuth2User);
    }

    public void saveUserAfterOAuth2Authentication(OAuth2User oAuth2User) {
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        AppUser appUser = appUserRepository.findByUsernameIgnoreCaseOrEmailIgnoreCase(email, email);
        if (appUser == null) {
            appUser = new AppUser();
        }
        appUser.setEmail(email);
        appUser.setFullname(name);
        appUser.setUsername(email.toLowerCase().replace("@gmail.com", ""));
        appUserRepository.save(appUser);
        //TODO - remove this after testing
        appUserRepository.findAll().stream().forEach(System.out::println);
    }

}
