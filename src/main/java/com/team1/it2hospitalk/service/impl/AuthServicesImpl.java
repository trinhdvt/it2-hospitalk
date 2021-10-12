package com.team1.it2hospitalk.service.impl;

import com.team1.it2hospitalk.exception.HttpError;
import com.team1.it2hospitalk.exception.UnauthorizedError;
import com.team1.it2hospitalk.model.entity.User;
import com.team1.it2hospitalk.model.request.LoginDTO;
import com.team1.it2hospitalk.repository.UserRepository;
import com.team1.it2hospitalk.security.SecurityServices;
import com.team1.it2hospitalk.service.IAuthServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AuthServicesImpl implements IAuthServices {

    private final UserRepository userRepository;

    private final SecurityServices securityServices;

    private final AuthenticationManager authManager;

    private final PasswordEncoder passwordEncoder;

    @Override
    public String login(LoginDTO loginDTO, HttpServletResponse resp) {
        String userName = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        try {
            // attempting to authenticate
            authManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));

            // create access token
            User user = userRepository.findByUsername(userName);
            return securityServices.createAccessJwt(user);

        } catch (LockedException e) {
            throw new HttpError("Account is blocked", HttpStatus.LOCKED);

        } catch (AuthenticationException e) {
            throw new UnauthorizedError("Invalid username or password");
        }

    }

    @Override
    public String reCreateToken(String clientToken, String accessToken, HttpServletResponse resp) {
        return null;
    }
}
