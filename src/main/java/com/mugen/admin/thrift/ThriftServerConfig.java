package com.mugen.admin.thrift;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2016/12/25.
 */
@ConfigurationProperties
public class ThriftServerConfig {
    @Value("${thrift.server.port}")
    int port;

    @Value("${zookeeper.server.list}")
    String serverList;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
