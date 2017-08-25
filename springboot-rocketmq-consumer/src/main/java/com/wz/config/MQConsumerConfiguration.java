package com.wz.config;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Created by wangzi on 2017/5/13.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "mq")
public class MQConsumerConfiguration {
    private String nameServerAddress;
    private String subscriberID;
    private String topic;

    @Bean
    public DefaultMQPushConsumer defaultMQPushConsumer(){
        // 订阅id，如果同一个id部署了多个节点的话，不会重复消费（数据会分散到多个节点去处理）
        // 如果是不同的id，则每个id消费的都是所有的数据
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(subscriberID);
        consumer.setNamesrvAddr(nameServerAddress);
        consumer.setMessageModel(MessageModel.CLUSTERING);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.setVipChannelEnabled(false);
        consumer.setPullBatchSize(2);
        consumer.setConsumeMessageBatchMaxSize(200);
        try{
            consumer.subscribe(topic, "*");
        }catch (MQClientException e){
            e.printStackTrace();
        }
        return consumer;
    }
}
