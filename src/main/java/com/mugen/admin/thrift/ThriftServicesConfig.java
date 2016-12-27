package com.mugen.admin.thrift;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/25.
 */
@ConfigurationProperties
public class ThriftServicesConfig implements InitializingBean {
    private List<Map<String,String>> services=new ArrayList<Map<String,String>>();

    private List<TService> serviceList=new ArrayList<TService>();

    public List<Map<String,String>> getServices() {
        return services;
    }
    public void setServices(List<Map<String,String>> services) {
        this.services = services;
    }

    public List<TService> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<TService> serviceList) {
        this.serviceList = serviceList;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        for(Map<String,String> serMap:services){
            ThriftServicesConfig.TService iservice=new ThriftServicesConfig.TService();
            iservice.setName(serMap.get("name"));
            iservice.setClasspath(serMap.get("classpath"));
            iservice.setMaxThreads(Integer.parseInt(serMap.get("maxThreads")));
            serviceList.add(iservice);
        }
    }

    class TService{
        private String name;
        private String classpath;
        private int maxThreads;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getClasspath() {
            return classpath;
        }

        public void setClasspath(String classpath) {
            this.classpath = classpath;
        }

        public int getMaxThreads() {
            return maxThreads;
        }

        public void setMaxThreads(int maxThreads) {
            this.maxThreads = maxThreads;
        }
    }
}
