package com.kaiyujin.sb.domain.shop;

import lombok.Data;
import org.seasar.doma.*;
import org.seasar.doma.jdbc.entity.NamingType;

import javax.validation.constraints.Size;

@Data
@Entity(naming = NamingType.SNAKE_LOWER_CASE)
@Table(name = "shops")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    private String name;

    private String phoneNumber;

    private String email;

    private Long sectionId;

    private String countryCode;

    private String timezoneCode;

    private Integer displayOrder;

    private Integer createdBy;

    private Integer updatedBy;
}
