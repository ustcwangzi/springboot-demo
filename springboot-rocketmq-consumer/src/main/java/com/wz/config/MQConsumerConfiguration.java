package com.wz.config;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Created by wangzi on 2017/5/13.
 */
@Configuration
@ConfigurationProperties(prefix = "mq")
public class MQConsumerConfiguration {
    private String nameServerAddress;
    private String subscriberID;

    @Bean
    public DefaultMQPushConsumer defaultMQPushConsumer(){
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(subscriberID);
        consumer.setNamesrvAddr(nameServerAddress);
        consumer.setMessageModel(MessageModel.CLUSTERING);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        try{
            consumer.subscribe("TESTTOPIC", "*");
        }catch (MQClientException e){
            e.printStackTrace();
        }
        return consumer;
    }

    public String getNameServerAddress() {
        return nameServerAddress;
    }

    public void setNameServerAddress(String nameServerAddress) {
        this.nameServerAddress = nameServerAddress;
    }

    public String getSubscriberID() {
        return subscriberID;
    }

    public void setSubscriberID(String subscriberID) {
        this.subscriberID = subscriberID;
    }

}
