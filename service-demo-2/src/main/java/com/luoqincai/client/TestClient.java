package com.luoqincai.client;

import com.luoqincai.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "service-demo-1", configuration = FeignConfig.class)
public interface TestClient {
    @GetMapping("/test")
    String sayTest();
}
