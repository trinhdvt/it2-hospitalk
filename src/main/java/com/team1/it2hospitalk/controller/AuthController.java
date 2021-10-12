package com.team1.it2hospitalk.controller;

import com.team1.it2hospitalk.model.request.LoginDTO;
import com.team1.it2hospitalk.service.IAuthServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Validated
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AuthController {

    private final IAuthServices authServices;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid LoginDTO loginDTO, HttpServletResponse resp) {

        String jwt = authServices.login(loginDTO, resp);

        Map<String, String> respBody = Collections.singletonMap("token", jwt);
        return ResponseEntity.ok(respBody);
    }
}
