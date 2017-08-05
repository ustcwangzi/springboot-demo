package com.wz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by wangzi on 2017-07-05.
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
