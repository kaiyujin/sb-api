package com.kaiyujin.sb.domain.country;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Cacheable(cacheNames = "countries")
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

}
