package com.team1.it2hospitalk.repository;

import com.team1.it2hospitalk.model.entity.TransferForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferFormRepository extends JpaRepository<TransferForm, Integer> {

    List<TransferForm> getAllBySendUser_Username(String username);

    List<TransferForm> getAllByReceiveHospital_Manager_Username(String username);

}