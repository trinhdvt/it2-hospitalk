package com.team1.it2hospitalk.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final SecurityServices securityServices;

    @Autowired
    public JwtFilter(SecurityServices securityServices) {
        this.securityServices = securityServices;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = securityServices.getJwtFromRequest(request);

        try {
            if (jwt != null && securityServices.isValidJwt(jwt)) {
                Authentication auth = securityServices.getAuthentication(jwt);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            response.sendError(403, "Forbidden");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
