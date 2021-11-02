package com.team1.it2hospitalk.repository;

import com.team1.it2hospitalk.model.entity.Hospital;
import com.team1.it2hospitalk.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);

    boolean existsByUsername(String username);

    Page<User> getAllByUsernameIsNull(Pageable pageable);

    Page<User> getAllByUsernameIsNotNull(Pageable pageable);

    Page<User> getAllByWorkHospital(Hospital workHospital, Pageable pageable);

    Page<User> getAllByWorkHospital_Id(int hospitalId, Pageable pageable);
}
