package com.kaiyujin.sb.domain.user;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.Optional;

@ConfigAutowireable
@Dao
public interface UserRepository {
    @Select
    Optional<User> findByName(String name);

    @Select
    Optional<User> findById(Long id);
}
