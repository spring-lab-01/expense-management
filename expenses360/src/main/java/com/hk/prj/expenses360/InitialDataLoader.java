package com.hk.prj.expenses360;

import com.hk.prj.expenses360.model.AppUser;
import com.hk.prj.expenses360.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitialDataLoader implements CommandLineRunner {
    @Autowired
    AppUserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {
        AppUser admin = new AppUser();
        admin.setUsername("admin");
        admin.setPassword(bCryptPasswordEncoder.encode("admin"));
        admin.setRole("ADMIN");
        userRepository.save(admin);

        AppUser user = new AppUser();
        user.setUsername("user");
        user.setPassword(bCryptPasswordEncoder.encode("user"));
        user.setRole("USER");
        userRepository.save(user);
    }
}
