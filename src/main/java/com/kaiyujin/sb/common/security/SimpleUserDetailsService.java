package com.kaiyujin.sb.common.security;

import com.kaiyujin.sb.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Userエンティティ
 */
public class SimpleUserDetailsService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    public SimpleUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String name) {
        // emailでデータベースからユーザーエンティティを検索する
        return userRepository.findByName(name)
                .map(SimpleLoginUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

}
