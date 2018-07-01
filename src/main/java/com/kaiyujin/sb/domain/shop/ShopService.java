package com.kaiyujin.sb.domain.shop;

import com.kaiyujin.sb.controller.client.ShopDTO;
import com.kaiyujin.sb.common.exception.HTTPNotFoundException;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ShopService {

    private final ShopRepository shopRepository;

    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public Page<Shop> findShops(Pageable pageable) {
        SelectOptions options = SelectOptions.get().offset(0).limit(100).count();
        List<Shop> shops = shopRepository.findAll(options);
        Page<Shop> page = new PageImpl<Shop>(shops, pageable, options.getCount());
        return page;
    }

    public Shop findById(Long id) {
        return shopRepository.findById(id);
    }

    public Shop save(ShopDTO shopDTO) {
        var shop = new Shop();
        BeanUtils.copyProperties(shopDTO, shop);
        shopRepository.insert(shop);
        return shop;
    }

    public Shop save(Long id, ShopDTO shopDTO) {
        var shop = findById(id);
        if(Objects.isNull(shop)) {
            throw new HTTPNotFoundException();
        }
        shop.setName(shopDTO.getName());
        shop.setEmail(shopDTO.getEmail());
        shop.setPhoneNumber(shopDTO.getPhoneNumber());
        shop.setCountryCode(shopDTO.getCountryCode());
        shop.setTimezoneCode(shopDTO.getTimezoneCode());
        //TODO remove
        shop.setUpdatedBy(1);
        shopRepository.update(shop);
        return shop;
    }
}
