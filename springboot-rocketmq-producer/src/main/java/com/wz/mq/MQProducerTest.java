package com.wz.mq;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by wangzi on 2017/5/13.
 */
@Component
public class MQProducerTest  implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private DefaultMQProducer defaultMQProducer;
    @Value("${mq.topic}")
    private String topic;
    @Value("${mq.tag}")
    private String tag;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try{
            for(int i=0; i<100; i++){
                String content = "[" + i + "]: Hello, RocketMQ";
                Message message = new Message(topic, tag, "MQ" + i, content.getBytes());
                defaultMQProducer.send(message);
            }
        }catch (Exception e){
            System.err.println("信息发送失败");
            e.printStackTrace();
        }
        defaultMQProducer.shutdown();
    }
}
