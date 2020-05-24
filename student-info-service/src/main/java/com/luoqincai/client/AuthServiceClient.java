package com.luoqincai.client;

import com.luoqincai.client.hystrix.AuthServiceHystrix;
import com.luoqincai.config.FeignConfig;
import com.luoqincai.entity.JWT;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * Created by fangzhipeng on 2017/5/27.
 */

@FeignClient(value = "security-service", configuration = FeignConfig.class)
public interface AuthServiceClient {

    @PostMapping(value = "/oauth/token")
    JWT getToken(@RequestHeader(value = "Authorization") String authorization, @RequestParam("grant_type") String type,
                 @RequestParam("username") String username, @RequestParam("password") String password);
}



