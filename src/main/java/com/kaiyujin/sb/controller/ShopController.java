package com.kaiyujin.sb.controller;

import com.kaiyujin.sb.domain.Shop;
import com.kaiyujin.sb.domain.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shops")
public class ShopController {

    @Autowired
    ShopService shopService;

    @RequestMapping(value="", method=RequestMethod.GET)
    public Page<Shop> getShops(Pageable pageable) {
        return shopService.findShops(pageable);
    }
}
