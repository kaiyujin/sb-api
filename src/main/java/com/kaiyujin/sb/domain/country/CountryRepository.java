package com.kaiyujin.sb.domain.country;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    List<Country> findAllByOrderByCode();
}
