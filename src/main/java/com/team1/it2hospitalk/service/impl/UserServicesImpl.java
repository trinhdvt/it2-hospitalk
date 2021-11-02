package com.team1.it2hospitalk.service.impl;

import com.team1.it2hospitalk.model.entity.User;
import com.team1.it2hospitalk.model.response.UserDTO;
import com.team1.it2hospitalk.repository.UserRepository;
import com.team1.it2hospitalk.service.IUserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserServicesImpl implements IUserServices {

    private final UserRepository userRepository;

    @Override
    public List<UserDTO> getListOfAllUser(Pageable pageable) {
        return userRepository.getAllByUsernameIsNotNull(pageable)
                .map(UserDTO::toUserDTO)
                .getContent();
    }

    @Override
    public List<UserDTO> getListOfAllUser(int hospitalId, Pageable pageable) {
        return userRepository.getAllByWorkHospital_Id(hospitalId, pageable)
                .map(UserDTO::toUserDTO)
                .getContent();
    }

    @Override
    public List<UserDTO> getListOfAllUserManageBy(String username, Pageable pageable) {
        User manager = userRepository.findByUsername(username);

        return userRepository.getAllByWorkHospital(manager.getManageHospital(), pageable)
                .map(UserDTO::toUserDTO)
                .getContent();
    }
}
