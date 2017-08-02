package com.wz.mq;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by wangzi on 2017/5/13.
 */
@Component
public class MQConsumerTest implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private DefaultMQPushConsumer defaultMQPushConsumer;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        defaultMQPushConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> messageExtList, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for(MessageExt messageExt : messageExtList){
                    String topic = messageExt.getTopic();
                    String tags = messageExt.getTags();
                    String keys = messageExt.getKeys();
                    byte[] content = messageExt.getBody();
                    System.out.println("topic:" + topic + ",tags:" + tags + ",keys:" + keys + ",msg: " + new String(content));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        try{
            defaultMQPushConsumer.start();
            System.out.println("Consumer start ...");
        }catch (MQClientException e){
            System.err.println("Consumer start error!");
        }
    }
}
