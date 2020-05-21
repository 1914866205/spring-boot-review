package com.soft1851.springboot.sms.service;

import java.util.Map;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/21 10:21
 * @Version 1.0
 **/
public interface SendSmsService {
    public boolean send(String phoneNum, String templateCode, Map<String, Object> code);
}
