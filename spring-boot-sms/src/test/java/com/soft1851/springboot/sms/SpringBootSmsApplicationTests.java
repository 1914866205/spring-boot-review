package com.soft1851.springboot.sms;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.rds.model.v20140815.*;

import java.util.HashMap;

@SpringBootTest
class SpringBootSmsApplicationTests {

    @Test
    void contextLoads() {
        //连接阿里云
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4G3rkARQwiGGkprRZwGm",  "Okx5ZZrlOveWo6WzQR3tHtCrDx5Mdq");
        IAcsClient client = new DefaultAcsClient(profile);
        //构建请求
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", "18851855106");
        request.putQueryParameter("SignName", "智慧园区");
        request.putQueryParameter("TemplateCode", "SMS_190277609");
        request.putQueryParameter("TemplateParam", "{\"code\":\"5201314\"}");   //发送的验证码
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ClientException e) {
            e.printStackTrace();
        }


    }

}
