package com.hk.prj.expenses360.service;

import com.hk.prj.expenses360.config.security.AppUserDetails;
import com.hk.prj.expenses360.model.AppUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public AppUser getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return ((AppUserDetails) authentication.getPrincipal()).getUser();
        }
        return null;
    }
}
