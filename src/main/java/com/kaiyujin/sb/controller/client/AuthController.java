package com.kaiyujin.sb.controller.client;

import com.kaiyujin.sb.common.JWT;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/client/auth")
public class AuthController {
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    public void login(HttpServletResponse res) {
        new JWT().build();
    }
}
