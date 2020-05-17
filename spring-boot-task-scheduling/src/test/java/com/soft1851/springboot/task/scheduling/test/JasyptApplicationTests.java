package com.soft1851.springboot.task.scheduling.test;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/17 9:22
 * @Version 1.0
 **/
@SpringBootTest
public class JasyptApplicationTests {
    @Autowired
    private StringEncryptor stringEncryptor;
    @Test
    public void contextLoads(){
        //加密方法
        String password=stringEncryptor.encrypt("root");
        System.out.println(password);
//        System.out.println(stringEncryptor.encrypt("123456"));
        //解密方法

        System.out.println(stringEncryptor.decrypt(password));
//        System.out.println(stringEncryptor.decrypt("lNhl4j55sknflVwdqfqdLw=="));
    }
}
