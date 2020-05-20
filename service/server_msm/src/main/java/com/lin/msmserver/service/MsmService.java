package com.lin.msmserver.service;

import java.util.Map;

public interface MsmService {
    boolean send(String PhoneNumbers, String templateCode, Map<String, Object> param);

}
