package com.wz.mq;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * <p></p>
 *
 * @author wangzi
 */
@Component
public class MqProducerTest implements ApplicationListener<ContextRefreshedEvent> {
    private static final int INIT_COUNT = 100;
    @Autowired
    private DefaultMQProducer defaultMQProducer;
    @Value("${mq.topic}")
    private String topic;
    @Value("${mq.tag}")
    private String tag;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try{
            for(int i=0; i<INIT_COUNT; i++){
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
