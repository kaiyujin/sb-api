package com.kaiyujin.sb.domain.timezone;

import lombok.Data;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.jdbc.entity.NamingType;

import java.io.Serializable;

@Data
@Entity(naming = NamingType.SNAKE_LOWER_CASE)
@Table(name = "timezones")
public class Timezone implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String code;
    private String name;
    private String intervalTime;
}
