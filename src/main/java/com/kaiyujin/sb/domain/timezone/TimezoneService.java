package com.kaiyujin.sb.domain.timezone;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TimezoneService {

    private final TimezoneRepository timezoneRepository;

    public TimezoneService(TimezoneRepository timezoneRepository) {
        this.timezoneRepository = timezoneRepository;
    }

    @Cacheable(cacheNames = "timezones")
    public List<Timezone> findAll() {
        return timezoneRepository.findAll();
    }

}

