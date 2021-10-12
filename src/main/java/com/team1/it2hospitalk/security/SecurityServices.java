package com.team1.it2hospitalk.security;

import com.team1.it2hospitalk.model.entity.User;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SecurityServices {

    @Value("${security.jwt.prefix}")
    private String PREFIX;

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    @Value("${security.jwt.expire-length}")
    private int JWT_EXPIRE_TIME;

    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public SecurityServices(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostConstruct
    protected void initialize() {
        this.SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
    }

    public String createAccessJwt(User user) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("sub", user.getUsername());
        payload.put("role", user.getRole().getAuthority());
        payload.put("age", user.getAge());
        payload.put("full_name", user.getFullName());
        payload.put("job", user.getJob());
        payload.put("phone", user.getPhoneNumber());
        payload.put("address", user.getAddress());
        payload.put("avatar_url", user.getAvatarUrl());

        Date now = new Date();
        Date expiresDate = new Date(now.getTime() + this.JWT_EXPIRE_TIME);

        return Jwts.builder()
                .setClaims(payload)
                .setId(user.getId())
                .setIssuedAt(now)
                .setExpiration(expiresDate)
                .signWith(SignatureAlgorithm.HS256, this.SECRET_KEY)
                .compact();
    }

    public String getUserName(String jwt) {

        try {
            return Jwts.parser()
                    .setSigningKey(this.SECRET_KEY)
                    .parseClaimsJws(jwt)
                    .getBody()
                    .get("sub", String.class);

        } catch (ExpiredJwtException e) {
            return e.getClaims().get("sub", String.class);
        }
    }

    public boolean isValidJwt(String jwt) {
        try {
            Jwts.parser()
                    .setSigningKey(this.SECRET_KEY)
                    .parseClaimsJws(jwt);
            return true;

        } catch (ExpiredJwtException e) {
            return false;

        } catch (JwtException | IllegalArgumentException e) {
            throw new RuntimeException("Invalid JWT");
        }
    }


    public Authentication getAuthentication(String jwt) {
        String username = getUserName(jwt);
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getJwtFromRequest(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith(this.PREFIX + " ")) {
            return bearerToken.replace(this.PREFIX + " ", "");
        }
        return null;
    }
}
