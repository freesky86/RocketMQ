package com.example.rocketmq.controller;

import jakarta.annotation.PostConstruct;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Max
 */
@RestController
@RequestMapping("/mq")
public class ProducerController {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    private static final String TOPIC = "TEST_TOPIC";

    @PostConstruct
    public void init() {
        System.out.println("Name Server: " + rocketMQTemplate.getProducer().getNamesrvAddr());
    }

    /**
     * 同步发送
      */
    @GetMapping("/sync-send")
    public String syncSend(@RequestParam String message) {
        SendResult result = rocketMQTemplate.syncSend(TOPIC, message);
        return "同步发送成功，MsgID: " + result.getMsgId();
    }

    /**
     * 异步发送
      */
    @GetMapping("/async-send")
    public void asyncSend(@RequestParam String message) {
        rocketMQTemplate.asyncSend(TOPIC, message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("异步发送成功：" + sendResult.getMsgId());
            }
            @Override
            public void onException(Throwable e) {
                System.err.println("异步发送失败：" + e.getMessage());
            }
        });
    }
}