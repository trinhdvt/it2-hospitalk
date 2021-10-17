package com.team1.it2hospitalk.service;

import com.team1.it2hospitalk.model.entity.Role;
import com.team1.it2hospitalk.model.request.CodeDTO;
import com.team1.it2hospitalk.model.request.LoginDTO;

import javax.servlet.http.HttpServletResponse;

public interface IAuthServices {

    String login(LoginDTO loginDTO, HttpServletResponse resp);

    String reCreateToken(String clientToken, String accessToken, HttpServletResponse resp);

    CodeDTO createUserCode(CodeDTO codeDTO, Role creatorRole);
}
