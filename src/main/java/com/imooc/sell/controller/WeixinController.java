package com.imooc.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/**
 * @author linxr
 * @version 1.0
 * @since <pre>2019/1/27</pre>
 */
@Controller
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {

    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code){
        log.info("微信auth回调");
        log.info("code={}",code);

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx1bd462a40f446c7e&secret=7079e86044db0e839245774c2378f327&code="+code+"&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String respon = restTemplate.getForObject(url,String.class);
        log.info("respon={}",respon);
    }
}



