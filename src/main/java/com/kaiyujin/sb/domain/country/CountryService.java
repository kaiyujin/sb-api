package com.kaiyujin.sb.domain.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CountryService {

    @Autowired
    CountryRepository countryRepository;

    @Cacheable(cacheNames = "countries")
    public List<Country> findAll() {
        return countryRepository.findAllByOrderByCode();
    }

}
