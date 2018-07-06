package com.kaiyujin.sb.common.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

/**
 * 認証が成功した時の処理
 */
@Slf4j
public class SimpleAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    final private Algorithm algorithm;

    final private static String AUTH_BEARER = "{\"Authorization\": \"Bearer %s\"}\n";

    public SimpleAuthenticationSuccessHandler(String secretKey) {
        this.algorithm = Algorithm.HMAC256(secretKey);
    }

    private static final Long EXPIRATION_TIME = 1000L * 60L * 30L;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication auth)  {
        if (response.isCommitted()) {
            log.info("Response has already been committed.");
            return;
        }
        setToken(response, generateToken(auth));
        response.setStatus(HttpStatus.OK.value());
        clearAuthenticationAttributes(request);
    }

    private String generateToken(Authentication auth) {
        SimpleLoginUser loginUser = (SimpleLoginUser) auth.getPrincipal();
        return generateToken(loginUser.getUser().getId());
    }

    public String generateToken(Long userId) {
        Date issuedAt = new Date();
        Date notBefore = new Date(issuedAt.getTime());
        Date expiresAt = new Date(issuedAt.getTime() + EXPIRATION_TIME);
        String token = JWT.create()
                .withIssuedAt(issuedAt)
                .withNotBefore(notBefore)
                .withExpiresAt(expiresAt)
                .withSubject(Objects.toString(userId))
                .sign(this.algorithm);
        if( log.isDebugEnabled() ) {
            log.debug("generate token : {}", token);
        }
        return token;
    }

    public void setToken(HttpServletResponse response, String token) {
        try {
            response.getWriter().append(
                    String.format(AUTH_BEARER,token
            )).flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getTokenJson(HttpServletResponse response, String token) {
        return String.format(AUTH_BEARER,token);
    }

    /**
     * Removes temporary authentication-related data which may have been stored in the
     * session during the authentication process.
     */
    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (Objects.isNull(session)) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

}
