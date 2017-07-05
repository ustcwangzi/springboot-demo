package com.wz.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangzi on 2017-07-05.
 */
@ConfigurationProperties(prefix = "tbschedule")
public class TbScheduleProperties {
    private String zkConnectString;
    private String rootPath;
    private String zkSessionTimeout;
    private String userName;
    private String password;
    private boolean isCheckParentPath;

    public Map<String,String> getProperties(){
        Map<String,String> config = new HashMap<>();
        config.put("zkConnectString",this.getZkConnectString());
        config.put("rootPath",this.getRootPath());
        config.put("zkSessionTimeout",this.getZkSessionTimeout());
        config.put("userName",this.getUserName());
        config.put("password",this.getPassword());
        config.put("isCheckParentPath",this.isCheckParentPath()+"");
        return config;
    }

    public String getZkConnectString() {
        return zkConnectString;
    }

    public void setZkConnectString(String zkConnectString) {
        this.zkConnectString = zkConnectString;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public String getZkSessionTimeout() {
        return zkSessionTimeout;
    }

    public void setZkSessionTimeout(String zkSessionTimeout) {
        this.zkSessionTimeout = zkSessionTimeout;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isCheckParentPath() {
        return isCheckParentPath;
    }

    public void setCheckParentPath(boolean checkParentPath) {
        isCheckParentPath = checkParentPath;
    }
}
