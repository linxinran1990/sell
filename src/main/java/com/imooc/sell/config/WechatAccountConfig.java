package com.imooc.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author linxr
 * @version 1.0
 * @since <pre>2019/1/27</pre>
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    private String mpAppId;

    private String mpAppSecret;

    private String mchKey;

    private String keyPath;

    private String notifyUrl;

    private String mchId;
}





