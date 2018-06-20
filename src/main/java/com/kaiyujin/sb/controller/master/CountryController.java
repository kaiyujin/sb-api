package com.kaiyujin.sb.controller.master;

import com.kaiyujin.sb.domain.country.Country;
import com.kaiyujin.sb.domain.country.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/master/countries")
public class CountryController {
    @Autowired
    CountryService countryService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Country> getCountries() {
        return countryService.findAll();
    }
}
