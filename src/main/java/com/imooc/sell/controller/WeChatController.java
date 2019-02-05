package com.imooc.sell.controller;

import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author linxr
 * @version 1.0
 * @since <pre>2019/1/27</pre>
 */
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WeChatController {

    @Autowired
    private WxMpService wxMpService;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl){

        String url = "http://linxinran.natapp1.cc/sell/wechat/userInfo";
        //String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAUTH2_SCOPE_BASE, returnUrl);
        String redirectUrl = "https://payjs.cn/api/openid?mchid=1525190581&callback_url="+url;
        log.info("[微信页面授权]获取code，result={}", redirectUrl);
        return "redirect:"+redirectUrl;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("openid") String openid){
        /*WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try{
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        }catch (Exception e){
            log.error("[微信用户信息]错误：" + e.getMessage());
            throw new SellException(ResultEnum.WECHAT_MP_ERROR);
        }

        String openid = wxMpOAuth2AccessToken.getOpenId();*/
        String redirect="http://sell.com/#/";
        System.out.println(openid);

        return "redirect:" + redirect + "?openid=" + openid;
    }

}



