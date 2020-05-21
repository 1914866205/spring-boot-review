package com.soft1851.springboot.sms.controller;

import com.soft1851.springboot.sms.service.SendSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/21 10:31
 * @Version 1.0
 **/
@RestController
@CrossOrigin  //跨域支持
public class SmsApiController {
    @Autowired
    private SendSmsService sendSmsService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @GetMapping("/send/{phone}")
    public String code(@PathVariable("phone") String phone) {
        //调用发送方法，模拟真实业务 redis
        System.out.println("接受的phone"+phone);
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)) {
            return phone + ":" + code + "已存在，还没有过期";
        }
        //生成验证码并存储到redis中
        code = UUID.randomUUID().toString().substring(0, 4);
        HashMap<String, Object> param = new HashMap<>();
        param.put("code", code);
        boolean sms = sendSmsService.send(phone, "SMS_190277609", param);
        if (sms){
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.SECONDS);
            return phone + ":" + code + "发送成功";
        }else {
            return "发送失败";
        }
    }
}
