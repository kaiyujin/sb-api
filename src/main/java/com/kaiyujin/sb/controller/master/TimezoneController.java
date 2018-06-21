package com.kaiyujin.sb.controller.master;

import com.kaiyujin.sb.domain.timezone.Timezone;
import com.kaiyujin.sb.domain.timezone.TimezoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/master/timezones")
public class TimezoneController {
    @Autowired
    TimezoneService timezoneService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Timezone> getTimezones() {
        return timezoneService.findAll();
    }
}