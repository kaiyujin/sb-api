package com.kaiyujin.sb.domain.shop;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    @Override
    Page<Shop> findAll(Pageable pageable);
}
