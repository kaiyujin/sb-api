package com.kaiyujin.sb.controller.client;

import com.kaiyujin.sb.common.Constants;
import com.kaiyujin.sb.domain.timezone.Timezone;
import com.kaiyujin.sb.domain.timezone.TimezoneService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Constants.CLIENT_API_BASE_URL+"/timezones")
public class TimezoneController {

    private final TimezoneService timezoneService;

    public TimezoneController(TimezoneService timezoneService) {
        this.timezoneService = timezoneService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Timezone> getTimezones() {
        return timezoneService.findAll();
    }
}