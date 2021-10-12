package com.team1.it2hospitalk.repository;

import com.team1.it2hospitalk.model.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
}
