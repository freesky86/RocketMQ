package com.example.rocketmq.service;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(
        topic = "TEST_TOPIC",
        consumerGroup = "${rocketmq.consumer.group}",
        selectorExpression = "*" // 消费所有Tag的消息[1](@ref)
)
public class ConsumerService implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        System.out.println("[消费者] 收到消息: " + message);
        // 此处添加业务处理逻辑（如数据库操作等）
    }
}
