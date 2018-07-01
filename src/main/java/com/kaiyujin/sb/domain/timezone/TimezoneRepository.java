package com.kaiyujin.sb.domain.timezone;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@ConfigAutowireable
@Dao
public interface TimezoneRepository {
    @Select
    @Cacheable(cacheNames = "timezones")
    List<Timezone> findAll();
}
