package com.kaiyujin.sb.domain.timezone;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TimezoneService {

    private final TimezoneRepository timezoneRepository;

    @Cacheable(cacheNames = "timezones")
    public List<Timezone> findAll() {
        return timezoneRepository.findAllByOrderByCode();
    }

}

