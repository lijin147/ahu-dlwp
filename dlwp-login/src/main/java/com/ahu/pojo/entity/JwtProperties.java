package com.ahu.pojo.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "dlwp.jwt")
public class JwtProperties {
    /**
     * JWT的相关配置
     */
    private String secretKey;
    private long ttl;
    private String tokenName;

}
