package com.hk.prj.expense360oauth.controllers;

import com.hk.prj.expense360oauth.model.AppUser;
import com.hk.prj.expense360oauth.repository.AppUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping()
//@PreAuthorize(value = "ROLE_ADMIN")
public class UsersController {
    private final AppUserRepository appUserRepository;

    public UsersController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView getUsers(){
        ModelAndView mav = new ModelAndView("users");
        mav.addObject("users", getUsersList());
        return mav;
    }

    public List<AppUser> getUsersList(){
        return appUserRepository.findAll();
    }

}
