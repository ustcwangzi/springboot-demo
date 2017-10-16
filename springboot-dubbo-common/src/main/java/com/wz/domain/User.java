package com.wz.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * <p></p>
 *
 * @author wangzi
 */
@Data
public class User implements Serializable{
    private static final long serialVersionUID = -1L;
    private int id;
    private String name;

    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
