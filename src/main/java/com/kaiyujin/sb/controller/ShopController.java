package com.kaiyujin.sb.controller;

import com.kaiyujin.sb.domain.Shop;
import com.kaiyujin.sb.domain.ShopService;
import com.kaiyujin.sb.exception.HTTPNotFoundException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/shops")
public class ShopController {

    @Autowired
    ShopService shopService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "string", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "string", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @RequestMapping(value="", method=RequestMethod.GET)
    public Page<Shop> getShops(Pageable pageable) {
        return shopService.findShops(pageable);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Shop getShop(@PathVariable("id") Long id, HttpServletResponse res) {
        var shop = shopService.findById(id);
        if (!shop.isPresent()) {
            throw new HTTPNotFoundException();
        }
        return shop.get();
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public Shop create(@ModelAttribute ShopDTO shopDTO) {
        return shopService.save(shopDTO);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public Shop update(@ModelAttribute ShopDTO shopDTO, @PathVariable Long id) {
        return shopService.save(id,shopDTO);
    }
}
