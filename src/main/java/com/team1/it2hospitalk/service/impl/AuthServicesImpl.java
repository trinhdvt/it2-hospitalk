package com.team1.it2hospitalk.service.impl;

import com.team1.it2hospitalk.exception.HttpError;
import com.team1.it2hospitalk.exception.UnauthorizedError;
import com.team1.it2hospitalk.model.entity.Role;
import com.team1.it2hospitalk.model.entity.User;
import com.team1.it2hospitalk.model.request.CodeDTO;
import com.team1.it2hospitalk.model.request.LoginDTO;
import com.team1.it2hospitalk.model.request.SignUpDTO;
import com.team1.it2hospitalk.model.response.UserDTO;
import com.team1.it2hospitalk.repository.UserRepository;
import com.team1.it2hospitalk.security.SecurityServices;
import com.team1.it2hospitalk.service.IAuthServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.List;

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

    @Override
    public CodeDTO createUserCode(CodeDTO codeDTO, Role creatorRole) {
        // if creator is admin then create user will be manager. And so on
        Role userRole = (creatorRole == Role.ADMIN) ? Role.MANAGER : Role.USER;

        // constructor new user
        User newUser = User.builder()
                .fullName(codeDTO.getName())
                .address(codeDTO.getLocation())
                .job(codeDTO.getDepartment())
                .role(userRole)
                .build();

        // save to database
        newUser = userRepository.saveAndFlush(newUser);

        // get the code and return to client with same payload
        codeDTO.setCode(newUser.getId());
        return codeDTO;
    }

    @Override
    public User verifyCode(String code) {
        User user = userRepository.findById(code).orElse(null);

        // code is already used
        if (user == null || user.getUsername() != null) {
            throw new UnauthorizedError("Invalid code!");
        }

        return user;
    }

    @Override
    public String signUp(SignUpDTO signUpDTO) {
        String username = signUpDTO.getUsername();
        String password = signUpDTO.getPassword();
        String code = signUpDTO.getCode();

        User user = verifyCode(code);

        boolean isUsernameExist = userRepository.existsByUsername(username);
        if (isUsernameExist) {
            throw new HttpError("Username is already existed", HttpStatus.CONFLICT);
        }

        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user = userRepository.saveAndFlush(user);

        return securityServices.createAccessJwt(user);
    }

    @Override
    public List<UserDTO> getAvailableCodes(Pageable pageable) {

        return userRepository.getAllByUsernameIsNull(pageable)
                .map(UserDTO::toUserDTO)
                .getContent();
    }
}
