package com.wz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class,args);
        synchronized (ProviderApplication.class){
            while (true){
                try {
                    ProviderApplication.class.wait();
                }catch (Throwable throwable){
                }
            }
        }
    }
}
