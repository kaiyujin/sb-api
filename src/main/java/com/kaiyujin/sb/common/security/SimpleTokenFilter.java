package com.kaiyujin.sb.common.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kaiyujin.sb.domain.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
public class SimpleTokenFilter extends GenericFilterBean {

    final private UserRepository userRepository;
    final private Algorithm algorithm;
    final private SimpleAuthenticationSuccessHandler handler;

    public SimpleTokenFilter(UserRepository userRepository, String secretKey) {
        Objects.requireNonNull(secretKey, "secret key must be not null");
        this.userRepository = userRepository;
        this.algorithm = Algorithm.HMAC256(secretKey);
        this.handler = new SimpleAuthenticationSuccessHandler(secretKey);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String token = resolveToken(request);

        if (Objects.isNull(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            var userId = authentication(verifyToken(token));
            handler.setToken((HttpServletResponse) response, handler.generateToken(userId));
        } catch (JWTVerificationException e) {
            log.error("verify token error", e);
            SecurityContextHolder.clearContext();
            ((HttpServletResponse) response).sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
        }
        filterChain.doFilter(request, response);
    }

    private String resolveToken(ServletRequest request) {
        String token = ((HttpServletRequest) request).getHeader("Authorization");
        if (Objects.isNull(token) || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7);
    }

    private DecodedJWT verifyToken(String token) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token);
    }

    private Long authentication(DecodedJWT jwt) {
        Long userId = Long.valueOf(jwt.getSubject());
        userRepository.findById(userId).ifPresent(user -> {
            SimpleLoginUser simpleLoginUser = new SimpleLoginUser(user);
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(simpleLoginUser, null, simpleLoginUser.getAuthorities()));
        });
        return userId;
    }

}
