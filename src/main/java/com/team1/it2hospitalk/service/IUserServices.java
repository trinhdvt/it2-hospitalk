package com.team1.it2hospitalk.service;

import com.team1.it2hospitalk.model.response.UserDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserServices {

    List<UserDTO> getListOfAllUser(Pageable pageable);

    List<UserDTO> getListOfAllUser(int hospitalId, Pageable pageable);

    List<UserDTO> getListOfAllUserManageBy(String username, Pageable pageable);
}
