package com.kaiyujin.sb.domain.country;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;

    @Cacheable(cacheNames = "countries")
    public List<Country> findAll() {
        return countryRepository.findAllByOrderByCode();
    }

}
