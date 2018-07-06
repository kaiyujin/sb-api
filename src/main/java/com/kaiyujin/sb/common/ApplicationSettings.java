package com.kaiyujin.sb.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix="settings")
public class ApplicationSettings {
    private String secretKey;
}
