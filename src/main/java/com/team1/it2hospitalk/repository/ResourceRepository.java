package com.team1.it2hospitalk.repository;

import com.team1.it2hospitalk.model.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Integer> {

    boolean existsByOwner_UsernameAndResourceName(String username, String fileName);
}
