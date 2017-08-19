package com.wz.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * Created by wangzi on 2017-08-19.
 */
@Component
public class TestCachePenetration {
    private static final Logger logger = LoggerFactory.getLogger(TestCachePenetration.class);
    @Autowired
    private CacheService service;
    private CountDownLatch latch = new CountDownLatch(10000);

    private class TestThread implements Runnable{
        @Override
        public void run() {
            try{
                //凑够10000个线程一起开始
                latch.await();
            }catch (InterruptedException e){
                logger.error(e.getMessage());
            }
            service.query();
        }
    }

    //10000个线程同时进行缓存查询
    public void test() throws Exception{
        for (int i=0; i<10000; i++){
            new Thread(new TestThread()).start();
            logger.info("====={}", i);
            latch.countDown();
        }
        Thread.sleep(2000);
        Integer count = service.getCount();
        logger.info("The count query db: {}", count);
    }

}
