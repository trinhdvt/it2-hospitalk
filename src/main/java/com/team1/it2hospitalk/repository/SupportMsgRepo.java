package com.team1.it2hospitalk.repository;

import com.team1.it2hospitalk.model.entity.SupportMsg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupportMsgRepo extends JpaRepository<SupportMsg, Integer> {

    Page<SupportMsg> getAllByCreator_ManageHospital_Id(int hospitalId, Pageable pageable);

}
