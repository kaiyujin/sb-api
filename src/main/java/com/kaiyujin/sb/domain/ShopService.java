package com.kaiyujin.sb.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@Transactional
public class ShopService {

    @Autowired
    ShopRepository shopRepository;

    public Page<Shop> findShops(Pageable pageable){
        return shopRepository.findAll(pageable);
    }

    public Optional<Shop> findById(Long id){
        return shopRepository.findById(id);
    }
}
