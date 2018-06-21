package com.kaiyujin.sb.domain.timezone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TimezoneService {

    @Autowired
    TimezoneRepository timezoneRepository;

    @Cacheable(cacheNames = "timezones")
    public List<Timezone> findAll() {
        return timezoneRepository.findAllByOrderByCode();
    }

}

