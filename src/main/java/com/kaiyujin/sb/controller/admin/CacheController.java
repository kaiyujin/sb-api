package com.kaiyujin.sb.controller.admin;

import com.kaiyujin.sb.common.Constants;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.CLIENT_API_BASE_URL + "/caches")
public class CacheController {

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @CacheEvict(cacheNames = {"countries", "user", "timezones"}, allEntries = true)
    public String clear() {
        return "{ \"status\": \"OK\" }";
    }
}
