package com.kaiyujin.sb.domain.timezone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TimezoneRepository extends JpaRepository<Timezone, Long> {
    List<Timezone> findAllByOrderByCode();
}
