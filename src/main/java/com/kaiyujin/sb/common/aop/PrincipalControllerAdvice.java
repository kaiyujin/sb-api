package com.kaiyujin.sb.common.aop;

import com.kaiyujin.sb.common.security.SimpleLoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;
import java.util.Optional;

@ControllerAdvice
public class PrincipalControllerAdvice {

    @ModelAttribute
    public SimpleLoginUser getLoggedInUser(Principal principal) {
        return
                Optional.ofNullable(principal)
                        .filter(p -> p instanceof Authentication).map(p -> (Authentication) p)
                        .map(a -> a.getPrincipal())
                        .filter(p -> p instanceof SimpleLoginUser).map(p -> (SimpleLoginUser) p)
                        .orElse(null);
    }

}
