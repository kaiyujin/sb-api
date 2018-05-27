package com.kaiyujin.sb.controller;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ShopDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String email;
}
