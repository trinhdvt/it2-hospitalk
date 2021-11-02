package com.team1.it2hospitalk.repository;

import com.team1.it2hospitalk.model.entity.Channel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Channel, Integer> {

    Page<Channel> getChannelByCreator_Username(String username, Pageable pageable);

    Page<Channel> getChannelByCreator_ManageHospital_Id(int hospitalId, Pageable pageable);
}
