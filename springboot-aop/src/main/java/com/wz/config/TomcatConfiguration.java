package com.wz.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p></p>
 *
 * @author wangzi
 */
@Configuration
public class TomcatConfiguration {
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> servletContainerFactory() {
        return factory -> factory.setPort(9999);
    }
}
