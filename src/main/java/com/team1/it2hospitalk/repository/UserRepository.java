package com.team1.it2hospitalk.repository;

import com.team1.it2hospitalk.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);
}
