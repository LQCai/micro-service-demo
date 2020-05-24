package com.luoqincai.service;

import com.luoqincai.client.TestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SayTestService {
    @Autowired
    TestClient testClient;

    public String sayTest()
    {
        return testClient.sayTest();
    }
}
