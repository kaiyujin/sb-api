package com.kaiyujin.sb.domain.shop;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

import java.util.List;

@ConfigAutowireable
@Dao
public interface ShopRepository {

    @Select
    Shop findById(Long id);

    @Select
    List<Shop> findAll(SelectOptions options);

    @Insert
    int insert(Shop shop);

    @Update
    int update(Shop shop);
}
