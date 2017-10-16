package com.wz.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.net.InetAddress;

/**
 * 初始化ES配置
 * Created by wangzi on 2017-08-18.
 */
@Component
@Configuration
public class ElasticsearchConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(ElasticsearchConfiguration.class);
    @Value("${es.cluster.nodes}")
    private String clusterNodes ;
    @Value("${es.cluster.name}")
    private String clusterName;
    private TransportClient client;

    @Bean
    public TransportClient init(){
        logger.info("Building ElasticSearch client");
        Settings settings = Settings.builder().put("cluster.name", clusterName).build();
        client = new PreBuiltTransportClient(settings);
        try {
            if (!"".equals(clusterNodes)){
                for (String nodes:clusterNodes.split(",")) {
                    String socket[] = nodes.split(":");
                    String address = socket[0];
                    Integer port = Integer.valueOf(socket[1]);
                    client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(address), port));
                }
            }
        } catch (Exception e) {
            logger.error("Error build ElasticSearch client: {}", e.getMessage());
        }
        return client;
    }

    @PreDestroy
    public void destory() {
        if (client != null){
            client.close();
        }
    }
}
