package com.kaiyujin.sb.domain.shop;

import com.kaiyujin.sb.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
public class Shop extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @Size(max = 255)
    private String name;

    @Column(nullable = false)
    private String phoneNumber;
}
