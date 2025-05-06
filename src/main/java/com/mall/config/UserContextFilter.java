package com.mall.config;

import com.mall.application.dto.UserContext;
import com.mall.application.dto.UserInfo;
import com.mall.code.RoleType;
import com.mall.code.UserStatus;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Component
public class UserContextFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;

    public static final List<String> EXCLUDED_END_POINT = List.of(
            "/public/**",
            "/auth/login",
            "/auth/signup",
            "/ws-chat/**"
    );

    public UserContextFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return EXCLUDED_END_POINT.stream().anyMatch(pattern -> new AntPathMatcher().match(pattern, path));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            if (request.getHeaders(HttpHeaders.AUTHORIZATION) == null) {
                throw new RuntimeException("no authorization header");
            }

            String authorizationHeader = Objects.requireNonNull(request.getHeaders(HttpHeaders.AUTHORIZATION)).nextElement();
            String token = authorizationHeader.replace("Bearer", "");

            if (!jwtTokenProvider.verify(token)) {
                throw new RuntimeException("JWT Token is not valid");
            }

            Claims claims = jwtTokenProvider.parse(token);
            Integer id = claims.get("id", Integer.class);
            String email = claims.get("email", String.class);
            String status = claims.get("status", String.class);
            String role = claims.get("role", String.class);

            if (id != null && email != null) {
                UserContext.set(new UserInfo(Long.valueOf(id), email, UserStatus.valueOf(status), RoleType.valueOf(role)));
            }

            filterChain.doFilter(request, response);
        } finally {
            UserContext.clear();
        }
    }
}
