package com.soft1851.springboot.sms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.rds.model.v20140815.AddTagsToResourceRequest;
import com.aliyuncs.rds.model.v20140815.AddTagsToResourceResponse;
import com.google.gson.Gson;
import com.soft1851.springboot.sms.service.SendSmsService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/21 10:26
 * @Version 1.0
 **/
@Service
public class SendSmsServiceImpl implements SendSmsService {

    @Override
    public boolean send(String phoneNum, String templateCode, Map<String, Object> code) {

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
        request.putQueryParameter("PhoneNumbers", phoneNum);
        request.putQueryParameter("SignName", "智慧园区");
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(code));   //发送的验证码
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return true;
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
}
}
