package com.wz;

import com.alibaba.dubbo.container.Main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p></p>
 *
 * @author wangzi
 */
@SpringBootApplication
public class ProviderTwoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderTwoApplication.class, args);
        synchronized (ProviderTwoApplication.class){
            while (true){
                try {
                    ProviderTwoApplication.class.wait();
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
