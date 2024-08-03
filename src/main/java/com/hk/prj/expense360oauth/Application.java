package com.hk.prj.expense360oauth;

import com.hk.prj.expense360oauth.model.AppUser;
import com.hk.prj.expense360oauth.repository.AppUserRepository;
import com.hk.prj.expense360oauth.security.AppUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
