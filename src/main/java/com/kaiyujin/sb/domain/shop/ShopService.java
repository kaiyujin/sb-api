package com.kaiyujin.sb.domain.shop;

import com.kaiyujin.sb.controller.client.ShopDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

    public Page<Shop> findShops(Pageable pageable) {
        return shopRepository.findAll(pageable);
    }

    public Optional<Shop> findById(Long id) {
        return shopRepository.findById(id);
    }

    public Shop save(ShopDTO shopDTO) {
        var shop = new Shop();
        BeanUtils.copyProperties(shopDTO, shop);
        //TODO remove
        shop.setCreatedBy(1);
        shop.setUpdatedBy(1);

        return shopRepository.save(shop);
    }

    public Shop save(Long id, ShopDTO shopDTO) {
        var shop = findById(id).get();
        BeanUtils.copyProperties(shopDTO, shop);
        //TODO remove
        shop.setUpdatedBy(1);

        return shopRepository.save(shop);
    }
}
