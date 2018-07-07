package com.kaiyujin.sb.controller.auth;

import com.kaiyujin.sb.common.ApplicationSettings;
import com.kaiyujin.sb.common.Constants;
import com.kaiyujin.sb.common.security.SimpleAuthenticationSuccessHandler;
import com.kaiyujin.sb.common.security.SimpleLoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(Constants.AUTH_API_BASE_URL)
public class AuthenticationController {

    @Autowired
    private ApplicationSettings settings;

    @RequestMapping(value = "refreshToken", method = RequestMethod.GET)
    public String refreshToken(HttpServletResponse res, @ModelAttribute SimpleLoginUser simpleLoginUser) {
        var handler = new SimpleAuthenticationSuccessHandler(settings.getSecretKey());
        return handler.getTokenJson(res, handler.generateToken(simpleLoginUser.getUser().getId()));
    }
}
