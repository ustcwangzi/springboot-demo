package com.wz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by wangzi on 2017/5/13.
 */
@SpringBootApplication
public class MQConsuemrApplication {
    public static void main(String[] args) {
        SpringApplication.run(MQConsuemrApplication.class, args);
        synchronized (MQConsuemrApplication.class){
            while (true){
                try{
                    MQConsuemrApplication.class.wait();
                }catch (Throwable throwable){
                }
            }
        }
    }
}
