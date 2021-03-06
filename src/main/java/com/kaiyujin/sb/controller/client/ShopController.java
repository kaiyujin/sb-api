package com.kaiyujin.sb.controller.client;

import com.kaiyujin.sb.common.Constants;
import com.kaiyujin.sb.common.security.SimpleLoginUser;
import com.kaiyujin.sb.domain.shop.Shop;
import com.kaiyujin.sb.domain.shop.ShopService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(Constants.CLIENT_API_BASE_URL + "/shops")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

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
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Page<Shop> getShops(Pageable pageable) {
        return shopService.findShops(pageable);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Shop getShop(@PathVariable("id") Long id, HttpServletResponse res, @ModelAttribute SimpleLoginUser simpleLoginUser) {
        System.out.println("-----------" + simpleLoginUser);
        var shop = shopService.findById(id);
        return shop;
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Shop create(@RequestBody @Validated ShopDTO shopDTO) {
        return shopService.save(shopDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Shop update(@PathVariable Long id, @RequestBody @Validated ShopDTO shopDTO) {
        return shopService.save(id, shopDTO);
    }
}
