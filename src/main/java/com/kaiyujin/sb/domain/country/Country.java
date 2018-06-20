package com.kaiyujin.sb.domain.country;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "countries")
public class Country implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String code;
    private String name;
    private String ituCode;
}
