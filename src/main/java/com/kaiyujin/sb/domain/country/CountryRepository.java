package com.kaiyujin.sb.domain.country;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@ConfigAutowireable
@Dao
public interface CountryRepository {
    @Select
    @Cacheable(cacheNames = "countries")
    List<Country> findAll();
}
