package com.wz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p></p>
 *
 * @author wangzi
 */
@SpringBootApplication
public class MqConsuemrApplication {
    public static void main(String[] args) {
        SpringApplication.run(MqConsuemrApplication.class, args);
        synchronized (MqConsuemrApplication.class){
            while (true){
                try{
                    MqConsuemrApplication.class.wait();
                }catch (Throwable throwable){
                }
            }
        }
    }
}
