package com.hk.prj.expense360oauth.repository;

import com.hk.prj.expense360oauth.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsernameIgnoreCaseOrEmailIgnoreCase(String username, String email);
}
