package com.wz.model;

import lombok.Data;

/**
 * Created by wangzi on 2017-08-22.
 */
@Data
public class Order {
    private Long orderId;
    private String sender;
    private String receiver;
}
