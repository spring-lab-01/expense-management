package com.hk.prj.expense360oauth.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class AppOAuth2User implements OAuth2User {

    private OAuth2User oAuth2User;

    public AppOAuth2User(OAuth2User oAuth2User) {
        this.oAuth2User = oAuth2User;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oAuth2User.getAuthorities();
//        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
//        return Arrays.asList(authority);
    }

    @Override
    public String getName() {
        return oAuth2User.getName();
    }

    public String getEmail() {
        return oAuth2User.<String>getAttribute("email");
    }

}
