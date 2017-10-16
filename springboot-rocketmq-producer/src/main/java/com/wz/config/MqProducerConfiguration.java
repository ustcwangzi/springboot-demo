package com.wz.config;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p></p>
 *
 * @author wangzi
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "mq")
public class MqProducerConfiguration {
    private String producerGroup;
    private String instanceName;
    private String nameServerAddress;

    @Bean
    public DefaultMQProducer defaultMQProducer() throws MQClientException{
        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
        producer.setNamesrvAddr(nameServerAddress);
        producer.setInstanceName(instanceName);
        producer.setVipChannelEnabled(false);
        producer.start();
        return producer;
    }
}
