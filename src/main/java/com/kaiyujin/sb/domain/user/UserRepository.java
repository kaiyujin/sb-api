package com.kaiyujin.sb.domain.user;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.cache.annotation.Cacheable;

import java.util.Optional;

@ConfigAutowireable
@Dao
public interface UserRepository {
    @Select
    Optional<User> findByName(String name);

    @Select
    @Cacheable(cacheNames = "user")
    Optional<User> findById(Long id);
}
