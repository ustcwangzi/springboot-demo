package com.wz.model;

import lombok.Data;

/**
 * <p></p>
 *
 * @author wangzi
 */
@Data
public class Order {
    private Long orderId;
    private String sender;
    private String receiver;
}
