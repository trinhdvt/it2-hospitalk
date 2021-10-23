package com.team1.it2hospitalk.service;

import com.team1.it2hospitalk.model.entity.Role;
import com.team1.it2hospitalk.model.entity.User;
import com.team1.it2hospitalk.model.request.CodeDTO;
import com.team1.it2hospitalk.model.request.LoginDTO;
import com.team1.it2hospitalk.model.request.SignUpDTO;
import com.team1.it2hospitalk.model.response.UserDTO;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IAuthServices {

    String login(LoginDTO loginDTO, HttpServletResponse resp);

    String reCreateToken(String clientToken, String accessToken, HttpServletResponse resp);

    CodeDTO createUserCode(CodeDTO codeDTO, Role creatorRole);

    User verifyCode(String code);

    String signUp(SignUpDTO signUpDTO);

    List<UserDTO> getAvailableCodes(Pageable pageable);
}
