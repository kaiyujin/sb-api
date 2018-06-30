package com.kaiyujin.sb.controller.client;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class ShopDTO {
    @NotBlank
    @Length(max = 255)
    private String name;

    @NotBlank
    @Pattern(regexp = "\\d{10,11}")
    private String phoneNumber;

    @NotBlank
    @Length(max = 255)
    @Pattern(regexp = "^([\\w])+([\\w\\._-])*\\@([\\w])+([\\w\\._-])*\\.([a-zA-Z])+$")
    private String email;

    @NotBlank
    @Length(max = 3)
    private String countryCode;

    @NotBlank
    @Length(max = 3)
    private String timezoneCode;

    private Long sectionId;

    private Integer displayOrder;
}
