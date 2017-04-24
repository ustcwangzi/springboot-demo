package com.wz.domain;

import java.io.Serializable;

/**
 * Created by wangzi on 2017/4/20.
 */
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
