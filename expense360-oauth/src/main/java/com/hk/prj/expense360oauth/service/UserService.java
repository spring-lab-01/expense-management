package com.hk.prj.expense360oauth.service;

import com.hk.prj.expense360oauth.model.AppUser;
import com.hk.prj.expense360oauth.security.AppUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public OAuth2User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return (OAuth2User) authentication.getPrincipal();
        }
        return null;
    }

    public String getLoggedInUserEmail() {
        OAuth2User oAuth2User = getLoggedInUser();
        if(null == oAuth2User){
            throw new RuntimeException("User not logged in");
        }
        return oAuth2User.getAttribute("email");
    }
}
