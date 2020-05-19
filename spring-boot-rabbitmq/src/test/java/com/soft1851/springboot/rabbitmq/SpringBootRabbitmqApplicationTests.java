package com.soft1851.springboot.rabbitmq;

import com.soft1851.springboot.rabbitmq.bean.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@Slf4j
class SpringBootRabbitmqApplicationTests {
    @Resource
    RabbitTemplate rabbitTemplate;

    @Resource
    private AmqpAdmin amqpAdmin;

    /**
     * 新建一个转发器
     */
    @Test
    public void createExchange(){
        amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
        System.out.println("创建转发器完成");
//        amqpAdmin.declareQueue(); //随机起名
//                                               队列名             是否是持久化
        amqpAdmin.declareQueue(new Queue("amqpadmin.queue", true));
//        创建绑定规则，将exchange和queue绑定起来
        //                                               目的地                    目的地类型                               交换器名称          路由键            map    可以没有
        amqpAdmin.declareBinding(new Binding("amqpadmin.queue",Binding.DestinationType.QUEUE,"amqpadmin.exchange","amqp.haha",null));

    }

    /**
     * 1.单播（点对点发送消息）
     */
    @Test
    void contextLoads() {
        //  Message需要自己构造一个;可以定义消息体内容和消息头
        //                     交换器    路由键   消息对象
//        rabbitTemplate.send(exchange,routeKey,message);
        //object默认当成消息体 只需要传入要发送的对象，自动序列化发送给rabbitmq
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "这是第一个消息");
        map.put("data", Arrays.asList("Hello World", 123, true, Book.builder().bookName("《西游记》").author("吴承恩").build()));
        //对象被默认序列化以后发送出去
        rabbitTemplate.convertAndSend("exchange.direct", "amqpadmin", map);
//        rabbitTemplate.convertAndSend("exchange.direct", "soft1851.news", map);
        /*
         * rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAACdAADbXNndAAV
         * 6L+Z5piv56ys5LiA5Liq5raI5oGvdAAEZGF0YXNyABpqYXZhLnV0aWwuQXJyYXlzJEFycmF5TGlzdNmkPL7NiAbSAgABWwABYXQAE1tMamF2YS9sYW5nL09i
         * amVjdDt4cHVyABdbTGphdmEuaW8uU2VyaWFsaXphYmxlO67QCaxT1+1JAgAAeHAAAAADdAALSGVsbG8gV29ybGRzcgARamF2YS5sYW5nLkludGVnZXIS4qCk
         * 94GHOAIAAUkABXZhbHVleHIAEGphdmEubGFuZy5OdW1iZXKGrJUdC5TgiwIAAHhwAAAAe3NyABFqYXZhLmxhbmcuQm9vbGVhbs0gcoDVnPruAgABWgAFdmFs
         * dWV4cAF4
         */
    }

    /**
     * 接收消息
     */
    /**
     * 将数据自动转化为json数据
     */
    @Test
    public void receive() {
//        Object o = rabbitTemplate.receiveAndConvert("soft1851.news");
        Object o = rabbitTemplate.receiveAndConvert("amqpadmin");
        log.info(o.getClass().toString());
        log.info(o.toString());
    }

    /**
     * 广播
     */
    @Test
    public void sendMsg(){
//        rabbitTemplate.convertAndSend("exchange.fanout",null, Book.builder().bookName("《三国演义》").author("罗贯中").build());
        rabbitTemplate.convertAndSend("exchange.fanout",null, Book.builder().bookName("《红楼梦》").author("曹雪芹").build());
    }
}
