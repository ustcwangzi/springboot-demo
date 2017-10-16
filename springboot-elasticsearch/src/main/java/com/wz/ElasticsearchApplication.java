package com.wz;

import org.elasticsearch.action.get.MultiGetRequest;
import org.elasticsearch.action.get.MultiGetRequestBuilder;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

/**
 * <p></p>
 *
 * @author wangzi
 */
@SpringBootApplication
public class ElasticsearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchApplication.class, args);
    }

    @Autowired
    private TransportClient client;

    @PostConstruct
    public void test(){
        MultiGetRequestBuilder builder = client.prepareMultiGet();
        builder.add("order_index", "order_type", "AV3Z0zxtdW_DXpzjmumC", "AV3Z0zxtdW_DXpzjmumB");
        MultiGetResponse responses = builder.get();
        responses.forEach(response -> System.out.println(response.getResponse().getSource().get("receiver_name")));
    }
}
