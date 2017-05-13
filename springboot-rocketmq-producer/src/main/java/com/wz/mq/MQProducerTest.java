package com.wz.mq;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by wangzi on 2017/5/13.
 */
@Component
public class MQProducerTest {
    @Autowired
    private DefaultMQProducer defaultMQProducer;
    @Value("${mq.topic}")
    private String topic;
    @Value("${mq.tag}")
    private String tag;

    @PostConstruct
    public void sendMsg() throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        for(int i=0; i<100; i++){
            String content = "[" + i + "]: Hello, RocketMQ";
            Message message = new Message(topic, tag, "MQ" + i, content.getBytes());
            defaultMQProducer.send(message);
        }
        defaultMQProducer.shutdown();
    }
}
