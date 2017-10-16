package com.wz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p></p>
 *
 * @author wangzi
 */
@SpringBootApplication
public class TbScheduleApplication {
    public static void main(String[] args) {
        SpringApplication.run(TbScheduleApplication.class, args);
        synchronized (TbScheduleApplication.class){
            while (true){
                try{
                    TbScheduleApplication.class.wait();
                }catch (Throwable throwable){
                }
            }
        }
    }
}
