package com.kaiyujin.sb.domain.timezone;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@ConfigAutowireable
@Dao
public interface TimezoneRepository {
    @Select
    List<Timezone> findAll();
}
