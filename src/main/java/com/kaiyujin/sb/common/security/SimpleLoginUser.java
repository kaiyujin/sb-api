package com.kaiyujin.sb.common.security;

import com.kaiyujin.sb.domain.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleLoginUser extends org.springframework.security.core.userdetails.User {

    private User user;

    private static final Map<Integer, List<GrantedAuthority>> ROLE_MAP = new HashMap();

    static {
        ROLE_MAP.put(1, AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_SUPPORT", "ROLE_USER"));
        ROLE_MAP.put(2, AuthorityUtils.createAuthorityList("ROLE_SUPPORT", "ROLE_USER"));
        ROLE_MAP.put(3, AuthorityUtils.createAuthorityList("ROLE_USER"));
    }

    public User getUser() {
        return user;
    }

    public SimpleLoginUser(User user) {
        super(user.getName(), user.getPassword(), ROLE_MAP.get(user.getRoleType()));
        this.user = user;
    }

}
