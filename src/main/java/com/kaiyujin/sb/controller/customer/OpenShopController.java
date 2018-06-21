package com.kaiyujin.sb.controller.customer;

import com.kaiyujin.sb.domain.shop.Shop;
import com.kaiyujin.sb.domain.shop.ShopService;
import com.kaiyujin.sb.exception.HTTPNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/customer/shops")
public class OpenShopController {

    @Autowired
    ShopService shopService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Shop getShop(@PathVariable("id") Long id, HttpServletResponse res) {
        var shop = shopService.findById(id);
        if (!shop.isPresent()) {
            throw new HTTPNotFoundException();
        }
        return shop.get();
    }
}
