package com.kaiyujin.sb.controller.client;

import com.kaiyujin.sb.common.Constants;
import com.kaiyujin.sb.domain.country.Country;
import com.kaiyujin.sb.domain.country.CountryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Constants.CLIENT_API_BASE_URL+"/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Country> getCountries() {
        return countryService.findAll();
    }
}
