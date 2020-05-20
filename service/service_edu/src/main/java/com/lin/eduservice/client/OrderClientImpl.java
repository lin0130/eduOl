package com.lin.eduservice.client;

import org.springframework.stereotype.Component;

@Component
public class OrderClientImpl implements OrderClient {

    @Override
    public boolean isBuyCourse(String memberid, String id) {
        return false;
    }
}
