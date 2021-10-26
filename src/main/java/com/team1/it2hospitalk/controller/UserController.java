package com.team1.it2hospitalk.controller;

import com.team1.it2hospitalk.model.entity.Role;
import com.team1.it2hospitalk.model.response.UserDTO;
import com.team1.it2hospitalk.service.IUserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Validated
public class UserController {

    private final IUserServices userServices;

    @RequestMapping("/user")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<?> getListOfUser(@PageableDefault(sort = {"fullName"}) Pageable pageable,
                                           Authentication auth) {

        boolean isAdmin = auth.getAuthorities().contains(Role.ADMIN);
        if (isAdmin) {
            List<UserDTO> listOfUsers = userServices.getListOfAllUser(pageable);

            return ResponseEntity.ok(listOfUsers);
        } else {
            String username = auth.getName();
            List<UserDTO> listOfUsers = userServices.getListOfAllUserManageBy(username, pageable);

            return ResponseEntity.ok(listOfUsers);
        }

    }
}
