package com.hk.prj.expenses360.repository;

import com.hk.prj.expenses360.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsernameIgnoreCaseOrEmailIgnoreCase(String username, String email);
}
